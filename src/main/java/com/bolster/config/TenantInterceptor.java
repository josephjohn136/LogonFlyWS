package com.bolster.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bolster.security.SecurityConstants;

@Component
public class TenantInterceptor extends HandlerInterceptorAdapter {

	@Value("X-TenantID")
	String tenantKey;

	@Value("public")
	String defaultTenant;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = request.getHeader(SecurityConstants.HEADER_STRING);
		System.out.println("-----> token: " + token);
		String tenant = request.getHeader(tenantKey);
		
		if (tenant != null) {
			TenantContext.setCurrentTenant(tenant);
		} else {
			TenantContext.setCurrentTenant(defaultTenant);
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		TenantContext.clear();
	}
}
