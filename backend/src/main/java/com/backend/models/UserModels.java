package com.backend.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;

@Data
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int idUser;

    @Column (name = "username")
    private String username;

    @Column (name = "password")
    private String password;

    @Column (name = "name")
    private String name;

    @Column (name = "surname")
    private String surname;

    @Column (name = "mail")
    private String mail;

    @Column (name = "verification_code")
    private String verificationCode;

    @Column (name = "verified_mail")
    private Boolean verifiedMail;

    @Column (name = "creation_date")
    private Date creationDate;




}
