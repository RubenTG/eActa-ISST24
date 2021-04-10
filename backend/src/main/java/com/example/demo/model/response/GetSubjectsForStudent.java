package com.example.demo.model.response;

import java.util.Set;

import com.example.demo.repository.SubjectCalificacion;

import lombok.Data;

@Data
public class GetSubjectsForStudent {

	private Set<SubjectCalificacion> subjects;
}
