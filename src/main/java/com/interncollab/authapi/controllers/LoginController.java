package com.interncollab.authapi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interncollab.authapi.payloads.AuthDto;
import com.interncollab.authapi.payloads.JwtAuthRequest;
import com.interncollab.authapi.payloads.JwtAuthResponse;
import com.interncollab.authapi.security.JwtTokenHelper;
import com.interncollab.authapi.services.AuthService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth/")
public class LoginController {
    
    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
        
        this.authenticate(request.getUsername(), request.getPassword());

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        
        String token = this.jwtTokenHelper.generateToken(userDetails);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        try{
            this.authenticationManager.authenticate(authenticationToken);

        } catch(BadCredentialsException e){
            System.out.println("Invalid Detail !!");
            throw new Exception("Invalid username or password !!");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthDto> registerAuth(@Valid @RequestBody AuthDto authDto) {
        //TODO: process POST request
        AuthDto registerAuth = this.authService.registerNewAuth(authDto);
        return new ResponseEntity<AuthDto>(registerAuth, HttpStatus.CREATED);
    }
    
    
}
