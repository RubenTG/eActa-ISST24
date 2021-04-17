package com.example.demo.model.request;

import lombok.Data;

@Data
public class AllCalification {
	private String subjectName;
	private String studentName;
	private String code;
	private String dniStudent;
	private String dniTeacher;
	private Double nota;
	private Boolean definitiva;
}
