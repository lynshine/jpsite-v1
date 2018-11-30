package com.mty.jpsite.controller.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangpeng on 2018/11/1.
 */
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/setget.html")
    public Map<String, Object> env(@RequestParam(value = "param", required = false, defaultValue = "test") String param) {
        Map<String, Object> map = new HashMap<>();
        redisTemplate.opsForValue().set("testenv", param);
        redisTemplate.opsForList().leftPush("platform:message", "hello,jiangpeng");
        redisTemplate.opsForHash().put("cache", "num", "123");
        String v1 = redisTemplate.opsForValue().get("testenv");
        List<String> v2 = redisTemplate.opsForList().range("platform:message", 0, -1);
        String v3 = (String) redisTemplate.opsForHash().get("cache", "num");

        map.put("v1", v1);
        map.put("v2", v2);
        map.put("v3", v3);

        return map;
    }

    @GetMapping("/boundKey")
    public String boundKey() {
        BoundListOperations operations = redisTemplate.boundListOps("somekey");
        operations.leftPush("a");
        operations.leftPush("b");
        return String.valueOf(operations.size());
    }

    @GetMapping("/connectionset.html")
    @ResponseBody
    public String connectionSet(final String key, final String value) throws Exception {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key.getBytes(), value.getBytes());
                return null;
            }
        });
        return "success";
    }

    @GetMapping("/pub")
    public void pub() {
        redisTemplate.convertAndSend("news", "新闻开始了");
    }
}
