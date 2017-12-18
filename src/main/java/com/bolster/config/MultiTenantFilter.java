package com.bolster.config;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.bolster.common.Constants;
import com.bolster.security.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class MultiTenantFilter implements Filter {

	String[] defaultTenantUri = {"/emp"};

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		
		System.out.println("uri: " + uri);
		System.out.println("======= Tenant Interceptor, Pre Handle ============");

		
		if(Arrays.asList(defaultTenantUri).contains(uri)){
			TenantContext.setCurrentTenant("public");
		}else{
			String tenant = "";
			String token = req.getHeader(SecurityConstants.HEADER_STRING);
	        if (token != null) {
	            // parse the token.
	            Jws<Claims> claims = Jwts.parser()
	                    .setSigningKey(SecurityConstants.SECRET.getBytes())
	                    .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""));
	            tenant = claims.getBody().get(Constants.TENANT_ID).toString();
	        }
			
			if (tenant != null && !tenant.isEmpty()) {
				TenantContext.setCurrentTenant(tenant);
			} else {
				TenantContext.setCurrentTenant("public");
			}
		}
		
		
		
		System.out.println("====-----> Tenant Interceptor, tenant: " + TenantContext.getCurrentTenant());
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}
}
