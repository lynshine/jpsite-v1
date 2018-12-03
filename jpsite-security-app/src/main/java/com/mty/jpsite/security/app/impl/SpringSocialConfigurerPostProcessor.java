package com.mty.jpsite.security.app.impl;

import com.mty.jpsite.security.core.config.JpSpringSocialConfigurer;
import com.mty.jpsite.security.core.properties.SecurityConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	/**
	 * appd第三方用户注册
	 * @param bean
	 * @param beanName
	 * @return
	 * @throws BeansException
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		/**
		 * 强制修改注册页面
		 */
		if(StringUtils.equals(beanName, "jpSpringSocialConfigurer")){
			JpSpringSocialConfigurer config = (JpSpringSocialConfigurer)bean;
			config.signupUrl(SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL);
			return config;
		}
		return bean;
	}

}
