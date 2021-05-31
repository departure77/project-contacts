package com.backend.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class GetUserDto {

    @JsonProperty("idUser")
    private int idUser;

    @JsonProperty("username")
    private String username;

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
}
