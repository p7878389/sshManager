package com.manage.annotations;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2016/6/28.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cacheable {

    String key();
    String fieldKey() ;
    int expireTime() default 1800;
}
