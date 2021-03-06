package com.bolster.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Component
public class HeaderTenantIdentifierResolver implements CurrentTenantIdentifierResolver{

    @Value("X-TenantID")
    String tenantKey;

    @Value("public")
    String defaultTenant;

    @Override
    public String resolveCurrentTenantIdentifier() {
       
    	String tenantId = TenantContext.getCurrentTenant();
    	System.out.println("HeaderTenantIdentifierResolver, tenantId: " + tenantId);
        if (tenantId != null) {
            return tenantId;
        }
        return defaultTenant;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
