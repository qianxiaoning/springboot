package com.company.springbootquickstart01.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    /**
     * 对象转json串
     */
    public static String toJSON(Object target) {
        String result = null;
        try {
            result = MAPPER.writeValueAsString(target);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }
    /**
     * json串转对象
     * @throws Exception
     */
    public static <T> T toObject(String json,Class<T> targetClass){
        T object = null;
        try {
            object = MAPPER.readValue(json, targetClass);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return object;
    }
}
