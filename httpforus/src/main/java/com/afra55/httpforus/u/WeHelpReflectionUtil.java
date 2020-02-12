package com.afra55.httpforus.u;

import android.text.TextUtils;


import com.afra55.httpforus.WeHelpLog;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Afra55
 * @date 2018/4/2
 * A smile is the best business card.
 * <p>
 * 反射工具类
 * 通过反射获得对应函数功能
 */
public class WeHelpReflectionUtil {

    /**
     * 通过类对象，运行指定方法
     *
     * @param obj        类对象
     * @param methodName 方法名
     * @param params     参数值
     * @return 失败返回null
     */
    public static Object invokeMethod(Object obj, String methodName, Object[] params) {
        if (obj == null || TextUtils.isEmpty(methodName)) {
            return null;
        }

        Class<?> clazz = obj.getClass();
        try {
            Class<?>[] paramTypes = null;
            if (params != null) {
                paramTypes = new Class[params.length];
                for (int i = 0; i < params.length; ++i) {
                    paramTypes[i] = params[i].getClass();
                }
            }
            Method method = clazz.getMethod(methodName, paramTypes);
            method.setAccessible(true);
            return method.invoke(obj, params);
        } catch (NoSuchMethodException e) {
            WeHelpLog.i( "method " + methodName + " not found in " + obj.getClass().getName(), "rrr");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过类对象，运行指定方法
     *
     * @param obj        类对象
     * @param methodName 方法名
     * @param params     参数值
     * @return 失败返回null
     */
    public static Object invokeDeclaredMethod(Object obj, String methodName, Object[] params) {
        if (obj == null || TextUtils.isEmpty(methodName)) {
            return null;
        }

        Class<?> clazz = obj.getClass();
        try {
            Class<?>[] paramTypes = null;
            if (params != null) {
                paramTypes = new Class[params.length];
                for (int i = 0; i < params.length; ++i) {
                    paramTypes[i] = params[i].getClass();
                }
            }
            Method method = clazz.getDeclaredMethod(methodName, paramTypes);
            method.setAccessible(true);
            return method.invoke(obj, params);
        } catch (NoSuchMethodException e) {
            WeHelpLog.i("method " + methodName + " not found in " + obj.getClass().getName(), "rrr" );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getFieldValue(Object obj, String fieldName) {
        if (obj == null || TextUtils.isEmpty(fieldName)) {
            return null;
        }

        Class<?> clazz = obj.getClass();
        while (clazz != Object.class) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field.get(obj);
            } catch (Exception e) {
            }
            clazz = clazz.getSuperclass();
        }
        WeHelpLog.e("get field " + fieldName + " not found in " + obj.getClass().getName(), "rrr" );
        return null;
    }

    public static void setFieldValue(Object obj, String fieldName, Object value) {
        if (obj == null || TextUtils.isEmpty(fieldName)) {
            return;
        }

        Class<?> clazz = obj.getClass();
        while (clazz != Object.class) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(obj, value);
                return;
            } catch (Exception e) {
            }
            clazz = clazz.getSuperclass();
        }
        WeHelpLog.e("set field " + fieldName + " not found in " + obj.getClass().getName(), "rrr" );
    }

    /**
     * 通过类全路径，运行指定静态方法
     *
     * @param obj        类全路径
     * @param methodName 方法名
     * @param params     参数值
     * @return 失败返回null
     */
    public static Object invokeStaticMethod(String obj, String methodName, Object[] params, Class<?>... parameterTypes) {
        if (obj == null || TextUtils.isEmpty(methodName)) {
            return null;
        }

        try {
            Class<?> clazz = Class.forName(obj);
            Method method = clazz.getMethod(methodName, parameterTypes);
            method.setAccessible(true);
            return method.invoke(null, params);
        } catch (NoSuchMethodException e) {
            WeHelpLog.i("static method " + methodName + " not found in " + obj.getClass().getName(), "rrr" );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static SharedPreferences invokeSp(String name) {
//        try {
//            return  (SharedPreferences) ReflectionUtil.invokeStaticMethod("com.sp.cons.SpConsForKernel", "getSp", new Object[]{name}, String.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}