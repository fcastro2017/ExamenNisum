package com.nisum.springboot.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nisum.springboot.jackson.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String id;

    @JsonSerialize(using= LocalDateTimeSerializer.class)
    private LocalDateTime created;
    @JsonSerialize(using= LocalDateTimeSerializer.class)
    private LocalDateTime modified;

    @JsonSerialize(using= LocalDateTimeSerializer.class)
    private LocalDateTime lastLogin;
    private String user;
    private String token;
    private String pwd;
    private Boolean isActive;

}
