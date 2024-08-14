package com.interncollab.authapi.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    private int id;
    
    private String title;

    private String content;

    private String postImage;

    private Date addedDate;
 
    private CategoryDto category;

    private AuthDto auth;

    private Set<CommentDto> comments = new HashSet<>();
}
