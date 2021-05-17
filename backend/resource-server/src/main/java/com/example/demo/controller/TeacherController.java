package com.example.demo.controller;

import java.io.IOException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.CalificacionEntity.Revisada;
import com.example.demo.model.request.PostCSV;
import com.example.demo.model.request.PutRevision;
import com.example.demo.model.response.GetAllStudentsForTeacher;
import com.example.demo.model.response.GetSubjectsForTeacher;
import com.example.demo.service.CalificationService;
import com.example.demo.service.TeacherService;

@RestController
@RequestMapping(path = "/teachers")
@CrossOrigin(origins = "*")
public class TeacherController {
	@Autowired
	private TeacherService teacherService;

	@Autowired
	private CalificationService calificationService;
//Enviar acta para almacenarla y recibir desde secretaria
	@SuppressWarnings("rawtypes")
	@PostMapping("/{mail}/actas")
    @PreAuthorize("hasRole('teacher')")
	public ResponseEntity enviarActa(@RequestParam("file") MultipartFile file, @PathVariable("mail") String mail) throws IOException {
		
		teacherService.enviarActa(file, mail);
		
		return ResponseEntity.ok().build();
		
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/{mail}")
    @PreAuthorize("hasRole('teacher')")
	public ResponseEntity getSubjectsById(@PathVariable(name = "mail") String dni) {

		GetSubjectsForTeacher response = teacherService.getSubjects(dni);

		if (Objects.isNull(response)) {

			return ResponseEntity.notFound().build();

		}

		return ResponseEntity.ok(response);
	}

	// Devuelve todos los alumnos de un profesor(dni) y sus alumnos por
	// asignatura(id)
	@SuppressWarnings("rawtypes")
	@GetMapping("/{mail}/subjects/{id}/students")
    @PreAuthorize("hasRole('teacher')")
	public ResponseEntity getStudentsById(@PathVariable(name = "mail") String mail, @PathVariable(name = "id") String id,
			@RequestParam(name = "revision", required = false) Revisada revision) {

		GetAllStudentsForTeacher response = teacherService.getStudents(mail, id, revision);

		if (Objects.isNull(response)) {

			return ResponseEntity.notFound().build();

		}

		return ResponseEntity.ok(response);
	}

	// Debe enviar un correo al alumno al que ha actualizado la nota
	@SuppressWarnings("rawtypes")
	@PatchMapping("/{dni_teacher}/subjects/{id}/students/{dni_student}")
    @PreAuthorize("hasRole('teacher')")
	public ResponseEntity updateRevisada(@PathVariable(name = "dni_student") String dni_student,
			@PathVariable(name = "id") String id, @PathVariable(name = "dni_teacher") String dni_teacher,
			@RequestBody PutRevision putCalificacion) {

		calificationService.updateCalificacion(putCalificacion, dni_student, dni_teacher, id);
		HttpHeaders responseHeaders = new HttpHeaders();
		    responseHeaders.set("Access-Control-Allow-Origin "," "); 
		    		//"Access-Control-Allow-Credentials: true" );
		return ResponseEntity.ok()
			   //  .headers(responseHeaders)
			     
			      .body(putCalificacion);
		//return ResponseEntity.ok().build();
	}

	// Actualizar nota de alumnos a través de un csv
	@SuppressWarnings("rawtypes")
	@PostMapping("/{dni_teacher}/allStudents")
    @PreAuthorize("hasRole('teacher')")
	public ResponseEntity upCSV(@PathVariable(name = "dni_teacher") String dniTeacher, @RequestBody PostCSV csv) {

		calificationService.postCSV(csv, dniTeacher);

		
		return ResponseEntity.ok().build();

	}
}
