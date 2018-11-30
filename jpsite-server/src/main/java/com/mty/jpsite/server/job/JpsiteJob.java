//package com.mty.jpsite.handler.com.mty.jpsite.server.job;
//
//import com.xxl.com.mty.jpsite.server.job.core.biz.model.ReturnT;
//import com.xxl.com.mty.jpsite.server.job.core.handler.IJobHandler;
//import com.xxl.com.mty.jpsite.server.job.core.handler.annotation.JobHandler;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * Created by jiangpeng on 2018/11/5.
// */
//@JobHandler(value = "jpsite-com.mty.jpsite.server.job")
//@Component
//public class JpsiteJob extends IJobHandler {
//    private Logger logger = LoggerFactory.getLogger(JpsiteJob.class);
//
//    @Override
//    public ReturnT<String> execute(String param) throws Exception {
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        logger.info("xxl-job传递参数：{}, 时间：{}", param, df.format(new Date()));
//        return SUCCESS;
//    }
//}