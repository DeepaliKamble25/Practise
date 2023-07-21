package com.layer.service;

import java.util.List;

import com.layer.model.Person;

public interface PersonService {

	
	Person createdata(Person p);
	
	List<Person> getAllPerson();
	
	Person  getPersonById(int id);
	
	Person getPersonByName(String personname);
	
	Person updatePerson(Person p,int id);
	
	void deletePerson(Person p);
	
	
	
	
}
