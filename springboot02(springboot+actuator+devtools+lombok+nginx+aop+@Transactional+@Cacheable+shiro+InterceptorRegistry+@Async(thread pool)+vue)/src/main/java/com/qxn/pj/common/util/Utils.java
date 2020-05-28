package com.qxn.pj.common.util;

import java.lang.reflect.Field;

public class Utils {
	public static String testReflect(Object model) {
		StringBuilder result = new StringBuilder("[");
		Class<?> Model = model.getClass();
        for (Field declaredField : Model.getDeclaredFields()) {
            try {
                result
                        .append(declaredField.getName())
                        .append("=")
                        .append(declaredField.get(Model))
                        .append(",,");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        System.out.println(result);
        return result.substring(0, result.length() - 1) + "]";
	}
}
