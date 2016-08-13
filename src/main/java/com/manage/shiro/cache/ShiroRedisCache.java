package com.manage.shiro.cache;

import com.manage.redis.RedisClient;
import redis.clients.jedis.Jedis;

import java.util.Set;

/***
 * redis管理shiro相关缓存
 * <p>
 * 实际业务缓存不是用该类
 */
public class ShiroRedisCache {

    private RedisClient redisClient;


    /**
     * get value from redis
     *
     * @param key
     * @return
     */
    public byte[] get(byte[] key) {
        byte[] value = null;
        Jedis jedis = redisClient.getJedisPool().getResource();
        try {
            value = jedis.get(key);
        } finally {
            redisClient.getJedisPool().returnResource(jedis);
        }
        return value;
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @return
     */
    public byte[] set(byte[] key, byte[] value) {
        Jedis jedis = redisClient.getJedisPool().getResource();
        try {
            jedis.set(key, value);
            if (redisClient.getExpire() != 0) {
                jedis.expire(key, redisClient.getExpire());
            }
        } finally {
            redisClient.getJedisPool().returnResource(jedis);
        }
        return value;
    }

    /**
     * get value from redis
     *
     * @param key
     * @return
     */
    public String get(String key) {
        String value = null;
        Jedis jedis = redisClient.getJedisPool().getResource();
        try {
            value = jedis.get(key);
        } finally {
            redisClient.getJedisPool().returnResource(jedis);
        }
        return value;
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        Jedis jedis = redisClient.getJedisPool().getResource();
        try {
            jedis.set(key, value);
            if (redisClient.getExpire() != 0) {
                jedis.expire(key, redisClient.getExpire());
            }
        } finally {
            redisClient.getJedisPool().returnResource(jedis);
        }
        return value;
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public void set(byte[] key, byte[] value, int expire) {
        Jedis jedis = redisClient.getJedisPool().getResource();
        try {
            jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        } finally {
            redisClient.getJedisPool().returnResource(jedis);
        }
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public String set(String key, String value, int expire) {
        Jedis jedis = redisClient.getJedisPool().getResource();
        try {
            jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
        } finally {
            redisClient.getJedisPool().returnResource(jedis);
        }
        return value;
    }

    /**
     * del
     *
     * @param key
     */
    public void del(byte[] key) {
        Jedis jedis = redisClient.getJedisPool().getResource();
        try {
            jedis.del(key);
        } finally {
            redisClient.getJedisPool().returnResource(jedis);
        }
    }

    /**
     * del
     *
     * @param key
     */
    public void del(String key) {
        Jedis jedis = redisClient.getJedisPool().getResource();
        try {
            jedis.del(key);
        } finally {
            redisClient.getJedisPool().returnResource(jedis);
        }
    }

    /**
     * flush
     */
    public void flushDB() {
        Jedis jedis = redisClient.getJedisPool().getResource();
        try {
            jedis.flushDB();
        } finally {
            redisClient.getJedisPool().returnResource(jedis);
        }
    }

    /**
     * size
     */
    public Long dbSize() {
        Long dbSize = 0L;
        Jedis jedis = redisClient.getJedisPool().getResource();
        try {
            dbSize = jedis.dbSize();
        } finally {
            redisClient.getJedisPool().returnResource(jedis);
        }
        return dbSize;
    }

    /**
     * keys
     *
     * @param pattern
     * @return
     */
    public Set<byte[]> keys(String pattern) {
        Set<byte[]> keys = null;
        Jedis jedis = redisClient.getJedisPool().getResource();
        try {
            keys = jedis.keys(pattern.getBytes());
        } finally {
            redisClient.getJedisPool().returnResource(jedis);
        }
        return keys;
    }

    public RedisClient getRedisClient() {
        return redisClient;
    }

    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }
}
