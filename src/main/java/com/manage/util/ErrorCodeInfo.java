package com.manage.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/26.
 */
public class ErrorCodeInfo {

    private static final Map<Integer, String> errorMsg = new HashMap<Integer, String>();


    public static final int SHIRO_ERROR = 000001;
    /****
     * 用户信息
     */
    public static final int USER_NULLPOINTER = 100001;
    public static final int USER_PASSWORD_ERROR = 100002;
    public static final int USER_LOGOUT_ERROR = 100003;


    /***
     * 菜单资源  100001 start
     */
    public static final int RESOURCE_NULLPOINTER = 200001;


    static {
        userRelevant();
        /**
         * 菜单
         */
        errorMsg.put(RESOURCE_NULLPOINTER, "找不到该资源");
    }

    /***
     * 用户相关错误信息
     */
    public static void userRelevant(){
        errorMsg.put(SHIRO_ERROR, "shiro未知错误");
        errorMsg.put(USER_NULLPOINTER, "用户不存在");
        errorMsg.put(USER_PASSWORD_ERROR, "用户名或密码错误");
        errorMsg.put(USER_LOGOUT_ERROR, "用户注销失败");
    }
    /**
     * 根据错误代码获取错误信息
     *
     * @param errorCode
     * @return
     */
    public static String getMsg(int errorCode) {
        return errorMsg.get(errorCode);
    }


    /***
     * 根据错误代码获取对应BaseReslut
     *
     * @param errorCode
     * @return
     */
    public static BaseResult getBaseResult(int errorCode) {
        BaseResult result = new BaseResult();
        result.setMsg(errorMsg.get(errorCode));
        result.setErrorCode(errorCode);
        return result;
    }
}
