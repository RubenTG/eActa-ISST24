package com.example.demo.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.response.GetSubjectsByIdResponse;
import com.example.demo.service.SubjectService;

@RestController
@RequestMapping(path = "/subjects", produces = MediaType.APPLICATION_JSON_VALUE)
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/{code}")
	public ResponseEntity getSubjectsById(@PathVariable(name = "code") String id) {
		
		GetSubjectsByIdResponse response =  subjectService.getSubjectsById(id);
		
		if (Objects.isNull(response)) {
			
			return ResponseEntity.notFound().build();
			
		}
		
		return ResponseEntity.ok(response);
	}
	

}
