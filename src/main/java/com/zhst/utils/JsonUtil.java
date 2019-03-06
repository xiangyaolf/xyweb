package com.zhst.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonUtil {

    final static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    public static String toJson(Object obj) {
        if (obj == null) return "";

        String str = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter();
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            str = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return str;
    }

    private static String toDefaultJson(Object obj) throws IllegalAccessException {
        if (obj == null) return "";
        getObjDefaultValue(obj);
        return toJson(obj);
    }

    /**
     * 获取obj对象的默认值
     * @param obj
     * @throws IllegalAccessException
     */
    public static void getObjDefaultValue(Object obj) throws IllegalAccessException {
        Class<?> objClass = obj.getClass();
        Field[] declaredFields = objClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String simpleName = declaredField.getType().getSimpleName();
            declaredField.setAccessible(true);
            if ("String".equals(simpleName)) {
                declaredField.set(obj, "");
            } else if ("Integer".equals(simpleName)) {
                declaredField.set(obj, 0);
            } else if ("Date".equals(simpleName)) {
                declaredField.set(obj, new Date());
            } else if ("Long".equals(simpleName)) {
                declaredField.set(obj, 0L);
            } else if ("Double".equals(simpleName)) {
                declaredField.set(obj, 0D);
            }
        }
    }

    public static Object convertToObj(String jsonStr, Class clazz){
        if (jsonStr != null &&  !"".equals(jsonStr.trim())){
            ObjectMapper mapper = new ObjectMapper();
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            try {
                Object obj = mapper.readValue(jsonStr,clazz);
                return obj;
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        return null;
    }
}
