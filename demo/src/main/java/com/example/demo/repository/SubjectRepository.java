package com.example.demo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SubjectEntity;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, String> {

	@Query("SELECT  asig.name as name, ca.definitiva as definitiva, ca.nota as nota FROM SubjectEntity AS asig  "
			+ "INNER JOIN StudentSubjectEntity AS a_asig ON asig.code = a_asig.id.subjectId "
			+ "LEFT JOIN CalificacionEntity as ca ON ca.id.subjectId = asig.code "
			+ "WHERE a_asig.id.studentId= :dni AND ca.id.studentId = a_asig.id.studentId")
	public Set<SubjectCalificacion> findSubjects(String dni);
	
}
