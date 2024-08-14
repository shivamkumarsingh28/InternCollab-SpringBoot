package com.interncollab.authapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interncollab.authapi.entities.Auth;
import com.interncollab.authapi.entities.Detail;



public interface DetailRepo extends JpaRepository<Detail, Integer>{

    Detail findByAuth(Auth auth);

    
}
