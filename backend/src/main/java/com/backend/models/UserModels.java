package com.backend.models;


import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class UserModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
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

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false, insertable = false, updatable = false)
    private List<ContactsModels> contactos;






}
