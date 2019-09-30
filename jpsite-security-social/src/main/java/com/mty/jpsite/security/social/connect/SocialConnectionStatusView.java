package com.mty.jpsite.security.social.connect;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

/**
 * 查看帐号绑定情况
 * @author haha
 */
@Component("connect/status")
public class SocialConnectionStatusView  extends AbstractView {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        /* key是weixin或qq ， list是否有连接userConnection表*/
        Map<String, List<Connection<?>>> connection = (Map<String, List<Connection<?>>>) model.get("connectionMap");
        Map<String, Boolean> result = new HashMap<>(16);

        // value 为false表示没有绑定
        for (Map.Entry<String, List<Connection<?>>> entry: connection.entrySet()) {
            result.put(entry.getKey(), CollectionUtils.isNotEmpty(connection.get(entry.getKey())));
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }

}
