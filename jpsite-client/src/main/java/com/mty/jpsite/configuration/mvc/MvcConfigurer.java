//package com.mty.jpsite.configuration.mvc;
//
//import com.mty.jpsite.interceptor.SessionHandlerinterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * 自定义mvc配置，实现WebMvcConfigurer
// * Created by jiangpeng on 2018/10/24.
// */
//@Configuration
//public class MvcConfigurer implements WebMvcConfigurer {
//
//    /**
//     * 如果requestMapping是 /admin/** 则进行session拦截判断
//     * 如果不使用spring security可以用这种方式做权限判断
//     * @param registry
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new SessionHandlerinterceptor()).addPathPatterns("/admin/**");
//    }
//}