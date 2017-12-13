package com.bolster;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.bolster.config.MultiTenantFilter;

@SpringBootApplication
public class LogonFlyApplication {

	@Autowired
    AutowireCapableBeanFactory beanFactory;
	
	public static void main(String[] args) {
		SpringApplication.run(LogonFlyApplication.class, args);
	}
	
	@Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        Filter tenantFilter = new MultiTenantFilter();
        beanFactory.autowireBean(tenantFilter);
        registration.setFilter(tenantFilter);
        registration.addUrlPatterns("/*");
        return registration;
    }
}
