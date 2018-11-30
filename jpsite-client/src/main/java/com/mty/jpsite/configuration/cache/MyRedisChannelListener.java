package com.mty.jpsite.configuration.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.io.UnsupportedEncodingException;

public class MyRedisChannelListener implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(MyRedisChannelListener.class);

    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] channal = message.getChannel();
        byte[] bs = message.getBody();
        try {
            String content = new String(bs, "UTF-8");
            String p = new String(channal, "UTF-8");
            logger.info("====>MyRedisChannelListener get {} form {}", content, p);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
