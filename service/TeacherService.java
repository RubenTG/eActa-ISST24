package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CalificacionEntity;
import com.example.demo.entity.CalificacionEntity.Revisada;
import com.example.demo.entity.SubjectEntity;
import com.example.demo.entity.TeacherEntity;
import com.example.demo.mapper.SubjectEntityMapper;
import com.example.demo.model.response.GetAllStudentsForTeacher;
import com.example.demo.model.response.GetSubjectsForTeacher;
import com.example.demo.model.response.StudentTeacher;
import com.example.demo.repository.CalificacionRepository;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.repository.TeacherRepository;

@Service
public class TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private CalificacionRepository calificacionRepository;
	
	@Autowired
	private SubjectRepository subjectRepository;

	public GetSubjectsForTeacher getSubjects(String dni) {

		TeacherEntity teacher = teacherRepository.findById(dni).orElseThrow(() -> new RuntimeException());

		return SubjectEntityMapper.INSTANCE.map(teacher);

	}
	
	
	public GetAllStudentsForTeacher getStudents(String dni,String idSubject,Revisada revision) {
		
		TeacherEntity teacher = teacherRepository.findById(dni).orElseThrow(() -> new RuntimeException());
		SubjectEntity subject = subjectRepository.findById(idSubject).orElseThrow(() -> new RuntimeException());
		
		 List <CalificacionEntity> Calificaciones = null;
		
		 if(Objects.isNull(revision)) {
			 
			 Calificaciones = calificacionRepository.findByTeacherAndSubject(teacher, subject);
			 
		 } else {
			 
			 Calificaciones = calificacionRepository.findByTeacherAndSubjectAndRevisada(teacher, subject,revision);
			 
		 }
		
		 
		 GetAllStudentsForTeacher response = new GetAllStudentsForTeacher();
		 response.setTeacherName(teacher.getName());
		 response.setSubjectName(subject.getName());
		 
		 List<StudentTeacher> students = new ArrayList<>();
		 
		 for (CalificacionEntity calificacion : Calificaciones) {
			
			 StudentTeacher student = new StudentTeacher();
			 student.setNota(calificacion.getNota());
			 student.setRevisada(calificacion.getRevisada().name());
			 student.setStudentName(calificacion.getStudent().getName());
		
			 students.add(student);
		}
		 
		 response.setStudents(students);
		 
		 return response;
	}

	
}
