package com.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PutUserDto {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;


}
