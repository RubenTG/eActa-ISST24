package com.example.demo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "MIEMBRO_TRIBUNAL")
public class TeacherEntity {

	@Id
	private String dni;

	@Column(nullable = false)
	private String name;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "PROFESOR_ASIGNATURA", joinColumns = @JoinColumn(name = "PROFESOR_ID"), inverseJoinColumns = @JoinColumn(name = "ASIGNATURA_ID"))
	private List <SubjectEntity> subjects;
}