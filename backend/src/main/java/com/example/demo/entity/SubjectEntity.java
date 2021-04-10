package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ASIGNATURA")
public class SubjectEntity {
	
	@Id
	@GeneratedValue
	private String code;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String acronym;
	
	@Column(nullable = false)
	private String department;

}
