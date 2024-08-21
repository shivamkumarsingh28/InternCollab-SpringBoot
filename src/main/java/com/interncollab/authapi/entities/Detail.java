package com.interncollab.authapi.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Auth_Detail")
@Getter
@Setter
@NoArgsConstructor
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "fullName")
    private String fullName;

    @Column(length = 10)
    private String contact;

    @Column(length = 1000000000)
    private String about;

    private String profilepic;

    private String document;

    private Date addedDate;

    @OneToOne
    private Auth auth;
}
