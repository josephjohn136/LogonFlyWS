package com.bolster;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.bolster.controller.UserController;
import com.bolster.model.Address;
import com.bolster.model.User;
import com.bolster.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mock;

	@MockBean
	private UserRepository userRepository;

	@Test
	public void testAddUser() throws Exception {
		User user = new User();
		user.setFirstName("Mathew");
		user.setLastName("Zack");
		user.setEmail("mathew@test.com");
		user.setPhoneNumber("23452354");
		user.setUserName("mathew01");
		user.setPassword("mathew01");
		user.setDob("19-04-1985");
		Address add = new Address();
		add.setAddressLine1("10 College Pl");
		add.setCity("Wollongong");
		add.setState("NSW");
		add.setCountry("Australia");
		add.setPostcode("2500");
		user.setAddress(add);

		String userJson = toJson(user);

		System.out.println(userJson);
		
		MvcResult result = mock.perform(post("/user").content(userJson).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();

		System.out.println("test111");
		MockHttpServletResponse res = result.getResponse();
		System.out.println(res.toString());
	}

	private String toJson(Object r) throws Exception {
		ObjectMapper map = new ObjectMapper();
		return map.writeValueAsString(r);
	}
}
