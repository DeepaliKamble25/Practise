package com.layer.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.layer.model.Person;
import com.layer.repository.PersonRepo;

@SpringBootTest(classes=PersonServiceImplTest.class)
class PersonServiceImplTest {
	@Mock
	private PersonRepo repo;
	
	@InjectMocks
	private PersonServiceImpl service;

	@Test
	public void getAllPersontest() {
		List<Person> list=new ArrayList<>();
		list.add(new Person(102,"Rami","engineer"));
		list.add(new Person(102,"Sam","engineer"));
		
		when(repo.findAll()).thenReturn(list);
		List<Person> person = service.getAllPerson();

		int actualsize = person.size();

		int expectedsize = 2;

		assertEquals(expectedsize, actualsize);
	}
	
	@Test
	public void getPersonnameByIdtest() {
		
		List<Person> list=new ArrayList<>();
		list.add(new Person(101,"Rami","engineer"));
		list.add(new Person(102,"Sam","engineer"));
		
		int id=101;
		when(repo.findAll()).thenReturn(list);
		
		int actualResult = service.getPersonById(id).getId();
		
		int exceptedResult=101;
		assertEquals(exceptedResult, actualResult);	}
	
	@Test
	public void getPersonByname() {
		List<Person> list=new ArrayList<>();
		list.add(new Person(101,"Rami","engineer"));
		list.add(new Person(102,"Sam","engineer"));
		
		String str="Rami";
		when(repo.findAll()).thenReturn(list);
		
	 Person personByName = service.getPersonByName(str);
	 
	 String actualname = personByName.getName();
	 
	 assertEquals(str, actualname);
		
		
	}
	@Test
	public void createdata() {
		Person p=new Person(101,"Rami","engineer");	
		
		when(repo.save(p)).thenReturn(p);
		
		Person actualperson = service.createdata(p);
		
		assertEquals(p,actualperson);
		
	}
	@Test
	public void updatePersontest() {
		
		Person p=new Person(101,"Rami","engineer");	
		
		when(repo.save(p)).thenReturn(p);
		
		int id=101;
		
		Person updatePerson = service.updatePerson(p, id);
		
		assertEquals(p,updatePerson);
	}
	@Test
	public void deletePerson() {
		Person p=new Person(101,"Rami","engineer");
		
		service.deletePerson(p);
		
		verify(repo,times(1)).delete(p);
		
		
	}

}
