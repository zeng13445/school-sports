package com.formssi.zengzl.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Web相关配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	/**
	 * 添加拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/user/**");
//		registry.addInterceptor(getLoginInterceptor()).excludePathPatterns("/checkToken",
//				"/login");
	}

//	//解决拦截器内无法注入
//	@Bean
//	public HandlerInterceptorAdapter getLoginInterceptor(){
//		return new LoginInterceptor();
//	}
//	//解决拦截器内无法注入
//	@Bean
//	public HandlerInterceptorAdapter getAuthenticationInterceptor(){
//		return new AuthenticationInterceptor();
//	}

}
