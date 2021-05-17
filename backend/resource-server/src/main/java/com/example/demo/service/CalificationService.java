package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.CalificacionEntity;
import com.example.demo.entity.CalificacionEntity.Revisada;
import com.example.demo.entity.CalificacionPK;
import com.example.demo.entity.StudentEntity;
import com.example.demo.entity.StudentSubjectEntity;
import com.example.demo.entity.SubjectEntity;
import com.example.demo.entity.TeacherEntity;
import com.example.demo.model.request.AllCalification;
import com.example.demo.model.request.PostCSV;
import com.example.demo.model.request.PutRevision;
import com.example.demo.repository.CalificacionRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.StudentSubjectRepository;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.repository.TeacherRepository;

@Service
public class CalificationService {

	@Autowired
	private CalificacionRepository calificacionRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentSubjectRepository studentSubjectRepository;

	public void updateRevision(PutRevision revisionDTO, String idStudent, String idTeacher, String idSubject) {

		CalificacionPK calificacionPk = new CalificacionPK();
		calificacionPk.setStudentId(idStudent);
		calificacionPk.setSubjectId(idSubject);
		calificacionPk.setTeacherId(idTeacher);
		CalificacionEntity calificacion = calificacionRepository.findById(calificacionPk)
				.orElseThrow(() -> new RuntimeException());

		if (calificacion.getRevisada().equals(Revisada.NO_REVISADA)) {
			calificacion.setRevisada(revisionDTO.getRevisada());
			calificacionRepository.save(calificacion);
			
		} else if (calificacion.getRevisada().equals(Revisada.PENDIENTE)) {

			 throw new ResponseStatusException(
			          HttpStatus.BAD_REQUEST, "Ya ha solicitado revisión");
			
		} else {
			throw new ResponseStatusException(  HttpStatus.BAD_REQUEST,"Su calificación ya ha sido revisada");
		}
	}

	public void updateCalificacion(PutRevision revisionDTO, String idStudent, String idTeacher, String idSubject) {

		CalificacionPK calificacionPk = new CalificacionPK();
		calificacionPk.setStudentId(idStudent);
		calificacionPk.setSubjectId(idSubject);
		calificacionPk.setTeacherId(idTeacher);
		CalificacionEntity calificacion = calificacionRepository.findById(calificacionPk)
				.orElseThrow(() -> new RuntimeException());

		calificacion.setNota(revisionDTO.getNota());
		calificacion.setDefinitiva(true);
		calificacion.setRevisada(Revisada.REVISADA);

		calificacionRepository.save(calificacion);
	}

	// Tnato la asignatura como el profesor han de estar dados de alta en la
	// plataforma
	@Transactional
	public void postCSV(PostCSV csvDTO, String idTeacher) {

		List<AllCalification> calificaciones = csvDTO.getCalifications();

		for (int i = 0; i < calificaciones.size(); i++) {

			CalificacionEntity calification = new CalificacionEntity();

			String mailTeacher = calificaciones.get(i).getMailTeacher();
			String mailStudent = calificaciones.get(i).getMailStudent();
			String codeSubject = calificaciones.get(i).getCode();
			Boolean definitiva = calificaciones.get(i).getDefinitiva();
			Double nota = calificaciones.get(i).getNota();
			Revisada revisada = calificaciones.get(i).getRevisada();

			TeacherEntity teacher = teacherRepository.findById(mailTeacher)
					.orElseThrow(() -> new RuntimeException("Profesor debe estar  dado de alta en el sistema", null));
			SubjectEntity subject = subjectRepository.findById(codeSubject)
					.orElseThrow(() -> new RuntimeException("Asignatura debe estar dada de alta en el sistema", null));
			StudentEntity student = studentRepository.findById(mailStudent)
					.orElseThrow(() -> new RuntimeException("Alumno debe estar dada de alta en el sistema", null));
			
			
			CalificacionPK calificacionPK = new CalificacionPK();
			calificacionPK.setStudentId(mailStudent);
			calificacionPK.setSubjectId(codeSubject);
			calificacionPK.setTeacherId(mailTeacher);
			
			if (calificacionRepository.existsById(calificacionPK))
				throw new RuntimeException("Calificacion ya existe", null);
			
			calification.setStudent(student);
			calification.setSubject(subject);
			calification.setTeacher(teacher);
			calification.setDefinitiva(definitiva);
			calification.setNota(nota);
			calification.setRevisada(revisada);

			calificacionRepository.save(calification);
		}

	}

}
