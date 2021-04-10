package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name ="CALIFICACION")
public class CalificacionEntity {
	
	@EmbeddedId
	private CalificacionPK id;
	
	@ManyToOne
	@MapsId("studentId")
	private StudentEntity student;
	
	@ManyToOne
	@MapsId("subjectId")
	private SubjectEntity subject;

	private Double nota;
	
	@Column(nullable = false)
	private Boolean definitiva;
	
}
