package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.StudentSubjectEntity;
import com.example.demo.entity.StudentSubjectPK;

@Repository
public interface StudentSubjectRepository  extends JpaRepository<StudentSubjectEntity, StudentSubjectPK> {

}
