package com.bolster.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

import com.bolster.model.AppUser;
import com.bolster.model.Employee;
import com.bolster.repository.EmployeeRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	EmployeeRepository empRespository;
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee applicationUser = empRespository.findByUserName(username);
        
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        AppUser user = new AppUser(applicationUser.getUserName(), applicationUser.getPassword(), emptyList());
        user.setTenant(String.valueOf(applicationUser.getTenant().getTenantName()));
        System.out.println("UserDetailsService (loadUserByUsername) -------> application user tenant : " + applicationUser.getTenant().getId());
        return user;
    }
}
