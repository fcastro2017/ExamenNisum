package com.nisum.springboot.service;

import com.nisum.springboot.dto.PhoneDto;
import com.nisum.springboot.dto.UserPhoneDto;
import com.nisum.springboot.model.User;
import com.nisum.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository ;

    @Override
    public User createUser(User user) {
        user.getPhones().forEach(phone -> {
            phone.setUser(user);
        });
        return userRepository.save(user);
    }
    @Override
    public List<UserPhoneDto> findUsers() {
        List<User> users = userRepository.findAll();
        List<UserPhoneDto> userPhoneDtoList = new ArrayList<>();
        List<PhoneDto> phoneDtoList = new ArrayList<>();
        UserPhoneDto userPhoneDto = new UserPhoneDto();
        PhoneDto phoneDto = new PhoneDto();
        users.forEach(user -> {
            userPhoneDto.setName(user.getName());
            userPhoneDto.setEmail(user.getEmail());
            userPhoneDto.setPassword(user.getPassword());
            user.getPhones().forEach(phone -> {
                phoneDto.setNumber(phone.getNumber());
                phoneDto.setCityCode(phone.getCityCode());
                phoneDto.setCountryCode(phone.getCountryCode());
                phoneDtoList.add(phoneDto);
                userPhoneDto.setPhones(phoneDtoList);
            });
            userPhoneDtoList.add(userPhoneDto);
        });
        return userPhoneDtoList;
    }
}
