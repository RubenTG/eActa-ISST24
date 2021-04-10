package com.example.demo.model.response;

import lombok.Data;

@Data
public class GetSubjectsByIdResponse {
	
	private String id;
	private String name;
	private String acronym;
	private String department;

}
