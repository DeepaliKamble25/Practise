package com.layer.controller;


/*spring boot test act as a container and which spin the class
 * */
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.layer.model.Person;
import com.layer.service.PersonService;
@SpringBootTest(classes=PersonControllerTest2.class)
@AutoConfigureMockMvc
@ContextConfiguration
@ComponentScan(basePackages="com.layer")
class PersonControllerTest2 {
	@Mock
	private PersonService service;

	@InjectMocks
	private PersonController controller;
	
	@Autowired
	MockMvc mockMvc;//entry point for junit
	/*when we are calling any test with help of url then MockMvc is required*/
	@BeforeEach
	public void setup() 
	{
		/*mockmvcBuilder set up separate stand alone setup so that not to depend on others*/
	MockMvcBuilders.standaloneSetup(controller).build();
	
	}
	@Test
	public void createdata() throws Exception {
		Person person=new Person(1,"shama","Developer");
		
		when(service.createdata(person)).thenReturn(person);
		//this data are sending inthe from of object in json that we have to convert into string
		ObjectMapper mapper=new ObjectMapper();
		String personAsString = mapper.writeValueAsString(mapper);
		
		mockMvc.perform(post("/person")
				.content(personAsString)
				.contentType(MediaType.APPLICATION_JSON))
		         .andExpect(status().isCreated())//in placed of .isCreated if we take .isOK then test is failed
		         .andDo(print());
				
				}
  @Test
	public void geAllPerson() throws Exception {
	  List<Person> list=new ArrayList();
	  list.add(new Person(1,"shama","Developer"));
	  list.add(new Person(2,"rama","Developer"));
	  
	  when(service.getAllPerson()).thenReturn(list);
	 
	  mockMvc.perform(get("/getAll"))
	  .andExpect(status().isFound()).andDo(print());
	  
	}
  @Test
 public void getPersonById() throws Exception {
	  Person person=new Person(1,"Deepali","chemical");
	  
	  Integer id=1;
	  when(service.getPersonById(id)).thenReturn(person);
	  mockMvc.perform(get("/person/{id}",id))
	          .andExpect(status().isFound())
	          .andExpect(MockMvcResultMatchers.jsonPath(".id"). value(1))
	          .andExpect(MockMvcResultMatchers.jsonPath(".name"). value("Deepali"))
	          .andExpect(MockMvcResultMatchers.jsonPath(".about"). value("chemical"))
	          .andDo(print());
	  
	}
  @Test
  public void getPersonByName () throws Exception {
 	  Person person=new Person(1,"Deepali","chemical");
 	  
 	  String name="Deepali";
 	  when(service.getPersonByName(name)).thenReturn(person);
 	  mockMvc.perform(get("/person/name").param("name", "Deepali"))
 	          .andExpect(status().isFound())
 	          .andExpect(MockMvcResultMatchers.jsonPath(".id"). value(1))
 	          .andExpect(MockMvcResultMatchers.jsonPath(".name"). value("Deepali"))
 	          .andExpect(MockMvcResultMatchers.jsonPath(".about"). value("chemical"))
 	          .andDo(print());
 	  
 	}
}
