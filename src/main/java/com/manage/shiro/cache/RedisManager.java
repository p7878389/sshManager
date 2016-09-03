package com.manage.shiro.cache;

import com.manage.redis.RedisClient;
import com.manage.util.SerializeUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Administrator on 2016/6/27.
 */
public class RedisManager extends RedisClient {

    private static Logger logger = LoggerFactory.getLogger(RedisManager.class);

    /**
     * 单位为 秒(s)
     */
    private int expire = 0;

    private JedisPool jedisPool;

    public RedisManager() {
    }

    /***
     * 获取缓存中 shiro session
     *
     * @param key
     * @return
     */
    public Session getSession(String key) {
        Jedis jedis = jedisPool.getResource();
        Session session = null;
        try {
            final byte[] keyByte = key.getBytes();
            final byte[] valueByte = jedis.get(keyByte);
            if (valueByte != null) {
                session = (Session) SerializeUtils.INSTANCE.deserialize(valueByte);
            }
        } finally {
            jedisPool.returnResource(jedis);
        }
        return session;
    }

    /***
     * 设置缓存  并设置过期时间
     *
     * @param key
     * @param value
     * @param expire
     */
    public Object setObject(String key, Object value, int expire) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(SerializeUtils.INSTANCE.serialize(key), SerializeUtils.INSTANCE.serialize(value));
            if (expire != 0) {
                jedis.expire(key, expire);
            } else {
                if (this.expire != 0) {
                    jedis.expire(key, this.expire);
                }
            }
        } finally {
            jedisPool.returnResource(jedis);
        }
        return value;
    }

    /***
     * 根据key获取缓存对象
     *
     * @param key
     * @return
     */
    public Object getObject(String key) {
        Jedis jedis = jedisPool.getResource();
        final byte[] keyByte = SerializeUtils.INSTANCE.serialize(key);
        byte[] valueByte = null;
        try {
            valueByte = jedis.get(keyByte);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return SerializeUtils.INSTANCE.deserialize(valueByte);
    }


    /***
     * 根据key删除缓存对象
     *
     * @param key
     */
    public void delObject(String key) {
        Jedis jedis = jedisPool.getResource();
        final byte[] keyByte = SerializeUtils.INSTANCE.serialize(key);
        try {
            jedis.del(keyByte);
        } finally {
            jedisPool.returnResource(jedis);
        }
    }

    /***
     * 把对象放入Hash中 自定义注解使用
     *
     * @param key
     * @param field
     * @param o
     * @param expire
     */
    public void hset(String key, String field, Object o, int expire) {
        Jedis jedis = jedisPool.getResource();
        final byte[] keyByte = SerializeUtils.INSTANCE.serialize(key);
        final byte[] fieldByte = SerializeUtils.INSTANCE.serialize(field);
        final byte[] objByte = SerializeUtils.INSTANCE.serialize(o);
        try {
            jedis.hset(keyByte, fieldByte, objByte);
            if (expire != 0) {
                jedis.expire(keyByte, expire);
            } else {
                jedis.expire(keyByte, this.expire);
            }
        } finally {
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 从Hash中获取对象 自定义注解
     *
     * @param key
     * @param field
     * @param clzz
     * @param <T>
     * @return
     */
    public <T> T hget(String key, String field, Class<T> clzz) {
        Jedis jedis = jedisPool.getResource();
        final byte[] keyByte = SerializeUtils.INSTANCE.serialize(key);
        final byte[] fieldByte = SerializeUtils.INSTANCE.serialize(field);
        Object object = null;
        try {
            object = SerializeUtils.INSTANCE.deserialize(jedis.hget(keyByte, fieldByte));
            if (clzz != null && clzz.isInstance(object) && null != object) {
                return (T) object;
            } else {
                return null;
            }
        } finally {
            jedisPool.returnResource(jedis);
        }
    }

    /***
     * 从Hash中删除对象 自定义注解
     *
     * @param key
     * @param field
     */
    public void hdel(String key, String field) {
        Jedis jedis = jedisPool.getResource();
        final byte[] keyByte = SerializeUtils.INSTANCE.serialize(key);
        final byte[] fieldByte = SerializeUtils.INSTANCE.serialize(field);
        try {
            Object result = jedis.hdel(keyByte, fieldByte);
        } finally {
            jedisPool.returnResource(jedis);
        }
    }


    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
}
