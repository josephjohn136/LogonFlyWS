package com.bolster;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bolster.controller.EmployeeController;
import com.bolster.model.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogonFlyApplicationTests {

	@Autowired
	EmployeeController uc;
	
	private MockMvc mock;
	
	@Test
	public void contextLoads() {
	}

	@Test
	public void addUser() throws Exception{
		Employee user = new Employee();
		user.setFirstName("Mathew");
		user.setLastName("Zack");
		user.setEmail("mathew@test.com");
		user.setPhoneNumber("23452354");
		user.setUserName("mathew01");
		user.setPassword("mathew01");
		user.setAddress("10 College Pl, Wollongong");
		
		//mock.perform(post("/user", user)).andExpect(status().isOk());
			
		uc.signUp(user);
	}
}
