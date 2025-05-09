package com.hanium.smartdispenser.user.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UserCreateDto {
    private final String name;
    private final String email;
    private final String password;

    @JsonCreator
    public UserCreateDto(@JsonProperty("name") String name,
                         @JsonProperty("email") String email,
                         @JsonProperty("password") String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }


}
