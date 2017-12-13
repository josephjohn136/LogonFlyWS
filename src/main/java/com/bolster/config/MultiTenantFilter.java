package com.bolster.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;

public class MultiTenantFilter implements Filter {

	@Value("X-TenantID")
	String tenantKey;

	@Value("public")
	String defaultTenant;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String tenant = req.getHeader(tenantKey);

		if (tenant != null) {
			req.setAttribute(tenantKey, tenant);
		} else {
			req.setAttribute(tenantKey, defaultTenant);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}
}
