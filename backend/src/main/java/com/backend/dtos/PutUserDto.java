package com.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PutUserDto {

    @JsonProperty("username")
    private String username;

    @JsonProperty("mail")
    private String mail;

    @JsonProperty("password")
    private String password;

    @JsonProperty("newPassword")
    private String newPassword;
}
