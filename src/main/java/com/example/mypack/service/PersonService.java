package com.example.mypack.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.mypack.entity.Person;
import com.example.mypack.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository repository;

	public Person addPerson(Person person) {
		return this.repository.save(person);
	}

	public Person getPersonById(String personId) {
		return this.repository.findById(personId).get();
	}

	public List<Person> getAllPersons() {
		return this.repository.findAll();
	}

	public Person deletePerson(Person person) {
		this.repository.delete(person);
		return person;
	}
}
