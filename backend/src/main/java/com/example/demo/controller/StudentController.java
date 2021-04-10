package com.example.demo.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.response.GetSubjectsForStudent;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping(path = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/{dni}/subjects")
	public ResponseEntity getSubjectsById(@PathVariable(name = "dni") String dni) {
		
		GetSubjectsForStudent response =  studentService.getSubjects(dni);
		
		if (Objects.isNull(response)) {
			
			return ResponseEntity.notFound().build();
			
		}
		
		return ResponseEntity.ok(response);
	}
}
