package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.entity.SubjectEntity;
import com.example.demo.model.response.GetSubjectsByIdResponse;

@Mapper
public interface SubjectEntityMapper {
	
	public static final SubjectEntityMapper INSTANCE = Mappers.getMapper(SubjectEntityMapper.class);
	
	GetSubjectsByIdResponse map(SubjectEntity subjectEntity);

}
