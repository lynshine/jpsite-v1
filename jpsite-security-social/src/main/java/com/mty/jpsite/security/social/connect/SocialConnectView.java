package com.mty.jpsite.security.social.connect;

import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 绑定页面视图
 *
 * @author jiangpeng
 * @date 2018/12/19
 */
public class SocialConnectView extends AbstractView {
    final String CONNECTIONS = "connections";

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String msg;

        response.setContentType("text/html;charset=UTF-8");
        if (model.get(CONNECTIONS) == null) {
            msg = "解绑成功";
        } else {
            msg = "解绑成功";
        }

        response.sendRedirect("/message/" + msg);
    }
}
