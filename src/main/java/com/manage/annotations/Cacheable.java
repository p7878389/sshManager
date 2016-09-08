package com.manage.annotations;

import java.lang.annotation.*;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cacheable {

    String key();
    String fieldKey() ;
    int expireTime() default 1800;
}
