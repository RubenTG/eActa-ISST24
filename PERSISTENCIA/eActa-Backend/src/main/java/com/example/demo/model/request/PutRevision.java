package com.example.demo.model.request;

import com.example.demo.entity.CalificacionEntity.Revisada;

import lombok.Data;

@Data
public class PutRevision {
  private Revisada revisada;
  private Double nota;
;
  
}