package com.manage.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by Administrator on 2016/6/27.
 */
public enum  SerializeUtils {
    INSTANCE;

    private final static Logger log = LoggerFactory.getLogger(SerializeUtils.class);

    public  byte[] serialize(Object obj) {
        if(obj==null){
            return new byte[0];
        }
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
            deserialize(bytes);
        } catch (IOException ex) {
            log.error("byteArray to Object error", ex);
        }
        return bytes;
    }

    public  Object deserialize(byte[] bytes) {
        if(bytes==null){
            return null;
        }
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            log.error("springRedisCache io error", ex);
        } catch (ClassNotFoundException ex) {
            log.error("Object to byteArray error", ex);
        }
        return obj;
    }

}
