package com.example.demo.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.request.PutRevision;
import com.example.demo.model.response.GetSubjectsForStudent;
import com.example.demo.service.CalificationService;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping(path = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private CalificationService calificationService;

	@SuppressWarnings("rawtypes")
	@GetMapping("/{dni}/subjects")
	public ResponseEntity getSubjectsById(@PathVariable(name = "dni") String dni) {

		GetSubjectsForStudent response = studentService.getSubjects(dni);

		if (Objects.isNull(response)) {

			return ResponseEntity.notFound().build();

		}

		return ResponseEntity.ok(response);
	}

	@SuppressWarnings("rawtypes")
	@PatchMapping("/{dni_student}/subjects/{id}/teachers/{dni_teacher}")
	public ResponseEntity updateRevisada(@PathVariable(name = "dni_student") String dni_student,
			@PathVariable(name = "id") String id, @PathVariable(name = "dni_teacher") String dni_teacher,
			@RequestBody PutRevision putRevision) {

		calificationService.updateRevision(putRevision, dni_student, dni_teacher, id);
		return ResponseEntity.ok().build();

	}
}
