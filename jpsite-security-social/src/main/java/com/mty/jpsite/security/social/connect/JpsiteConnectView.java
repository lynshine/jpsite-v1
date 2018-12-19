package com.mty.jpsite.security.social.connect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 */
@Component("connect/status")
public class JpsiteConnectView extends AbstractView {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {

        /** key是微信或qq ， list是否有连接 userConnection表*/
        Map<String, List<Connection<?>>> connection = (Map<String, List<Connection<?>>>) model.get("connectionMap");
        Map<String, Boolean> result = new HashMap<String, Boolean>();

        for (String key : connection.keySet()) {
            result.put(key, CollectionUtils.isNotEmpty(connection.get(key)));
        }

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }

}
