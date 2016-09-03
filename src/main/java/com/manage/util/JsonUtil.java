package com.manage.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

public enum  JsonUtil {

    INSTANCE;

    /**
     * 字符串转java对象
     * @param jsonStr
     * @param clzz
     * @return
     */
    public Object  jsonStrTObject(String jsonStr,Class clzz){
       return  JSON.parseObject(jsonStr, clzz);
    }


    /***
     * 对象转json
     * @param o
     * @return
     */
    public String objectToJson(Object o){
       return  JSONObject.toJSONString(o);
    }

    /**
     * 字符串转list集合
     * @param json
     * @param o
     * @return
     */
    public List jsonStrToList(String json, Object o) {
        return JSON.parseArray(json, o.getClass());
    }
}
