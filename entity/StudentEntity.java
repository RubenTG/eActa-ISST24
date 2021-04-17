package com.example.demo.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name ="ALUMNO")
public class StudentEntity {
	
	@Id
	private String dni;
	
	@Column(nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "student", cascade =  CascadeType.ALL)
	private List<StudentSubjectEntity> subjects;
	
	@OneToMany(mappedBy = "student", cascade =  CascadeType.ALL)
	private Set<CalificacionEntity> calificaciones;
	
}
