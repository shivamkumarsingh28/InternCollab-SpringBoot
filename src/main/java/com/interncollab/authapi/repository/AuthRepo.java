package com.interncollab.authapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interncollab.authapi.entities.Auth;

public interface AuthRepo extends JpaRepository<Auth, Integer>{
    
    Optional<Auth> findByEmail(String email);

 } 
