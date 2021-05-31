package com.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class UserDto {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("mail")
    private String mail;

    @JsonProperty("verificationCode")
    private String verificationCode;

    @JsonProperty("verifiedMail")
    private Boolean verifiedMail;

    @JsonProperty("creationDate")
    private Date creationDate;
}
