//package com.mty.jpsite.handler.com.mty.jpsite.server.job;
//
//import com.ctrip.framework.apollo.Config;
//import com.ctrip.framework.apollo.ConfigService;
//import com.xxl.com.mty.jpsite.server.job.core.biz.model.ReturnT;
//import com.xxl.com.mty.jpsite.server.job.core.handler.IJobHandler;
//import com.xxl.com.mty.jpsite.server.job.core.handler.annotation.JobHandler;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@JobHandler(value = "apollo-com.mty.jpsite.server.job")
//@Component
//public class ApolloJob extends IJobHandler {
//    private Logger logger = LoggerFactory.getLogger(JpsiteJob.class);
//
////    @Value("${apolloConfig:test}")
////    String apolloConfig;
//
//    @Override
//    public ReturnT<String> execute(String param) throws Exception {
//        Config config = ConfigService.getAppConfig(); //config instance is singleton for each namespace and is never null
//        String someKey = "apolloConfig";
//        String someDefaultValue = "test";
//        String apolloConfig = config.getProperty(someKey, someDefaultValue);
//
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        logger.info("====>xxl-job传递参数：{}, 时间：{}, apollo配置：{}", param, df.format(new Date()), apolloConfig);
//        return SUCCESS;
//    }
//}
