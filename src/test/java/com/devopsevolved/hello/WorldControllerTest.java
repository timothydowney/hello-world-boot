package com.devopsevolved.hello;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MockServletContext.class)
@WebAppConfiguration
public class WorldControllerTest {

	@Mock
	private World world;
	
	private MockMvc mvc;
	
	private WorldController controller;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		controller = new WorldController();
		controller.setWorld(world);
		
		mvc = MockMvcBuilders.standaloneSetup(controller)
				.build();		
	}
	
	@Test
	public void testSayHello() throws Exception {
		when(world.say("Hello")).thenReturn("Hello");
		
		mvc.perform(MockMvcRequestBuilders.get("/rest/hello")
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.message", is("Hello")));
	}

}
