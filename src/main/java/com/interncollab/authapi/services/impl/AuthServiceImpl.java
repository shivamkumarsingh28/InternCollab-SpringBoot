package com.interncollab.authapi.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.interncollab.authapi.config.AppConstants;
import com.interncollab.authapi.entities.Auth;
import com.interncollab.authapi.entities.Role;
import com.interncollab.authapi.exceptions.ResourceNotFoundException;
import com.interncollab.authapi.payloads.AuthDto;
import com.interncollab.authapi.repository.AuthRepo;
import com.interncollab.authapi.repository.RoleRepo;
import com.interncollab.authapi.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthRepo authRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public AuthDto createAuth(AuthDto authDto) {
        Auth auth = this.dtoToAuth(authDto);
        Auth savedAuth =this.authRepo.save(auth);
        return this.authToDto(savedAuth);
    }

    @Override
    public AuthDto updateAuth(AuthDto authDto, Integer authId) {
        Auth auth = this.authRepo.findById(authId).orElseThrow(()-> new ResourceNotFoundException("auth", " id ", authId));

        auth.setUserName(authDto.getUserName());
        auth.setEmail(authDto.getEmail());
        auth.setPassword(authDto.getPassword());

        Auth updatedAuth = this.authRepo.save(auth);
        AuthDto authDto1 = this.authToDto(updatedAuth);
        return authDto1;
    }

    @Override
    public AuthDto getAuthById(Integer authId) {
        Auth auth = this.authRepo.findById(authId).orElseThrow(()-> new ResourceNotFoundException("auth", " id ", authId));
        return this.authToDto(auth);
    }

    @Override
    public List<AuthDto> getAllAuths() {

        List<Auth> auths = this.authRepo.findAll();
        List<AuthDto> authDtos = auths.stream().map(auth -> this.authToDto(auth)).collect(Collectors.toList());
        return authDtos;
    }

    @Override
    public void deleteAuth(Integer authId) {
        Auth auth = this.authRepo.findById(authId).orElseThrow(()-> new ResourceNotFoundException("auth", " id ", authId));
        this.authRepo.delete(auth);
    }

    // auth to authdto
    public AuthDto authToDto(Auth auth){
        AuthDto authDto = this.modelMapper.map(auth, AuthDto.class);
        // authDto.setId(auth.getId());
        // authDto.setUserName(auth.getUserName());
        // authDto.setEmail(auth.getEmail());
        // authDto.setPassword(auth.getPassword());
        return authDto;

    }

    // authdto to auth
    public Auth dtoToAuth(AuthDto authDto){
        Auth auth = this.modelMapper.map(authDto, Auth.class);
        // auth.setId(authDto.getId());
        // auth.setUserName(authDto.getUserName());
        // auth.setEmail(authDto.getEmail());
        // auth.setPassword(authDto.getPassword());
        return auth;
    }

    @Override
    public AuthDto registerNewAuth(AuthDto authDto) {
        Auth auth = this.modelMapper.map(authDto, Auth.class);
        // password encode 
        auth.setPassword(this.passwordEncoder.encode(auth.getPassword()));

        // role
        Role role = this.roleRepo.findById(AppConstants.STUDENT_USER).get();

        auth.getRole().add(role);

        Auth newAuth = this.authRepo.save(auth);
        return this.modelMapper.map(newAuth, AuthDto.class);
    }

}
