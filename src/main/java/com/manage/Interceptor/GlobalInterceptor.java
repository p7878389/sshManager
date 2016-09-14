package com.manage.Interceptor;


import com.manage.controller.LoginController;
import com.manage.redis.RedisClient;
import com.manage.util.BaseResult;
import com.manage.util.ErrorCodeInfo;
import com.manage.util.JsonUtil;
import com.manage.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static com.manage.util.ErrorCodeInfo.USER_SESSION_INVALID;

/***
 * 拦截器  判断用户是否登录
 */
public class GlobalInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger( LoginController.class );

    @Autowired
    private RedisClient redisClient;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring( contextPath.length() );

        log.info( "requestUri:" + requestUri );
        log.info( "contextPath:" + contextPath );
        log.info( "url:" + url );

        Object sessionInfo = request.getSession().getAttribute( "admin" );
        if (sessionInfo == null || StringUtil.isNull( sessionInfo.toString() )) {
            if (request.getHeader( "x-requested-with" ) != null
                    && request.getHeader( "x-requested-with" ).equalsIgnoreCase( "XMLHttpRequest" )) {
                //是ajax请求，则返回个消息给前台
                PrintWriter printWriter = response.getWriter();
                BaseResult result = ErrorCodeInfo.INSTANCE.getBaseResult( ErrorCodeInfo.USER_SESSION_INVALID );
                printWriter.print( JsonUtil.INSTANCE.objectToJson( result ) );
                printWriter.flush();
                printWriter.close();
            } else {
                //不是ajax请求，则直接跳转页面
                response.sendRedirect( request.getContextPath() + "/admin/login.html" );
            }
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

    public RedisClient getRedisClient() {
        return redisClient;
    }

    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }
}
