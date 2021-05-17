package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
	@MapsId("studentId")
	//@JoinColumn(name = "MAIL_STUDENT")
	private StudentEntity student;

	@ManyToOne
	@MapsId("subjectId")
	private SubjectEntity subject;

	@ManyToOne
	@MapsId("teacherId")
	//@JoinColumn(name = "MAIL_TEACHER")
	private TeacherEntity teacher;

	private Double nota;

	private Boolean definitiva;

	private Revisada revisada;

	public enum Revisada {
		NO_REVISADA, PENDIENTE, REVISADA
	};

	private Date fechaPubli= new Date();
	private Date fechaRev= new Date();
}
