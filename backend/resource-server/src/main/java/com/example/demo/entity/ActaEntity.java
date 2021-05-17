package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name ="ACTA")
public class ActaEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Lob
	private byte[] file;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private TeacherEntity teacher;

}
