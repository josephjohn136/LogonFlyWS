package com.bolster;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bolster.controller.UserController;
import com.bolster.model.Address;
import com.bolster.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogonFlyApplicationTests {

	@Autowired
	UserController uc;
	
	private MockMvc mock;
	
	@Test
	public void contextLoads() {
	}

	@Test
	public void addUser() throws Exception{
		User user = new User();
		user.setFirstName("Mathew");
		user.setLastName("Zack");
		user.setEmail("mathew@test.com");
		user.setPhoneNumber("23452354");
		user.setUserName("mathew01");
		user.setPassword("mathew01");
		Address add = new Address();
		add.setAddressLine1("10 College Pl");
		add.setCity("Wollongong");
		add.setState("NSW");
		add.setCountry("Australia");
		add.setPostcode("2500");
		user.setAddress(add);
		
		//mock.perform(post("/user", user)).andExpect(status().isOk());
			
		uc.addUser(user);
	}
}
