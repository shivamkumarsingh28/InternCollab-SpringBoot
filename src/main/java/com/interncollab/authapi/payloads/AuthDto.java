package com.interncollab.authapi.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AuthDto {
    
    private int id;

    @NotEmpty
    @Size(min = 5, message = "Username must be min of 5 characters !!")
    private String userName;

    @Email(message = "Email address is not valid")
    private String email;

    @NotNull
    @Size(min = 3, max = 10, message = "Password must be min of 3 chars and max of 4 chars !!")
    private String password;
}
