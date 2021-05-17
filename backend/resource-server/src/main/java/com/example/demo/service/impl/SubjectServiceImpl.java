package com.example.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.SubjectEntity;
import com.example.demo.mapper.SubjectEntityMapper;
import com.example.demo.model.request.PostSubjectRequest;
import com.example.demo.model.response.GetSubjectsByIdResponse;
import com.example.demo.repository.SubjectRepository;
import com.example.demo.service.SubjectService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SubjectServiceImpl implements SubjectService {
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	public GetSubjectsByIdResponse getSubjectsById(String id) {
		
		Optional<SubjectEntity> subjectOptional = subjectRepository.findById(id);
		
		if(subjectOptional.isPresent()) {
			//Devuelve un DTO para poder enviar la entidad
			return SubjectEntityMapper.INSTANCE.map(subjectOptional.get());
			
		}
		
		return null;
		
	}
	
	public Boolean saveSubject(PostSubjectRequest subjectDTO) {
		try {
			//Devuelve una entidad para poder almacenarla
			SubjectEntity s= SubjectEntityMapper.INSTANCE.map(subjectDTO);
			subjectRepository.save(s);
			return true;
		}catch(Exception e){
			log.error("Error al guardar subject", e);
			return false;
		}
		
	}
	
	
	

}
