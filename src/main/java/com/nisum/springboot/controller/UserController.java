package com.nisum.springboot.controller;

import com.nisum.springboot.dto.UserDto;
import com.nisum.springboot.dto.UserLoginDto;
import com.nisum.springboot.dto.UserPhoneDto;
import com.nisum.springboot.exception.AlreadyRegisteredException;
import com.nisum.springboot.exception.BadEmailException;
import com.nisum.springboot.model.User;
import com.nisum.springboot.repository.UserRepository;
import com.nisum.springboot.service.UserService;
import com.nisum.springboot.util.Util;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public UserLoginDto login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
        String token = getJWTToken(username);
        UserLoginDto user = new UserLoginDto();
        user.setUser(username);
        user.setToken(token);
        return user;

    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }


    // build create user REST API
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody User user) {
        UserDto userDto = new UserDto();

        UUID uuid = UUID.randomUUID();
        userDto.setId(String.valueOf(uuid));
        List<UserPhoneDto> users = userService.findUsers();

        if(!Util.ValidarMail(user.getEmail())){
            throw new BadEmailException("Correo incorrecto");
        }

        users.forEach(user1 -> {

            if (user.getEmail().equals(user1.getEmail())) {
                throw new AlreadyRegisteredException("Correo ya registrado");

            }
        });
        userDto.setToken(getJWTToken(user.getName()));
        userDto.setIsActive(true);
        User created = userService.createUser(user);
        userDto.setCreated(created.getCreated());
        userDto.setModified(created.getModified());
        userDto.setLastLogin(created.getLastLogin());
        return new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED);
    }

    @GetMapping
    public List<UserPhoneDto> findUsers() {
        List<UserPhoneDto> users = userService.findUsers();
        return users;
    }
}
