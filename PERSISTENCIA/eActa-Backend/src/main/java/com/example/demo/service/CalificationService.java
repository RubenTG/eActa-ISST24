package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		calificacionPk.setStudentDni(idStudent);
		calificacionPk.setSubjectId(idSubject);
		calificacionPk.setTeacherId(idTeacher);
		CalificacionEntity calificacion = calificacionRepository.findById(calificacionPk)
				.orElseThrow(() -> new RuntimeException());

		if (calificacion.getRevisada().equals(Revisada.NO_REVISADA)) {
			calificacion.setRevisada(revisionDTO.getRevisada());
			calificacionRepository.save(calificacion);
		} else if (calificacion.getRevisada().equals(Revisada.PENDIENTE)) {

			throw new RuntimeErrorException(null, "Ya ha solicitado revisión");
		} else {
			throw new RuntimeErrorException(null, "Su calificación ya ha sido revisada");
		}
	}

	public void updateCalificacion(PutRevision revisionDTO, String idStudent, String idTeacher, String idSubject) {

		CalificacionPK calificacionPk = new CalificacionPK();
		calificacionPk.setStudentDni(idStudent);
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

			StudentEntity student = new StudentEntity();
			StudentSubjectEntity studentSubject = new StudentSubjectEntity();
			List<StudentSubjectEntity> studentsSubject = new ArrayList<>();
			CalificacionEntity calification = new CalificacionEntity();

			String dniTeacher = calificaciones.get(i).getDniTeacher();
			String dniStudent = calificaciones.get(i).getDniStudent();
			String codeSubject = calificaciones.get(i).getCode();
			String studentName = calificaciones.get(i).getStudentName();
			Boolean definitiva = calificaciones.get(i).getDefinitiva();
			Double nota = calificaciones.get(i).getNota();

			TeacherEntity teacher = teacherRepository.findById(dniTeacher)
					.orElseThrow(() -> new RuntimeException("Profesor debe estar  dado de alta en el sistema", null));
			if (studentRepository.existsById(dniStudent))
				throw new RuntimeException("Alumno ya existe", null);

			SubjectEntity subject = subjectRepository.findById(codeSubject)
					.orElseThrow(() -> new RuntimeException("Asignatura debe estar dada de alta en el sistema", null));

			student.setDni(dniStudent);
			student.setName(studentName);

			studentSubject.setStudent(student);
			studentSubject.setSubject(subject);

			calification.setStudent(student);
			calification.setSubject(subject);
			calification.setTeacher(teacher);
			calification.setDefinitiva(definitiva);
			calification.setNota(nota);

			List<StudentSubjectEntity> studentSubjectEntities = new ArrayList<>();
			studentSubjectEntities.add(studentSubject);

			Set<CalificacionEntity> calificacionEntities = new HashSet<>();
			calificacionEntities.add(calification);

			student.setCalificaciones(calificacionEntities);
			student.setSubjects(studentSubjectEntities);

			student = studentRepository.save(student);

		}

	}

}