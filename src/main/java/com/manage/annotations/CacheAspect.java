package com.manage.annotations;

import com.manage.redis.RedisClient;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.Assert;

import java.lang.reflect.Method;

/**
 * 自定注解实现
 */
@Aspect
public class CacheAspect {

    private Logger logger = LoggerFactory.getLogger(CacheAspect.class);

    private RedisClient redisClient;

    public CacheAspect() {
    }

    public CacheAspect(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    @Pointcut(value = "execution(@Cacheable * *.*(..))")
    public void setCacheRedis() {
    }

    @Pointcut(value = "execution(@CacheEvict * *.*(..))")
    public void cacheEvict() {
    }

    @Pointcut(value = "execution(@CachePut * *.*(..))")
    public void cacheRedis() {
    }

    /**
     * aop实现自定缓存注解
     *
     * @param joinPoint
     * @return
     */
    @Around("setCacheRedis()")
    public Object setCache(ProceedingJoinPoint joinPoint) {

        Object result = null;
        Method method = getMethod(joinPoint);
        Cacheable cacheable = method.getAnnotation(Cacheable.class);
        /**
         * 判断是否开启redis缓存
         */
        if (!redisClient.isFlag()) {
            try {
                result = joinPoint.proceed();
                return result;
            } catch (Throwable throwable) {
                logger.error("cache param for key{} , fieldKey{} ", cacheable.key(), cacheable.fieldKey());
                logger.error("cache Throwable", throwable);
            }
        }

        String fieldKey = parseKey(cacheable.fieldKey(), method, joinPoint.getArgs());

        String redisKey = cacheable.key() + fieldKey;

        //获取方法的返回类型,让缓存可以返回正确的类型
        Class returnType = ((MethodSignature) joinPoint.getSignature()).getReturnType();

        //使用redis 的hash进行存取，易于管理
        result = redisClient.getRedisObject(redisKey, returnType);

        if (result == null) {
            try {
                result = joinPoint.proceed();
                redisClient.setRedisObject(redisKey, result, cacheable.expireTime());
            } catch (Throwable e) {
                logger.error("annotation Throwable", e);
            }
        }
        return result;
    }

    /**
     * 缓存清除
     *
     * @param joinPoint
     */
    @Around("cacheEvict()")
    public void evict(ProceedingJoinPoint joinPoint) {
        /**
         * 判断是否开启redis缓存
         */
        if (!redisClient.isFlag()) {
            return;
        }
        Method method = getMethod(joinPoint);
        CacheEvict cacheable = method.getAnnotation(CacheEvict.class);
        String fieldKey = parseKey(cacheable.fieldKey(), method, joinPoint.getArgs());
        redisClient.delRedisObject(fieldKey);
    }


    @Around("cacheRedis()")
    public void putCache(ProceedingJoinPoint joinPoint) {
        /**
         * 判断是否开启redis缓存
         */
        if (!redisClient.isFlag()) {
            return;
        }

        Object result = null;

        Method method = getMethod(joinPoint);
        CachePut cacheable = method.getAnnotation(CachePut.class);

        String fieldKey = parseKey(cacheable.fieldKey(), method, joinPoint.getArgs());

        //获取方法的返回类型,让缓存可以返回正确的类型
        Class returnType = ((MethodSignature) joinPoint.getSignature()).getReturnType();

        String redisKey = cacheable.key() + fieldKey;
        //使用redis 的hash进行存取，易于管理
        result = redisClient.getRedisObject(redisKey, returnType);

        try {
            if (result == null) {
                result = joinPoint.proceed();
                Assert.notNull(fieldKey);
                redisClient.setRedisObject(redisKey, result, cacheable.expireTime());
            } else {
                redisClient.delRedisObject(redisKey);
                result = joinPoint.proceed();
                Assert.notNull(fieldKey);
                redisClient.setRedisObject(redisKey, result, cacheable.expireTime());
            }
        } catch (Throwable e) {
            logger.error("annotation Throwable", e);
        }
    }

    public Method getMethod(ProceedingJoinPoint pjp) {
        //获取参数的类型
        Object[] args = pjp.getArgs();
        Class[] argTypes = new Class[pjp.getArgs().length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }
        Method method = null;
        try {
            method = pjp.getTarget().getClass().getMethod(pjp.getSignature().getName(), argTypes);
        } catch (NoSuchMethodException e) {
            logger.error("annotation no sucheMehtod", e);
        } catch (SecurityException e) {
            logger.error("annotation SecurityException", e);
        }
        return method;

    }

    private String parseKey(String key, Method method, Object[] args) {


        //获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer u =
                new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = u.getParameterNames(method);

        //使用SPEL进行key的解析
        ExpressionParser parser = new SpelExpressionParser();
        //SPEL上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        //把方法参数放入SPEL上下文中
        for (int i = 0; i < paraNameArr.length; i++) {
            context.setVariable(paraNameArr[i], args[i]);
        }
        return parser.parseExpression(key).getValue(context, String.class);
    }

    public RedisClient getRedisClient() {
        return redisClient;
    }

    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }
}
