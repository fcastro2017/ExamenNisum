package com.nisum.springboot.dto;

import com.nisum.springboot.model.Phone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPhoneDto {

    private String name;
    private String email;
    private String password;
    private List<PhoneDto> phones;
}
