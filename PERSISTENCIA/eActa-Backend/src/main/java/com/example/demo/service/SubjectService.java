package com.example.demo.service;


import com.example.demo.model.request.PostSubjectRequest;
import com.example.demo.model.response.GetSubjectsByIdResponse;

public interface SubjectService {
	
	 GetSubjectsByIdResponse getSubjectsById(String id);
	  Boolean saveSubject(PostSubjectRequest request) ;
}