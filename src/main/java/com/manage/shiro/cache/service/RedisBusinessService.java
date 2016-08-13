package com.manage.shiro.cache.service;

/**
 * Created by Administrator on 2016/7/15.
 */
public interface RedisBusinessService {

    public Object setObject(String key, Object value, int expire) ;
}
