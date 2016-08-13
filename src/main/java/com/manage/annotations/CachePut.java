package com.manage.annotations;

import java.lang.annotation.*;

/**
 * 缓存更新
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CachePut {
    String key();
    String fieldKey() ;
    int expireTime() default 3600;
}
