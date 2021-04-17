package com.example.demo.model.response;

import java.util.List;

import com.example.demo.model.SubjectTeacher;

import lombok.Data;

@Data
public class GetSubjectsForTeacher {

	private String teacherName;
	private List<SubjectTeacher> subjects;
	

}
