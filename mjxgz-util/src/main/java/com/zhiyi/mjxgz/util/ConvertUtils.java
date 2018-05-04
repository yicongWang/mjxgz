package com.zhiyi.mjxgz.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

/**
 * 转换工具类
 *
 * Created by Floki on 2017/6/28.
 */
public class ConvertUtils {
    /**
     * 将javabean实体类转为map类型，然后返回一个map类型的值
     *
     * @param obj javabean实体类
     * @param excludeField 需要排除的字段名称
     * @return map类型的值
     */
    public static Map<String, Object> beanToMap(Object obj, String[] excludeField) {
        Map<String, Object> params = new HashMap<>();
        if (null == obj) {
            return params;
        }

        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                if ("class".equals(name) || isExcludeField(excludeField, name)) {
                    continue;
                }

                Object value = propertyUtilsBean.getNestedProperty(obj, name);
                if (null != value) {
                    params.put(name, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }

    private static boolean isExcludeField(String[] excludeField, String field) {
        if (null == excludeField || excludeField.length == 0) {
            return false;
        }
        for (String name : excludeField) {
            if (name.equals(field)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 将map类型转换为javabean实体对象
     *
     * @param map map类型
     * @param obj javabean实体对象
     */
    public static void mapToBean(Map<String, Object> map, Object obj) {
        if (map == null || obj == null) {
            return;
        }

        try {
            BeanUtils.populate(obj, map);
        } catch (Exception e) {
            System.out.println("transMap2Bean2 Error " + e);
        }
    }
}
