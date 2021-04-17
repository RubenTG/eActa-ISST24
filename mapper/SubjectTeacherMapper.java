package com.example.demo.mapper;

import org.mapstruct.Mapper;

import com.example.demo.entity.SubjectEntity;
import com.example.demo.model.SubjectTeacher;

@Mapper
public interface SubjectTeacherMapper {

	SubjectTeacher map(SubjectEntity entity);
	
}
