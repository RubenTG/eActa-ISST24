package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ActaEntity;

@Repository
public interface ActaRepository extends JpaRepository<ActaEntity, Long> {

}
