//package com.mty.jpsite.controller.zookeeper;
//
//import org.apache.curator.framework.CuratorFramework;
//import org.apache.curator.framework.recipes.locks.InterProcessMutex;
//import org.apache.curator.x.discovery.ServiceDiscovery;
//import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
//import org.apache.curator.x.discovery.ServiceInstance;
//import org.apache.curator.x.discovery.ServiceInstanceBuilder;
//import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by jiangpeng on 2018/11/22.
// */
//@Service
//public class OrderServiceimpl {
//    private Logger logger = LoggerFactory.getLogger(OrderServiceimpl.class);
//    @Autowired
//    CuratorFramework zkClient;
//
//    String lockPath = "/lock/order";
//
//    // 处理某种订单类型，比如type值是 book
//    // todo 修改成注解方式
//    public void makeOrderType(String type) {
//        String path = lockPath + "/" + type;
//        logger.info("===>try do com.mty.jpsite.server.job for " + type);
//        try {
//            InterProcessMutex lock = new InterProcessMutex(zkClient, path);
//            if (lock.acquire(10, TimeUnit.HOURS)) {   // 获取锁
//                try {
//                    // 模拟耗时5秒的業務
//                    Thread.sleep(1000 * 5);
//                    logger.info("====>do com.mty.jpsite.server.job {} done", type);
//                } finally {
//                    lock.release();   //释放锁
//                }
//            }
//        } catch (Exception e) {
//            // zk 异常
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 注册服务
//     *
//     * @param zkClient
//     * @throws Exception
//     */
//    protected void registerServer(CuratorFramework zkClient) throws Exception {
//        // 构造一个服务描述
//        ServiceInstanceBuilder<Map> service = ServiceInstance.builder();
//        service.address("192.168.1.100");
//        service.port(8080);
//        service.name("book");
//        Map config = new HashMap();
//        config.put("url", "api/v3/book");
//        service.payload(config);
//
//        ServiceInstance<Map> instance = service.build();
//        // 用于注册服务
//        ServiceDiscovery<Map> serviceDiscovery = ServiceDiscoveryBuilder.builder(Map.class)
//                .client(zkClient).serializer(new JsonInstanceSerializer<Map>(Map.class))
//                .basePath("/service").build();
//        //服务注销
//        serviceDiscovery.registerService(instance);
//        serviceDiscovery.start();
//    }
//
//    /**
//     * 获取服务
//     */
//    protected ServiceInstance<Map> findService(CuratorFramework zkClient, String serviceName) throws Exception {
//        ServiceDiscovery<Map> serviceDiscovery = ServiceDiscoveryBuilder.builder(Map.class)
//                .client(zkClient).serializer(new JsonInstanceSerializer<Map>(Map.class))
//                .basePath("/service").build();
//
//        serviceDiscovery.start();
//        //取得当前 服务
//        Collection<ServiceInstance<Map>> all = serviceDiscovery.queryForInstances(serviceName);
//        if (all.size() == 0) {
//            return null;
//        } else {
//            // 取得第一个服务
//            ServiceInstance<Map> service = new ArrayList<ServiceInstance<Map>>(all).get(0);
//            logger.info("====>{}", service.getAddress());
//            logger.info("====>{}", service.getPayload());
//            return service;
//        }
//
//    }
//}
