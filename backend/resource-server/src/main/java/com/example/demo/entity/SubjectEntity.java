package com.example.demo.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ASIGNATURA")
public class SubjectEntity {
	
	@Id
	private String code;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String acronym;
	
	@Column(nullable = false)
	private String department;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "PROFESOR_ASIGNATURA", joinColumns = @JoinColumn(name = "ASIGNATURA_ID"), inverseJoinColumns = @JoinColumn(name = "PROFESOR_ID"))
	private Set <TeacherEntity> teachers;

}
