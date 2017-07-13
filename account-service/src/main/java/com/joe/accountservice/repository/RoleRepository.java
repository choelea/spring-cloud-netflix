package com.joe.accountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joe.accountservice.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role getByCode(String code);
}
