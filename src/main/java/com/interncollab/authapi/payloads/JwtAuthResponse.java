package com.interncollab.authapi.payloads;

import lombok.Data;

@Data
public class JwtAuthResponse {
    private String token;


    private AuthDto auth;
}
