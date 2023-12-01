package com.example.mypack.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.mypack.entity.Person;
import com.example.mypack.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;

	@PostMapping(path = "/addPerson")
	public ResponseEntity<Person> addPerson(@RequestBody Person person) {
		return ResponseEntity.status(HttpStatus.CREATED).body(personService.addPerson(person));
	}

	@GetMapping(path = "/getPerson/{personId}")
	public ResponseEntity<Person> getPersonById(@PathVariable String personId) {
		return ResponseEntity.status(HttpStatus.OK).body(personService.getPersonById(personId));
	}

	@GetMapping(path = "/getPerson")
	public ResponseEntity<List<Person>> getAllPerson() {
		return ResponseEntity.status(HttpStatus.OK).body(personService.getAllPersons());
	}

	@DeleteMapping(path = "/deletePerson")
	public ResponseEntity<Person> deletePerson(@RequestBody Person person) {
		return ResponseEntity.status(HttpStatus.OK).body(personService.deletePerson(person));
	}

}
