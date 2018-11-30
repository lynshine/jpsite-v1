package com.mty.jpsite.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 公共工具类
 * Created by jiangpeng on 2018/10/26.
 */
public class CommonUtil {
    @Autowired
    static ObjectMapper objectMapper;

    /**
     * 返回指定的集合对象类型如：List<User>/Set<Role>
     * @param collectionClass  集合类型
     * @param elementClass  元素类型
     * @return JavaType
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClass) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClass);
    }

}