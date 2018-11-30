//package com.mty.jpsite.controller.zookeeper;
//
//import com.mty.jpsite.annotation.AcquireZk;
//import org.apache.curator.framework.CuratorFramework;
//import org.apache.zookeeper.CreateMode;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * Created by jiangpeng on 2018/11/22.
// */
//@RestController
//public class CuratorController {
//    private Logger logger = LoggerFactory.getLogger(CuratorController.class);
//    @Autowired
//    private CuratorFramework zkClient;
//
//    @GetMapping("/zkSet")
//    public void zkSet() throws Exception {
///**CuratorAPI是链式调用风格，遇到forPath接口就触发ZooKeeper调用*/
////        zkClient.create().forPath("/jpsite", new byte[0]);
//        zkClient.create().withMode(CreateMode.PERSISTENT).forPath("/jpsite/jp", new byte[0]);
//        zkClient.delete().forPath("/jpsite/jp");
//        zkClient.checkExists().watched().forPath("/jpsite");
//        byte[] bytes = zkClient.getData().forPath("/jpsite");
//        zkClient.setData().forPath("/jpsite", "setData".getBytes());
//        zkClient.getChildren().forPath("/jpsite");
//        testAnnotation("23");
//    }
//
//    @AcquireZk(path = "/lock/a", type = "book")
//    public void testAnnotation(String a) {
//        int b = 3;
//        System.out.printf(a);
//    }
//}
