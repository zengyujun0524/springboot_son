package com.example.springboot_son.utils;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * @author 曾俞钧
 * @version 1.0
 * @date 2020/4/13 5:05 下午
 */
public class ObjectUtils{
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null) return true;
        else if (obj instanceof CharSequence) return ((CharSequence) obj).length() == 0;
        else if (obj instanceof Collection) return ((Collection) obj).isEmpty();
        else if (obj instanceof Map) return ((Map) obj).isEmpty();
        else if (obj.getClass().isArray()) return Array.getLength(obj) == 0;

        return false;
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

}
