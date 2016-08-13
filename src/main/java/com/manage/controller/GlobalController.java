package com.manage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局异常管理
 */
public class GlobalController {

    private final static Logger log= LoggerFactory.getLogger(GlobalController.class);

    @ExceptionHandler(Exception.class)
    //在Controller类中添加该注解方法即可(注意：添加到某个controller，只针对该controller起作用)
    public void exceptionHandler(Exception ex, HttpServletResponse response, HttpServletRequest request) throws IOException {
        log.error(ex.getMessage(), ex);
        if(ex.getClass() == NoSuchRequestHandlingMethodException.class){
            response.sendRedirect("/error/404.html");
        }else{
            response.sendRedirect("/error/500.html");
        }
    }

}
