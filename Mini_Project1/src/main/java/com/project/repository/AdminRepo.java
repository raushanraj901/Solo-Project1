package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.models.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer>{
	
	public Admin findByMobile(String mobile);

}
