package com.interncollab.authapi.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interncollab.authapi.payloads.ApiResponse;
import com.interncollab.authapi.payloads.AuthDto;
import com.interncollab.authapi.services.AuthService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/user/")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Post- create auth
    @PostMapping("/")
    public ResponseEntity<AuthDto> createAuth(@Valid @RequestBody AuthDto authDto) {
        AuthDto createAuthDto = this.authService.createAuth(authDto);
        return new ResponseEntity<>(createAuthDto, HttpStatus.CREATED);
    }
    
    // Put - update auth
    @PutMapping("/{id}")
    public ResponseEntity<AuthDto> updateAuth(@Valid @RequestBody AuthDto authDto, @PathVariable Integer id) {
        AuthDto updatedAuth = this.authService.updateAuth(authDto, id);
        
        return ResponseEntity.ok(updatedAuth);
    }

    // Delete auth
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAuth(@PathVariable("id") Integer authId){
        this.authService.deleteAuth(authId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted", true), HttpStatus.OK);
    }

    // Get - auth get
    @GetMapping("/{id}")
    public ResponseEntity<AuthDto> getSingleAuth(@PathVariable Integer id) {
        return ResponseEntity.ok(this.authService.getAuthById(id));
    }

    // Get - all auth
    // @PreAuthorize("hasRole('COMPANY')")
    @GetMapping("/")
    public ResponseEntity<List<AuthDto>> getAllAuth() {
        return ResponseEntity.ok(this.authService.getAllAuths());
    }


    
    
}
