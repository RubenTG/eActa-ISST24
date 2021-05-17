package com.example.demo.repository;

import java.util.Date;

import com.example.demo.entity.CalificacionEntity.Revisada;

public interface SubjectCalificacion {
    String getCode();
	String getName();
	Double getNota();
	Boolean getDefinitiva();
	Revisada getRevisada();
	Date getFechaPubli();
	Date getFechaRev();
	String getTeacher();
}
