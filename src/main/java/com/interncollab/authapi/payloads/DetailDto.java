package com.interncollab.authapi.payloads;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DetailDto {
    
    private int id;

    private String fullName;

    private String contact;

    private String about;

    private String profilePic;

    private String document;

    private Date addedDate;

    private AuthDto auth;

}
