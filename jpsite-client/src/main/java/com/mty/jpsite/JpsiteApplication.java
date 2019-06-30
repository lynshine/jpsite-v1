package com.mty.jpsite;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Created by jiangpeng on 2018/10/24.
 */
@EnableApolloConfig   //开启apollo配置中心
@SpringBootApplication
@EnableCaching  //打开缓存功能
@MapperScan("com.mty.jpsite.server.dao")
//@Import(SwaggerConfiguration.class)
//@EnableMySwagger
public class JpsiteApplication {

    public static void main(String args[]) {
        SpringApplication.run(JpsiteApplication.class, args);
    }
}
