package com.interncollab.authapi.services;

import java.util.List;

import com.interncollab.authapi.payloads.AuthDto;

public interface AuthService {

    AuthDto registerNewAuth(AuthDto auth);
    
    AuthDto createAuth(AuthDto auth);

    AuthDto updateAuth(AuthDto authDto, Integer authId);

    AuthDto getAuthById(Integer authId);

    List<AuthDto> getAllAuths();

    void deleteAuth(Integer authId);
}
