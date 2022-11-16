package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.models.CurrentAdminSession;

public interface AdminSessionRepo extends JpaRepository<CurrentAdminSession, Integer>{
	
	public CurrentAdminSession findByadKey(String key);
	
}
