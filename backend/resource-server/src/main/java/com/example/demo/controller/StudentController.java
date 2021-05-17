package com.example.demo.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.CalificacionEntity.Revisada;
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
	@GetMapping("/{mail}/subjects")
	@PreAuthorize("hasRole('student') or hasRole('secretary')")
	public ResponseEntity getSubjectsById(@PathVariable(name = "mail") String dni) {

		GetSubjectsForStudent response = studentService.getSubjects(dni);

		if (Objects.isNull(response)) {

			return ResponseEntity.notFound().build();

		}

		return ResponseEntity.ok(response);
	}

	@SuppressWarnings("rawtypes")
	@PatchMapping("/{mail_student}/subjects/{codeSubject}/teachers/{mail_teacher}")
	@PreAuthorize("hasRole('student')")
	public ResponseEntity updateRevisada(@PathVariable(name = "mail_student") String mail_student,
			@PathVariable(name = "codeSubject") String id, @PathVariable(name = "mail_teacher") String mail_teacher,
			@RequestBody PutRevision putRevision) {

		calificationService.updateRevision(putRevision, mail_student, mail_teacher, id);
		return ResponseEntity.ok().build();

	}
}