package com.example.demo.model.response;

import lombok.Data;

@Data
public class GetSubjectsByIdResponse {
	
	private String code;
	private String name;
	private String acronym;
	private String department;

}
