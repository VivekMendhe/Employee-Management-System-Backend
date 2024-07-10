package com.pack.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pack.hr.entities.HR;

public interface HRRepository extends JpaRepository<HR, Long> {

}
