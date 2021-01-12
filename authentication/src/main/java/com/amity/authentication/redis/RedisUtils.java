package com.amity.authentication.redis;

import com.amity.authentication.common.StringConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Amity on 2021/1/11 18:18
 */
@Component
public class RedisUtils<T> {

    @Resource
    private RedisTemplate<String, T> redisTemplate;

    /**
     * 存入缓存并设置过期时间
     * @param key 键
     * @param value 值
     * @param time 过期时间
     * @return 存入结果  true/false
     */
    public boolean set(String key, T value, Long time) {
        try {
            if(time > 0 ) {
                redisTemplate.opsForValue().set(realKey(key), value, time, TimeUnit.MINUTES);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置过期时间
     * @param key
     * @param time
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if(time > 0) {
                redisTemplate.expire(this.realKey(key), time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String realKey(String key) {
        return StringConstant.REDIS_KEY_PREFIX + key;
    }

    /**
     * 根据key查询过期时间
     * @param key
     * @return
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(realKey(key), TimeUnit.SECONDS);
    }


    /**
     * 判断key是否存在
     */
    public Boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(realKey(key));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     * @param key  可以传一个值 或者 多个
     */
    public void deleteKey(String... key) {
        if(null != key && key.length > 0) {
            if(key.length == 1) {
                redisTemplate.delete(realKey(key[0]));
            } else {
                List<String> list = new ArrayList<>();
                for (String str : key) {
                    list.add(realKey(str));
                }
                redisTemplate.delete(list);
            }
        }
    }

    /**
     * 批量删除
     * @param keys  key集合
     */
    public void batchDeleteKey(Collection<String> keys) {
        if(!CollectionUtils.isEmpty(keys)) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 根据前缀删除缓存
     * @param prefix 前缀
     */
    public void deleteByPrefix(String prefix) {
        Set<String> keys = redisTemplate.keys(realKey(prefix) + "*");
        if(!CollectionUtils.isEmpty(keys)) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 根据key获取缓存
     * @param key key
     * @return T
     */
    public T get(String key) {
        return StringUtils.isEmpty(key) ? null : redisTemplate.opsForValue().get(realKey(key));
    }

    /**
     * 存入缓存  不设置过期时间
     * @param key 键
     * @param value 值
     * @return 存入结果  true/false
     */
    public boolean set(String key, T value) {
        try {
            redisTemplate.opsForValue().set(realKey(key), value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增
     * @param key 键
     * @param step 步长
     * @return 结果值
     */
    public long incr(String key, long step) {
        if(step < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(realKey(key), step);
    }

    /**
     * 递减
     * @param key 键
     * @param step 步长
     * @return 结果值
     */
    public long decr(String key, long step) {
        if(step < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().decrement(realKey(key), step);
    }


    ////////////////////////////////// Map //////////////////////////////////

    /**
     * HashGet
     * @param key  键
     * @param hashKey  map-key
     * @return map-value
     */
    public T hget(String key, String hashKey) {
        return (T) redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 获取key对应的map值
     * @param key 键
     * @return map
     */
    public Map<String, T> hmget(String key) {
        BoundHashOperations<String, String, T> operations = redisTemplate.boundHashOps(key);
        Map<String, T> entries = operations.entries();
        return entries;
    }

    /**
     * 存入map
     * @param key 键
     * @param map 值--以map为形式
     * @return 存入结果  true/false
     */
    public boolean hmset(String key, Map<String, T> map) {
        try {
            redisTemplate.opsForHash().putAll(realKey(key), map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 存入map并设置过期时间
     * @param key 键
     * @param map 值--以map为形式
     * @param time 过期时间
     * @return 存入结果  true/false
     */
    public boolean hmset(String key, Map<String, T> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(realKey(key), map);
            if(time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向已存在的map中插入一个value
     * 若map不存在，则创建一个map,存入value
     * @param key 键
     * @param hashKey map-key
     * @param value map-value
     * @return 存入结果  true/false
     */
    public boolean hset(String key, String hashKey, T value) {
        try {
            redisTemplate.opsForHash().put(realKey(key), hashKey, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向已存在的map中插入一个value
     * 若map不存在，则创建一个map,存入value
     * @param key 键
     * @param hashKey map-key
     * @param value map-value
     * @param time 过期时间 注意：如果已存在的map有时间，该操作会替换原来的时间
     * @return 存入结果  true/false
     */
    public boolean hset(String key, String hashKey, T value, long time) {
        try {
            redisTemplate.opsForHash().put(realKey(key), hashKey, value);
            if(time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     * @param key 键
     * @param hashKeys map-key
     */
    public void hdelete(String key, Object... hashKeys) {
        redisTemplate.opsForHash().delete(realKey(key), hashKeys);
    }

    /**
     * 判断hash表中是否存在该项的值
     * @param key 键
     * @param hashKey map-key
     * @return 是否存在
     */
    public boolean hHasKey(String key, Object hashKey) {
        return redisTemplate.opsForHash().hasKey(realKey(key), hashKey);
    }

    /**
     * map递增，如果不存在，创建一个新的
     * @param key 键
     * @param hashKey map-key
     * @param step 步长
     * @return 结果值
     */
    public long hincr(String key, String hashKey, long step) {
        return redisTemplate.opsForHash().increment(realKey(key), hashKey, step);
    }

    /**
     * map递减，如果不存在，创建一个新的
     * @param key 键
     * @param hashKey map-key
     * @param step 步长
     * @return 结果值
     */
    public long hdecr(String key, String hashKey, long step) {
        return redisTemplate.opsForHash().increment(realKey(key), hashKey, -step);
    }


    ////////////////////////////////// set //////////////////////////////////


}
