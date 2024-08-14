package com.interncollab.authapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.interncollab.authapi.entities.Auth;
import com.interncollab.authapi.exceptions.ResourceNotFoundException;
import com.interncollab.authapi.repository.AuthRepo;

@Service
public class CustomUserDetailService implements UserDetailsService{

    @Autowired
    private AuthRepo authRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Auth auth = this.authRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("user","email"+username, 0));
        return auth;
    }

    
}
