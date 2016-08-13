package com.manage.util;


import net.sf.json.JSONObject;

/**
 * Created by Administrator on 2016/6/29.
 */
public class JsonUtil {

    /**
     * 字符串转对象
     * @param jsonStr
     * @param clzz
     * @return
     */
    public Object  jsonStrToObject(String jsonStr,Class clzz){
        JSONObject jsonObject=JSONObject.fromObject(jsonStr);
        return JSONObject.toBean(jsonObject,clzz);
    }
}
