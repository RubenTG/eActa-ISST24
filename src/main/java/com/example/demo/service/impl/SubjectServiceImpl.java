package com.example.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.SubjectEntity;
import com.example.demo.mapper.SubjectEntityMapper;
import com.example.demo.model.response.GetSubjectsByIdResponse;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	public GetSubjectsByIdResponse getSubjectsById(String id) {
		
		Optional<SubjectEntity> subjectOptional = subjectRepository.findById(id);
		
		if(subjectOptional.isPresent()) {
			
			return SubjectEntityMapper.INSTANCE.map(subjectOptional.get());
			
		}
		
		return null;
		
	}

}
