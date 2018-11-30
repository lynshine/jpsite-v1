//package com.mty.jpsite.handler.com.mty.jpsite.server.job;
//
//import com.xxl.com.mty.jpsite.server.job.core.biz.model.ReturnT;
//import com.xxl.com.mty.jpsite.server.job.core.handler.IJobHandler;
//import com.xxl.com.mty.jpsite.server.job.core.handler.annotation.JobHandler;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@JobHandler(value = "memory-com.mty.jpsite.server.job")
//@Component
//public class MemoryJob extends IJobHandler {
//    private Logger logger = LoggerFactory.getLogger(MemoryJob.class);
////    private List<String> list = new ArrayList<>();
//
//    @Override
//    public ReturnT<String> execute(String param) throws Exception {
//        List<String> list = new ArrayList<>();
//        String a = System.currentTimeMillis() + "";
//        list.add(a);
//        logger.info("====> list.size: {}", list.size());
//        return SUCCESS;
//    }
//}
