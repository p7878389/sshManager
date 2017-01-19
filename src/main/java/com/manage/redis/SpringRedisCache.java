package com.manage.redis;

import com.manage.util.SerializeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

/***
 * spring redis cacheManager
 */
public class SpringRedisCache implements Cache {

    private static final  Logger log = LoggerFactory.getLogger(SpringRedisCache.class);

    /***
     * redis模板
     */
    private RedisTemplate<String, Object> redisTemplate;

    /***
     * 缓存名称
     */
    private String name;
    /**
     * 超时时间
     */
    private long timeout;

    /***
     * 获取缓存名称
     *
     * @return
     */
    public String getName() {
        return this.name;
    }


    public Object getNativeCache() {
        return this.redisTemplate;
    }


    /***
     * 根据key获取对应的value
     *
     * @param key
     * @return
     */
    @Override
    public ValueWrapper get(Object key) {
        final String keyf = (String) key;

        Object object = null;
        object = redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {

                byte[] key = keyf.getBytes();
                byte[] value = connection.get(key);
                if (value == null) {
                    return null;
                }
                return SerializeUtils.INSTANCE.deserialize(value);

            }
        });
        return (object != null ? new SimpleValueWrapper(object) : null);
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        if (StringUtils.isEmpty(key) || null == type) {
            return null;
        } else {
            final String finalKey;
            final Class<T> finalType = type;
            if (key instanceof String) {
                finalKey = (String) key;
            } else {
                finalKey = key.toString();
            }
            final Object object = redisTemplate.execute(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    byte[] key = finalKey.getBytes();
                    byte[] value = connection.get(key);
                    if (value == null) {
                        return null;
                    }
                    return SerializeUtils.INSTANCE.deserialize(value);
                }
            });
            if (finalType.isInstance(object) && null != object) {
                return (T) object;
            } else {
                return null;
            }
        }
    }

    @Override
    public void put(Object key, Object value) {
        final String keyf = (String) key;
        final Object valuef = value;

        redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection)
                    throws DataAccessException {
                byte[] keyb = keyf.getBytes();
                byte[] valueb = SerializeUtils.INSTANCE.serialize(valuef);
                connection.set(keyb, valueb);
                connection.expire(keyb, timeout);
                return 1L;
            }
        });
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        put(key, value);
        return new SimpleValueWrapper(value);
    }

    /***
     * 根据key删除缓存
     *
     * @param key
     */
    @Override
    public void evict(Object key) {
        final String keyf = (String) key;
        redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection)
                    throws DataAccessException {
                return connection.del(keyf.getBytes());
            }
        });

    }

    @Override
    public void clear() {
        redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }


    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
}
