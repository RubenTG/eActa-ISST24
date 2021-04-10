package com.example.demo.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.response.GetSubjectsForStudent;
import com.example.demo.repository.SubjectCalificacion;
import com.example.demo.repository.SubjectRepository;

@Service
public class StudentService {

	@Autowired
	private SubjectRepository studentSubjectRepository;
	
	public GetSubjectsForStudent getSubjects(String dni) {

		GetSubjectsForStudent response = new GetSubjectsForStudent();
		
		Set<SubjectCalificacion> subjects = studentSubjectRepository.findSubjects(dni);

		response.setSubjects(subjects);
		
		return response;

	}

}
