package com.layer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.layer.exception.PersonNotFoundException;
import com.layer.model.Person;
import com.layer.service.PersonService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	@PostMapping("/person")
	public ResponseEntity<Person> createdata(@RequestBody Person p){
		
		if(p==null) {
			throw new PersonNotFoundException("Person not found");
		}else {
			
		
		Person person = service.createdata(p);
		return new  ResponseEntity<Person>(person,HttpStatus.CREATED);
		}
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Person>> geAllPerson()
	{
		
		List<Person> list = service.getAllPerson();
		return new ResponseEntity<List<Person>>(list,HttpStatus.FOUND);
		
	}
	
	@GetMapping("/person/{id}")
	public ResponseEntity<Person> getPersonById( @PathVariable int id)
	{
		
		Person person = service.getPersonById(id);
		
		return new ResponseEntity<Person> (person,HttpStatus.FOUND);
		
	}
	
	@GetMapping("/getByName")
	public ResponseEntity<Person> getPersonByName(@RequestParam  String name)
	{
		Person personByName = this.service.getPersonByName(name);
		
		return new ResponseEntity<Person>(personByName,HttpStatus.FOUND);
		
	}
	
	@PutMapping("/person/{id}")
	public ResponseEntity<Person> updatePerson(@RequestBody Person p,@PathVariable int id)
	{
		Person updatePerson = service.updatePerson(p, id);
		return new ResponseEntity<Person>(updatePerson,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/person")
	public void deletePerson(Person p) 
	{
		service.deletePerson(p);
	}

}
