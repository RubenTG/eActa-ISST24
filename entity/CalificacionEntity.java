package com.example.demo.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "CALIFICACION")
public class CalificacionEntity {

	@EmbeddedId
	private CalificacionPK id = new CalificacionPK();

	@ManyToOne
	@MapsId("studentDni")
	private StudentEntity student;

	@ManyToOne
	@MapsId("subjectId")
	private SubjectEntity subject;

	@ManyToOne
	@MapsId("teacherId")
	private TeacherEntity teacher;

	private Double nota;

	private Boolean definitiva;

	private Revisada revisada;

	public enum Revisada {
		NO_REVISADA, PENDIENTE, REVISADA
	};

}
