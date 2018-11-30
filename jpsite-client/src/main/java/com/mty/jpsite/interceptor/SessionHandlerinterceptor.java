//package com.mty.jpsite.interceptor;
//
//import com.mty.jpsite.domain.User;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.lang.Nullable;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * 自定义登录拦截器
// * 如果使用了 Spring security 则不要这种方式了
// * Created by jiangpeng on 2018/10/24.
// */
//public class SessionHandlerinterceptor implements HandlerInterceptor {
//    private Logger logger = LoggerFactory.getLogger(SessionHandlerinterceptor.class);
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null) {
////            response.sendRedirect("/login.html");  跳转到登录controller后set session
//            System.out.println("用户没有登陆");
//            return false;
//        }
//        System.out.println("用户登陆成功");
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//                           @Nullable ModelAndView modelAndView) throws Exception {
//        //Controller方法处理完毕后，调用此方法
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
//                                @Nullable Exception ex) throws Exception {
//        //页面渲染完毕后调用此方法，通常用来清除某些资源，类似Java语法的finally
//    }
//}
