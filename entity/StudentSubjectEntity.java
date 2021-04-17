package com.example.demo.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name ="ALUMNO_ASIGNATURA")
public class StudentSubjectEntity {

	@EmbeddedId
	private StudentSubjectPK id = new StudentSubjectPK();
	
	@ManyToOne
	@MapsId("studentId")
	@JoinColumn(name = "ALUMNO_ID")
	private StudentEntity student;
	
	@ManyToOne
	@MapsId("subjectId")
	@JoinColumn(name = "ASIGNATURA_ID")
	private SubjectEntity subject;
	
}
