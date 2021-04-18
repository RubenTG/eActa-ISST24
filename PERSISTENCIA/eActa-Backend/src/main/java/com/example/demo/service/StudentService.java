package com.example.demo.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.StudentEntity;
import com.example.demo.entity.SubjectEntity;
import com.example.demo.mapper.StudentEntityMapper;
import com.example.demo.mapper.SubjectEntityMapper;
import com.example.demo.model.request.PostSubjectRequest;
import com.example.demo.model.request.PutRevision;
import com.example.demo.model.response.GetSubjectsForStudent;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.SubjectCalificacion;
import com.example.demo.repository.SubjectRepository;

@Service
public class StudentService {

	@Autowired
	private SubjectRepository studentSubjectRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	public GetSubjectsForStudent getSubjects(String dni) {

		GetSubjectsForStudent response = new GetSubjectsForStudent();
		
		StudentEntity student = studentRepository.findById(dni).orElseThrow(() -> new RuntimeException());
		
		Set<SubjectCalificacion> subjects = studentSubjectRepository.findSubjects(dni);

		response.setStudentName(student.getName());
		response.setSubjects(subjects);
		
		return response;

	}
	
	


}