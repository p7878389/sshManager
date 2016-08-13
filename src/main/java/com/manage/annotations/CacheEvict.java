package com.manage.annotations;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheEvict {

    String key();
    String fieldKey() ;
    int expireTime() default 3600;
}
