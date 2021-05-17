package com.example.demo.model.response;

import java.util.List;
import java.util.Set;

import com.example.demo.model.Teacher;

import lombok.Data;

@Data
public class GetAllStudentsForTeacher {
   
	private String teacherName;
	private String subjectName;
	private Set<Teacher> miembrosTribunal;
	private List<StudentTeacher> students;
	
	
}
