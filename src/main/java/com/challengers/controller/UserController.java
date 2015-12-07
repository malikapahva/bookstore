package com.challengers.controller;

import com.challengers.dto.UserDto;
import com.challengers.entities.User;
import com.challengers.repo.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Created by Malika(mxp134930) on 11/29/2015.
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody String userName){
        User user = userRepository.findByUserName(userName);
        HttpHeaders httpHeaders = new HttpHeaders();
        if(user != null){
            httpHeaders.setLocation(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .buildAndExpand()
                    .toUri());
            return new ResponseEntity<>(user, httpHeaders, HttpStatus.OK);
        }else {
            httpHeaders.setLocation(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .buildAndExpand()
                    .toUri());
            return new ResponseEntity<>("User not Found with username " + userName, httpHeaders, HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/registeruser", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto){
        User existedUser = userRepository.findByUserName(userDto.getUserName());
        HttpHeaders httpHeaders = new HttpHeaders();
        if(existedUser != null){
            httpHeaders.setLocation(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .buildAndExpand()
                    .toUri());
            return new ResponseEntity<>("User with username " + userDto.getUserName() + " already existed.", httpHeaders, HttpStatus.BAD_REQUEST);
        } else {
            User user = new User(userDto.getUserName(), userDto.getPassword(), userDto.getFirstName(), userDto.getMiddleName(), userDto.getLastName(),
                    userDto.getStreet(), userDto.getCity(), userDto.getZipCode(), userDto.getState(), userDto.getCountry());
            userRepository.save(user);
            httpHeaders.setLocation(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .buildAndExpand()
                    .toUri());
            return new ResponseEntity<>("User Registered Successfully, user id : " + user.getUserId(), httpHeaders, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/updateuser/{userId}", method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto){
        User user = userRepository.findOne(userId);
        HttpHeaders httpHeaders = new HttpHeaders();
        if(user != null){
            user.setUserName(userDto.getUserName());
            user.setPassword(userDto.getPassword());
            user.setFirstName(userDto.getFirstName());
            user.setMiddleName(userDto.getMiddleName());
            user.setLastName(userDto.getLastName());
            user.setStreet(userDto.getStreet());
            user.setCity(userDto.getCity());
            user.setZipCode(userDto.getZipCode());
            user.setState(userDto.getState());
            user.setCountry(userDto.getCountry());
            userRepository.save(user);

            httpHeaders.setLocation(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .buildAndExpand()
                    .toUri());
            return new ResponseEntity<>("User Updated Successfully", httpHeaders, HttpStatus.OK);
        }
        else {
            httpHeaders.setLocation(ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .buildAndExpand()
                    .toUri());
            return new ResponseEntity<>("User not found, user Id : " + userId, httpHeaders, HttpStatus.NOT_FOUND);
        }
    }
}
