package com.example.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.ActaEntity;
import com.example.demo.entity.CalificacionEntity;
import com.example.demo.entity.CalificacionEntity.Revisada;
import com.example.demo.entity.SubjectEntity;
import com.example.demo.entity.TeacherEntity;
import com.example.demo.mapper.SubjectEntityMapper;
import com.example.demo.model.Teacher;
import com.example.demo.model.response.GetAllStudentsForTeacher;
import com.example.demo.model.response.GetSubjectsForTeacher;
import com.example.demo.model.response.StudentTeacher;
import com.example.demo.repository.ActaRepository;
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
	
	@Autowired
	private ActaRepository actaRepository;
	
	public void enviarActa(MultipartFile file, String mail) throws IOException {
		
		TeacherEntity teacher = teacherRepository.findById(mail).orElseThrow(() -> new RuntimeException());
		
		ActaEntity entity = new ActaEntity();
		entity.setFile(file.getBytes());
		entity.setTeacher(teacher);
		actaRepository.save(entity);
		
		
		
		
	}

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
		 
		 Set<Teacher> profesores = new HashSet<>();
		 
		 for(TeacherEntity profesor : subject.getTeachers()) {
			 
			 Teacher profesorRespuesta = new Teacher();
			 profesorRespuesta.setMail(profesor.getMail());
			 profesorRespuesta.setName(profesor.getName());
			 profesores.add(profesorRespuesta);
			 
		 }
		 
		 response.setMiembrosTribunal(profesores);
		 
		 List<StudentTeacher> students = new ArrayList<>();
		 
		 for (CalificacionEntity calificacion : Calificaciones) {
			
			 StudentTeacher student = new StudentTeacher();
			 student.setNota(calificacion.getNota());
			 student.setRevisada(calificacion.getRevisada().name());
			 student.setStudentName(calificacion.getStudent().getName());
			 student.setDefinitiva(calificacion.getDefinitiva());
		     student.setMailStudent(calificacion.getStudent().getMail());
			 students.add(student);
		}
		 
		 response.setStudents(students);
		 
		 return response;
	}

	
}
