package com.layer.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.layer.model.Person;
import com.layer.repository.PersonRepo;



@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	private PersonRepo repo;

	@Override
	public Person createdata(Person p) {
		Person person = repo.save(p);
		return person;
	}

	@Override
	public List<Person> getAllPerson() {
		List<Person> list = repo.findAll();
		return list;
	}

	@Override
	public Person getPersonById(int id) {
		
		List<Person> findAll = this.repo.findAll();
		
		Person p=null;
		for(Person person:findAll) {
			
			if(person.getId()==id) 
				p = person;
		
		}
		return p;
		
	}

	@Override
	public Person getPersonByName(String name) {
		List<Person> findAll = this.repo.findAll();
		Person p=null;
		for(Person p1:findAll)
			 {
				if (p1.getName()==(name))
				
					p1=p;
					System.out.println(p);
			
			}
		
		return p;
		}

	@Override
	public Person updatePerson(Person p, int id) {
		p.setId(id);
		Person save = this.repo.save(p);
		return save ;
	}

	@Override
	public void deletePerson(Person p) {
		this.repo.delete(p);
		
	}

	

}
