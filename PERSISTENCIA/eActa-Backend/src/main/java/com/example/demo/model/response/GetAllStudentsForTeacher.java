package com.example.demo.model.response;

import java.util.List;

import com.example.demo.entity.StudentEntity;

import lombok.Data;

@Data
public class GetAllStudentsForTeacher {
   
	private String teacherName;
	private String subjectName;
	private List<StudentTeacher> students;
	
	
}