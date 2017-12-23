package com.bolster;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.bolster.controller.EmployeeController;
import com.bolster.model.Employee;
import com.bolster.model.Tenant;
import com.bolster.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mock;

	@MockBean
	private EmployeeRepository empRepository;

	@Autowired
	EmployeeController uc;
	
	@Test
	public void testAddUser() throws Exception {
		Employee user = new Employee();
		user.setFirstName("Mathew");
		user.setLastName("Zack");
		user.setEmail("mathew@test.com");
		user.setPhoneNumber("23452354");
		user.setUserName("mathew01");
		user.setPassword("mathew01");
		user.setAddress("10 College Pl, Wollongong");
		Tenant tenant = new Tenant();
		tenant.setId(1);
		tenant.setTenantName("bolster");
		user.setTenant(tenant);
		String userJson = toJson(user);

		System.out.println(userJson);
		
		//MvcResult result = mock.perform(post("/emp").content(userJson).contentType(MediaType.APPLICATION_JSON))
		//		.andExpect(status().isOk()).andReturn();
		uc.signUp(user);
		System.out.println("test111");
		//MockHttpServletResponse res = result.getResponse();
		//System.out.println(res.toString());
	}

	private String toJson(Object r) throws Exception {
		ObjectMapper map = new ObjectMapper();
		return map.writeValueAsString(r);
	}
}
