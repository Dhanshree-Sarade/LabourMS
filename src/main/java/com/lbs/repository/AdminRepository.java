package com.lbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lbs.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	Admin findByUsername(String username);

}
