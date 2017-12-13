package com.bolster.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bolster.model.User;
import com.bolster.repository.UserRepository;


@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRespository;
	
	@RequestMapping(method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON)
	public @ResponseBody List<User> getUsers() {
		List<User> users = new ArrayList<>();
		Iterable<User> iter = userRespository.findAll();
		iter.forEach(users::add);
		return users;
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON)
	public @ResponseBody User getUser(@PathVariable Integer userId){
		User user = userRespository.findOne(userId);
		return user;
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
	public @ResponseBody Integer addUser(@RequestBody User user){
		System.out.println("Add user..");
		System.out.println("User: " + user.toString());
		 User _user = userRespository.save(user);
		 System.out.println("user id: " + _user.getUserId());
		 return _user.getUserId();
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	public @ResponseBody void updateUser(@PathVariable Integer userId, @RequestBody User user){
		 User _user = userRespository.findOne(userId);
		 if(_user != null){
			 userRespository.save(user);
		 }	 
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteUser(@PathVariable Integer userId){
		 userRespository.delete(userId);
	}
}
