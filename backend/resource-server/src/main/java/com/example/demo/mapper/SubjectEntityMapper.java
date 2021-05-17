package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.example.demo.entity.SubjectEntity;
import com.example.demo.entity.TeacherEntity;
import com.example.demo.model.request.PostSubjectRequest;
import com.example.demo.model.response.GetSubjectsByIdResponse;
import com.example.demo.model.response.GetSubjectsForTeacher;

@Mapper(uses = SubjectTeacherMapper.class)
public interface SubjectEntityMapper {
	
	public static final SubjectEntityMapper INSTANCE = Mappers.getMapper(SubjectEntityMapper.class);
	
	GetSubjectsByIdResponse map(SubjectEntity subjectEntity);

	SubjectEntity map(PostSubjectRequest request);
	
	@Mapping(target = "teacherName", source = "name")
	GetSubjectsForTeacher map(TeacherEntity teacher);
	
}


