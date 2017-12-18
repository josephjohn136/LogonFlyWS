package com.bolster.config;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bolster.security.SecurityConstants;

import io.jsonwebtoken.Jwts;

@Component
public class TenantInterceptor extends HandlerInterceptorAdapter {

	@Value("X-TenantID")
	String tenantKey;

	@Value("public")
	String defaultTenant;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("======= Tenant Interceptor, Pre Handle ============");
		String tenant = "";
		String token = request.getHeader(SecurityConstants.HEADER_STRING);
        if (token != null) {
            // parse the token.
            tenant = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET.getBytes())
                    .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                    .getHeader()
                    .get(tenantKey).toString();
            
        }
		System.out.println("-----> Tenant Interceptor, tenant: " + tenant);
		
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
