package com.ant.core.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericUtil {

    private GenericUtil() {
    }

    public static Type getGenericType(Object obj) {
        Type type = obj.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] types = parameterizedType.getActualTypeArguments();
            if (types.length > 0) {
                return types[0];
            }
        }
        return null;
    }
}