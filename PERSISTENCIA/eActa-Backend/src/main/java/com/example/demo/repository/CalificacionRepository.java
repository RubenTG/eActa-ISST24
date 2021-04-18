package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CalificacionEntity;
import com.example.demo.entity.CalificacionEntity.Revisada;
import com.example.demo.entity.CalificacionPK;
import com.example.demo.entity.SubjectEntity;
import com.example.demo.entity.TeacherEntity;

@Repository
public interface CalificacionRepository  extends JpaRepository<CalificacionEntity, CalificacionPK>{
	
	List<CalificacionEntity> findByTeacherAndSubject(TeacherEntity teacher, SubjectEntity subjectEntity);
	
	List<CalificacionEntity> findByTeacherAndSubjectAndRevisada(TeacherEntity teacher, SubjectEntity subjectEntity,Revisada revisada);
	
}