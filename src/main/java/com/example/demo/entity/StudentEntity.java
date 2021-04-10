package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class StudentEntity {
	
	@Id
	private String dni;
	
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<SubjectEntity> subjects;

}
