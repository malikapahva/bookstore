package com.challengers.controller;

import com.challengers.dto.UserDto;
import com.challengers.entities.User;
import com.challengers.services.UserService;
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
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody String userName){
        User user = userService.login(userName);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand()
                .toUri());
        if(user != null){
            return new ResponseEntity<>(user, httpHeaders, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("User not Found with username " + userName, httpHeaders, HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/registeruser", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand()
                .toUri());
        User user = userService.registerUser(userDto);
        if(user == null){
            return new ResponseEntity<>("User with username " + userDto.getUserName() + " already existed.", httpHeaders, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("User Registered Successfully, user id : " + user.getUserId(), httpHeaders, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/updateuser/{userId}", method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UserDto userDto){
        User user = userService.updateUser(userId, userDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .buildAndExpand()
                .toUri());
        if(user != null){
            return new ResponseEntity<>("User Updated Successfully", httpHeaders, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("User not found, user Id : " + userId, httpHeaders, HttpStatus.NOT_FOUND);
        }
    }
}
