package com.example.demo.model.request;

import com.example.demo.entity.CalificacionEntity.Revisada;

import lombok.Data;

@Data
public class AllCalification {
	private String subjectName;
	private String studentName;
	private String code;
	private String mailStudent;
	private String mailTeacher;
	private Double nota;
	private Boolean definitiva;
	private Revisada revisada;
}
