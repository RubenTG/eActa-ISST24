package com.example.demo.service;

import com.example.demo.model.response.GetSubjectsByIdResponse;

public interface SubjectService {
	
	 GetSubjectsByIdResponse getSubjectsById(String id);

}
