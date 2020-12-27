package com.amity.common.utils;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Amity on 2020/12/24 11:37
 */
public class BeanMapper {

    private static final Mapper MAPPER = DozerBeanMapperBuilder.buildDefault();

    public BeanMapper() {
    }

    /**
     * 对象转换
     * @param source 源数据
     * @param destinationClass 目标类型
     * @param <S> 泛型 源数据类型
     * @param <D> 泛型 目标类型
     * @return 转换后的目标结果
     */
    public static <S, D> D map(S source, Class<D> destinationClass) {
        return null == source ? null : MAPPER.map(source, destinationClass);
    }

    /**
     * list转换
     * @param sourceList 源数据
     * @param destinationClass 目标类型
     * @param <S> 泛型 源数据类型
     * @param <D> 泛型 目标类型
     * @return 转换后的目标结果list
     */
    public static <S, D> List<D> mapList(Iterable<S> sourceList, Class<D> destinationClass) {
        if(null == sourceList) {
            return null;
        }else {
            List<D> destinationList = new ArrayList<>();
            Iterator<S> sourceIterator = sourceList.iterator();

            while (sourceIterator.hasNext()) {
                S source = sourceIterator.next();
                if(null != source) {
                    destinationList.add(MAPPER.map(source,destinationClass));
                }
            }

            return destinationList;
        }
    }

    /**
     * 数组转换
     * @param sourceArray 源数组
     * @param destinationClass  目标类型
     * @param <S> 泛型 源数据类型
     * @param <D> <D> 泛型 目标类型
     * @return 转换后的目标结果Array
     */
    public static <S, D> D[] mapArray(final S[] sourceArray, final Class<D> destinationClass) {
        if(null == sourceArray) {
            return null;
        }else {
            D[] destinationArray = (D[]) Array.newInstance(destinationClass, sourceArray.length);
            int i = 0;
            int sourceLength = sourceArray.length;

            for (int j = 0; j < sourceLength; ++j) {
                S source = sourceArray[j];
                if(null != source) {
                    destinationArray[i] = MAPPER.map(source, destinationClass);
                    ++i;
                }
            }

            return destinationArray;
        }
    }

    public static void copy(Object source, Object destinationObject) {
        MAPPER.map(source, destinationObject);
    }
}
