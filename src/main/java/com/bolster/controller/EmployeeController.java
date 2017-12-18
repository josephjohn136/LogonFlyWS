package com.bolster.controller;

/**
 * Reference https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/
 */
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bolster.model.Employee;
import com.bolster.repository.EmployeeRepository;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	EmployeeRepository empRespository;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;


	@RequestMapping(value = "/sign-up", method = RequestMethod.POST)
	public void signUp(@RequestBody Employee user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		System.out.println("password: " + user.getPassword());
		System.out.println("Add user..");
		System.out.println("Employee: " + user.toString());
		Employee _emp = empRespository.save(user);
		System.out.println("user id: " + _emp.getId());
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody List<Employee> getUsers() {
		List<Employee> users = new ArrayList<>();
		Iterable<Employee> iter = empRespository.findAll();
		iter.forEach(users::add);
		return users;
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody Employee getUser(@PathVariable Integer userId) {
		Employee user = empRespository.findOne(userId);
		return user;
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	public @ResponseBody void updateUser(@PathVariable Integer userId, @RequestBody Employee user) {
		Employee _emp = empRespository.findOne(userId);
		if (_emp != null) {
			empRespository.save(user);
		}
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteUser(@PathVariable Integer userId) {
		empRespository.delete(userId);
	}
}
