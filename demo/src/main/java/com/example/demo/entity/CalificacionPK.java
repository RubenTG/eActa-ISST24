package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class CalificacionPK implements Serializable {
	
	@Column
	String studentId;
	
	@Column
	String subjectId;

}
