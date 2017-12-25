package com.bolster.common;

import javax.servlet.http.HttpServletRequest;

import com.bolster.model.Tenant;
import com.bolster.security.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class Utils {

	public static Tenant getTenantFromToken(HttpServletRequest req){
		Tenant tenant = null;
		String token = req.getHeader(SecurityConstants.HEADER_STRING);
		System.out.println("Utils, Token :" + token);
        if (token != null) {
        	
            // parse the token.
        	Claims claim = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET.getBytes())
                    .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                    .getBody();
                 
        	tenant = new Tenant();
        	tenant.setId((Integer) claim.get(Constants.TENANT_ID));
        	tenant.setTenantName(claim.get(Constants.TENANT_NAME).toString());
        }
        System.out.println("Utils, TenantName: "+tenant.getTenantName());
        return tenant;
	}
}
