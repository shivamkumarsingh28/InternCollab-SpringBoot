package com.interncollab.authapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interncollab.authapi.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{
    
}
