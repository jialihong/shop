package com.amity.account.common.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amity on 2020/12/24 14:47
 */
@Aspect
@Component
public class DaoAspect {

    private static final String CREATE_BY = "createBy";
    private static final String CREATE_TIME = "createTime";
    private static final String LAST_MODIFIED_BY = "lastModifiedBy";
    private static final String LAST_MODIFIED_TIME = "lastModifiedTime";
    private static final String DISABLED_TIME = "disabledTime";
    private static final String ENABLED = "enabled";
    private static final String VERSION = "version";
    // 指定模式
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @Pointcut("execution(*  com.amity.account.dao.*.insert*( .. ))")
    public void insert(){

    }

    @Pointcut("execution(*  com.amity.account.dao.*.batchInsert*( .. ))")
    public void batchInsert() {

    }

    @Pointcut("execution(*  com.amity.account.dao.*.update*( .. ))")
    public void update() {

    }

    @Before("insert()")
    public void doBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if(null != args && args.length > 0) {
            for (Object argument : args) {
                BeanWrapper beanWrapper = new BeanWrapperImpl(argument);
                initValue(beanWrapper);
            }
        }
    }


    @Before("batchInsert()")
    public void doBeforeBatch(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if(null != args && args.length > 0) {
            for (Object argument : args) {
                List<Object> objectList = castList(argument, Object.class);
                for (Object o : objectList) {
                    BeanWrapper beanWrapper = new BeanWrapperImpl(o);
                    initValue(beanWrapper);
                }
            }
        }
    }

    @Before("update()")
    public void doBeforeUpdate(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if(null != args && args.length > 0) {
            for(Object o : args) {
                BeanWrapper beanWrapper = new BeanWrapperImpl(o);
                //设置修改人和修改时间
                if(beanWrapper.isWritableProperty(LAST_MODIFIED_BY)) {
                    beanWrapper.setPropertyValue(LAST_MODIFIED_BY, getLoginUser());
                }
                if(beanWrapper.isWritableProperty(LAST_MODIFIED_TIME)) {
                    beanWrapper.setPropertyValue(LAST_MODIFIED_TIME, LocalDateTime.now());
                }
            }
        }

    }



    private <T> List<T> castList(Object argument, Class<T> clazz) {
        List<T> result = new ArrayList<>(16);
        if(argument instanceof List<?>) {
            for (Object o : (List<?>)argument) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }


    private void initValue(BeanWrapper beanWrapper) {
        //设置创建时间、修改时间、删除时间
        if(beanWrapper.isWritableProperty(CREATE_TIME)) {
            beanWrapper.setPropertyValue(CREATE_TIME, LocalDateTime.now());
        }
        if(beanWrapper.isWritableProperty(LAST_MODIFIED_TIME)) {
            beanWrapper.setPropertyValue(LAST_MODIFIED_TIME, LocalDateTime.now());
        }
        if(beanWrapper.isWritableProperty(DISABLED_TIME)) {
            beanWrapper.setPropertyValue(DISABLED_TIME, LocalDateTime.parse("1970-01-01 08:00:00", dateTimeFormatter));
        }

        //设置创建人和修改人
        if(beanWrapper.isWritableProperty(CREATE_BY)) {
            beanWrapper.setPropertyValue(CREATE_BY, getLoginUser());
        }
        if(beanWrapper.isWritableProperty(LAST_MODIFIED_BY)) {
            beanWrapper.setPropertyValue(LAST_MODIFIED_BY, getLoginUser());
        }

        //设置其他
        if(beanWrapper.isWritableProperty(ENABLED)) {
            beanWrapper.setPropertyValue(ENABLED, 1);
        }
        if(beanWrapper.isWritableProperty(VERSION)) {
            beanWrapper.setPropertyValue(VERSION, 1);
        }
    }


    /**
     * TODO
     * 获取当前登录人
     * @return String
     */
    private String getLoginUser() {

        return "";
    }
}
