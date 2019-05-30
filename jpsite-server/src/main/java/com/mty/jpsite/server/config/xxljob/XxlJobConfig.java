package com.mty.jpsite.server.config.xxljob;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;


@Conditional(XxlJobConfig.XxlJobCondtion.class)  // 通过XxlJobCondtion判断是否实例化bean
/**分布式定时任务xxl-job配*/
@Configuration
/**执行指定包下的任务*/
@ComponentScan(basePackages = "com.mty.jpsite.server.job")
public class XxlJobConfig {
    private Logger logger = LoggerFactory.getLogger(XxlJobConfig.class);

    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Value("${xxl.job.executor.appname}")
    private String appName;

    @Value("${xxl.job.executor.ip}")
    private String ip;

    @Value("${xxl.job.executor.port}")
    private int port;

    @Value("${xxl.job.accessToken}")
    private String accessToken;

    @Value("${xxl.job.executor.logpath}")
    private String logPath;

    @Value("${xxl.job.executor.logretentiondays}")
    private int logRetentionDays;

    /**
     * 效果等同
     * <bean id="xxlJobExecutor" init-method="start" destroy-method="destroy" class="xxx.xxx.XxlJobSpringExecutor"/>
     */
    @Bean(initMethod = "start", destroyMethod = "destroy")
//    @Bean(initMethod = "start")
    public XxlJobSpringExecutor xxlJobExecutor() throws Exception {
        logger.info("====> XxlJobConfig XxlJobSpringExecutor init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setAppName(appName);
        xxlJobSpringExecutor.setIp(ip);
        xxlJobSpringExecutor.setPort(port);
        xxlJobSpringExecutor.setAccessToken(accessToken);
        xxlJobSpringExecutor.setLogPath(logPath);
        xxlJobSpringExecutor.setLogRetentionDays(logRetentionDays);
        return xxlJobSpringExecutor;
    }

    /**
     * 内部类，自定义Condtion，需要实现Condition接口
     */
    static class XxlJobCondtion implements Condition {
        @Override
        public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
            String value = conditionContext.getEnvironment().getProperty("jpsite.xxl.job.enable");
            return "true".equals(value);
        }
    }
}