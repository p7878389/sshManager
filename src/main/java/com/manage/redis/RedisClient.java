package com.manage.redis;


import com.manage.util.SerializeUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis初始化
 */
public class RedisClient {

    private static final Logger log= LoggerFactory.getLogger(RedisClient.class);

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

    public RedisClient(){
        System.out.print(111);
    }

    public RedisClient(JedisPoolConfig jedisPoolConfig,String host,int port,String passWord,int expire,boolean flag) {
        /**
         * 判断是否请用redis缓存
         */
        if (flag) {
            try {
                this.host=host;
                this.jedisPoolConfig=jedisPoolConfig;
                this.passWord=passWord;
                this.port=port;
                this.flag=flag;
                this.expire=expire;

                jedisPool = new JedisPool(jedisPoolConfig, host, port, expire, passWord);

                log.info("redis init success");
            }catch (Exception e){
                log.error("redis init error",e);
                log.info("redis init error");
            }
        }

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
                session = (Session) SerializeUtils.deserialize(valueByte);
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
        Jedis jedis= jedisPool.getResource();
        try {
            jedis.set(SerializeUtils.serialize(key), SerializeUtils.serialize(value));
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
        final byte[] keyByte = SerializeUtils.serialize(key);
        byte[] valueByte = null;
        try {
            valueByte = jedis.get(keyByte);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return SerializeUtils.deserialize(valueByte);
    }


    /***
     * 根据key删除缓存对象
     *
     * @param key
     */
    public void delObject(String key) {
        Jedis jedis = jedisPool.getResource();
        final byte[] keyByte = SerializeUtils.serialize(key);
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
        final byte[] keyByte = SerializeUtils.serialize(key);
        final byte[] fieldByte = SerializeUtils.serialize(field);
        final byte[] objByte = SerializeUtils.serialize(o);
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
        final byte[] keyByte = SerializeUtils.serialize(key);
        final byte[] fieldByte = SerializeUtils.serialize(field);
        Object object = null;
        try {
            object = SerializeUtils.deserialize(jedis.hget(keyByte, fieldByte));
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
        final byte[] keyByte = SerializeUtils.serialize(key);
        final byte[] fieldByte = SerializeUtils.serialize(field);
        try {
            jedis.hdel(keyByte, fieldByte);
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
}
