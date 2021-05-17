package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.entity.StudentEntity;
import com.example.demo.model.request.PutRevision;

@Mapper
public interface StudentEntityMapper {

	public static final StudentEntityMapper INSTANCE = Mappers.getMapper(StudentEntityMapper.class);
	
}
