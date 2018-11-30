package com.mty.jpsite.controller.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jiangpeng on 2018/11/5.
 */
@RestController
public class ApolloController {
    @Value("${myConf:test}")
    private String myConf;
    @Value("${jpsiteConf:test}")
    private String jpsiteConf;

    @RequestMapping("/getMyconf")
    public String getMyconf() {
        return myConf;
    }

    @RequestMapping("/getJpsiteConf")
    public String getJpsiteConf() {
        return jpsiteConf;
    }

    @RequestMapping("/config")
    public String getConfig() {
        Config config = ConfigService.getAppConfig(); //config instance is singleton for each namespace and is never null
        String someKey = "myConf";
        String someDefaultValue = "jp_default_123";
        String value = config.getProperty(someKey, someDefaultValue);

        return value;
    }
}
