package com.mty.jpsite.configuration.zookeeper;

import com.mty.jpsite.annotation.AcquireZk;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Zookeeper 加锁实现类，根据注解@acquireZk判断是否加zookeeper锁
 */
@Conditional(ZookeeperConf.ZookeeperCondtion.class)   // 通过ZookeeperCondtion判断是否实例化bean
@Aspect   //该注解标示该类为切面类
@Component  //注入依赖
public class AcquireZkAspect {
    private Logger logger = LoggerFactory.getLogger(AcquireZkAspect.class);

    @Autowired
    CuratorFramework zkClient;

    @Around("@annotation(acquireZk)")
    public void addLogSuccess(ProceedingJoinPoint pjp, AcquireZk acquireZk) throws Throwable {
        logger.info("====>获取目标类名: {}", pjp.getTarget().getClass().toString());
        logger.info("====>获取目标方法签名:{}", pjp.getSignature().toString());


        String path = acquireZk.path() + "/" + acquireZk.type();
        logger.info("===>zookeeper lock " + path);
        InterProcessMutex lock = new InterProcessMutex(zkClient, path);
        if (lock.acquire(10, TimeUnit.HOURS)) {   // 获取锁
            // 模拟耗时5秒的業務
            Thread.sleep(1000 * 5);
            logger.info("====>do com.mty.jpsite.server.job {} done", path);
            lock.release();   //释放锁
        }

        Object[] args = pjp.getArgs();//获取目标方法体参数
        // 调用原有方法
        Object o = pjp.proceed();
        logger.info("调用方法: {}, 调用参数args: {}", o, Arrays.asList(args));
    }
}
