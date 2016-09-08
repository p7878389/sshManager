package com.manage.redis;


import com.manage.util.JsonUtil;
import com.manage.util.SerializeUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.UnsupportedEncodingException;

/**
 * redis初始化
 */
public class RedisClient {

    private static final Logger log = LoggerFactory.getLogger(RedisClient.class);

    private JedisPoolConfig jedisPoolConfig;

    private JedisPool jedisPool;//非切片连接池

    private String host;

    private int port;

    private String passWord;

    /**
     * 单位为 秒(s)
     */
    private int expire;

    private boolean flag;

    private boolean shiroCache;

    public RedisClient() {
    }

    public RedisClient(JedisPoolConfig jedisPoolConfig, String host, int port, String passWord, int expire, boolean flag, boolean shiroCache) {
        /**
         * 判断是否请用redis缓存
         */
        if (flag) {

            this.host = host;
            this.jedisPoolConfig = jedisPoolConfig;
            this.passWord = passWord;
            this.port = port;
            this.flag = flag;
            this.expire = expire;
            this.shiroCache = shiroCache;
            try {
                this.jedisPool = new JedisPool(this.jedisPoolConfig, this.host, this.port, this.expire, this.passWord);
                log.info("redis init success");
            } catch (Exception e) {
                e.printStackTrace();
                log.error("redis init error", e);
            }
        }

    }

    /***
     * 获取缓存中 shiro session
     *
     * @param key
     * @return
     */
    public Session getSession(String key) throws UnsupportedEncodingException {
        Jedis jedis = jedisPool.getResource();
        Session session = null;
        try {
            final byte[] keyByte = key.getBytes("UTF-8");
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
     * 根据key获取缓存对象
     *
     * @param key
     * @return
     */
    public <T> T getObject(String key, Class<T> clzz) {
        Jedis jedis = jedisPool.getResource();
        final byte[] keyByte = SerializeUtils.INSTANCE.serialize(key);
        byte[] valueByte = null;
        try {
            valueByte = jedis.get(keyByte);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return (T) SerializeUtils.INSTANCE.deserialize(valueByte);
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
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            final byte[] keyByte = SerializeUtils.INSTANCE.serialize(key);
            final byte[] fieldByte = SerializeUtils.INSTANCE.serialize(field);
            Object object = null;
            object = SerializeUtils.INSTANCE.deserialize(jedis.hget(keyByte, fieldByte));
            if (clzz != null && clzz.isInstance(object) && null != object) {
                return (T) object;
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("jedis init error");
            return null;
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
            jedis.hdel(keyByte, fieldByte);
        } finally {
            jedisPool.returnResource(jedis);
        }
    }


    public <T> T getRedisObject(String key, Class<T> clzz) {
        Jedis jedis = jedisPool.getResource();
        try {
            String objStr = jedis.get(key);
            return (T) JsonUtil.INSTANCE.jsonStrTObject(objStr, clzz);
        } finally {
            jedisPool.returnResource(jedis);
        }
    }

    public void setRedisObject(String key, Object o, int expire) {
        Jedis jedis = jedisPool.getResource();
        String objJson = JsonUtil.INSTANCE.objectToJson(o);
        try {
            jedis.set(key, objJson);
            if (expire != 0) {
                jedis.expire(key, expire);
            } else {
                jedis.expire(key, this.expire);
            }
        } finally {
            jedisPool.returnResource(jedis);
        }
    }

    public void delRedisObject(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.decr(key);
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

    public JedisPoolConfig getJedisPoolConfig() {
        return jedisPoolConfig;
    }

    public void setJedisPoolConfig(JedisPoolConfig jedisPoolConfig) {
        this.jedisPoolConfig = jedisPoolConfig;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isShiroCache() {
        return shiroCache;
    }

    public void setShiroCache(boolean shiroCache) {
        this.shiroCache = shiroCache;
    }
}
