package com.layer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="person_info")
public class Person {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private int  id;
	
	@Column(name="person_name")
	private String name;
	
	
	@Column(name="person_about")
	private String about;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAbout() {
		return about;
	}


	public void setAbout(String about) {
		this.about = about;
	}


	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", about=" + about + "]";
	}


	public Person(int i, String name, String about) {
		super();
		this.id = i;
		this.name = name;
		this.about = about;
	}


	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	
	
	
	

}
