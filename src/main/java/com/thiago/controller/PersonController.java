package com.thiago.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.thiago.dao.PersonDAO;
import com.thiago.model.Person;
import com.thiago.helper.Message;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonDAO personDAO;
	
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody Person person) {
		if(personDAO.count(person.getId()) > 0)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("Person id already exists", HttpStatus.BAD_REQUEST.value()));
		if(personDAO.insert(person))
			return ResponseEntity.status(HttpStatus.OK).body(person);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(person);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> get(@PathVariable("id") long id) {
		if(personDAO.count(id) == 0)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("Person not found", HttpStatus.NOT_FOUND.value()));
		return ResponseEntity.status(HttpStatus.OK).body(personDAO.findById(id));
	}

	@GetMapping
	public ResponseEntity<Object> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(personDAO.findAll());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") long id) {
		if(personDAO.deleteById(id))
			return ResponseEntity.status(HttpStatus.OK).body(new Message("Person deleted successfully", HttpStatus.OK.value()));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("Person not found", HttpStatus.NOT_FOUND.value()));
	}
	
	
	@PutMapping
	public ResponseEntity<Object> update(@RequestBody Person person) {
		if(personDAO.count(person.getId()) == 0)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("Person not found", HttpStatus.NOT_FOUND.value()));
		if(personDAO.update(person))
			return ResponseEntity.status(HttpStatus.OK).body(person);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(person);
	}

	
	
}

