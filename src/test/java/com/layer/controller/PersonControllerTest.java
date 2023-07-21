package com.layer.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.layer.model.Person;
import com.layer.service.PersonService;

@SpringBootTest(classes=(PersonControllerTest.class))
public class PersonControllerTest {
	
	@Mock
	private PersonService personService;
	
	@InjectMocks
	private PersonController personController;

	@Test
	public void createdatatest() {
		
		Person p=new Person(1,"Karuna","Mumbai");
		when(personService.createdata(p)).thenReturn(p);
		
		ResponseEntity<Person> createdata = personController.createdata(p);
		
		 HttpStatusCode actualstatusCode = createdata.getStatusCode();
		 System.out.println(actualstatusCode);
		 
		 Person body = createdata.getBody();
		String actualname =body.getName();
		System.out.println(actualname);
		
	    int id = body.getId();
	    System.out.println("actualid   :"+id);
	    
	    int exceptedid=1;
	    
		String exceptedname="Karuna";
		
		assertEquals(exceptedname,actualname);
		
		assertEquals(exceptedid,id);
	}
	@Test
	public void  geAllPerson() {
		
		List<Person> persons=new ArrayList();
		persons.add(new Person(1,"Karuna","Mumbai"));
		persons.add(new Person(2,"Dhanu","Jaipur"));
		when(personService.getAllPerson()).thenReturn(persons);
		 ResponseEntity<List<Person>> geAllPerson = personController.geAllPerson();
		
		 HttpStatusCode actualstatusCode = geAllPerson.getStatusCode();
		 System.out.println(actualstatusCode);
		 
		 List<Person> actualbody1 = geAllPerson.getBody();
		 List<Person> exceptedlist=persons;
		 
		 assertEquals(exceptedlist, actualbody1);
	}
	@Test
	public void getPersonnameById() {
		Person p=new Person(1,"Karuna","Mumbai");
		
		when(personService.getPersonById(1)).thenReturn(p);
	
		ResponseEntity<Person> personById = personController.getPersonById(1);
		
		 HttpStatusCode actualstatusCode = personById.getStatusCode();
		 System.out.println(actualstatusCode);
		 
		 Person actualdata = personById.getBody();
		 Person excepteddata=p;
		 assertEquals(excepteddata, actualdata );
		
	}
	@Test
	public void getPersonById() {
		
		
		
		
		
		
	}
	
	

}
