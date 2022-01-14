package com.my.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class MapChangeUtil {
    
    
    public static Map<String,Object> json2map(String json){
        return JSONObject.parseObject(json);
    }
    
    public static JSONArray String2JSONArray(String json){
        return JSONObject.parseArray(json);
    }
    
    public static Map<String,Object> bean2Map(Object obj){
        if(obj == null){
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e);
        }
        return map;
    }
    
    public static Object map2Object(Map<String, Object> map, Class<?> clazz) throws Exception {
        if (map == null) {
            return null;
        } else {
            Object obj = null;
            try {
                obj = clazz.newInstance();
                Field[] fields = obj.getClass().getDeclaredFields();
                Field[] var4 = fields;
                int var5 = fields.length;
                
                for(int var6 = 0; var6 < var5; ++var6) {
                    Field field = var4[var6];
                    int mod = field.getModifiers();
                    if (!Modifier.isStatic(mod) && !Modifier.isFinal(mod)) {
                        field.setAccessible(true);
                        if (field.getType().toString().contains("Integer") && map.get(field.getName()) != null) {
                            field.set(obj, Integer.parseInt(map.get(field.getName()).toString()));
                        } else if (field.getType().toString().contains("String") && map.get(field.getName()) != null) {
                            field.set(obj, map.get(field.getName()) == null ? null : map.get(field.getName()).toString());
                        } else {
                            field.set(obj, map.get(field.getName()));
                        }
                    }
                }
            } catch (Exception var9) {
                var9.printStackTrace();
            }
            return obj;
        }
    }
    
}
