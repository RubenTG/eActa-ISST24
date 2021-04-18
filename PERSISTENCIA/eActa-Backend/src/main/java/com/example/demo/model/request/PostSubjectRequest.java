package com.example.demo.model.request;



import lombok.Data;

@Data
public class PostSubjectRequest {

	private String code;
	private String name;
	private String acronym;
	private String department;
}