package com.mty.jpsite.configuration.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * ResponseBody注解的json返回解析实现自定义
 */
@Configuration
public class JacksonConf {
    /**
     * 指定日期返回格式
     *
     * @return
     */
    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒"));
        return objectMapper;
    }
}
