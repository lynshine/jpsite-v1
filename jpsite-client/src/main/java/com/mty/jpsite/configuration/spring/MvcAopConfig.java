package com.mty.jpsite.configuration.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * 自定义aop配置类实现
 */
@Configuration
/**把当前类标识为一个切面供容器读取*/
@Aspect
public class MvcAopConfig {
    private Logger logger = LoggerFactory.getLogger(MvcAopConfig.class);

    /**
     * 所有带有Controller注解在类都会先进入这里
     *
     * @param pjp 进行的连接点
     * @return Object
     * @throws Throwable
     */
    @Around("@within(org.springframework.stereotype.Controller)")
    public Object simpleAop(final ProceedingJoinPoint pjp) throws Throwable {
        try {
            Object[] args = pjp.getArgs();
            // 调用原有方法
            Object o = pjp.proceed();
            logger.info("调用方法: {}, 调用参数args: {}", o, Arrays.asList(args));
            return o;
        } catch (Throwable e) {
            throw e;
        }
    }
}
