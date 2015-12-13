package com.challengers.services;

import com.challengers.dto.UserDto;
import com.challengers.entities.User;
import com.challengers.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Malika(mxp134930) on 12/6/2015.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public User login(String userName) {
        User user = userRepository.findByUserName(userName);
        return userRepository.save(user);
    }

    @Transactional(readOnly = false)
    @CachePut(value = "defaultCache", key = "#userDto.userName")
    public User registerUser(UserDto userDto) {
        boolean existedUser = isExistedUser(userDto.getUserName());
        if(existedUser){
            return null;
        }
        User user = new User(userDto.getUserName(), userDto.getPassword(), userDto.getFirstName(), userDto.getMiddleName(), userDto.getLastName(),
            userDto.getStreet(), userDto.getCity(), userDto.getZipCode(), userDto.getState(), userDto.getCountry(), userDto.getRole(), userDto.getEmail());
        return userRepository.save(user);

    }

    @Transactional(readOnly = false)
    @CacheEvict(value = "defaultCache", key = "#userDto.userName")
    public User updateUser(Long userId,UserDto userDto) {
        User user = userRepository.findOne(userId);
        if(user != null) {
            user.setUserName(userDto.getUserName());
            user.setPassword(userDto.getPassword());
            user.setFirstName(userDto.getFirstName());
            user.setMiddleName(userDto.getMiddleName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setStreet(userDto.getStreet());
            user.setCity(userDto.getCity());
            user.setZipCode(userDto.getZipCode());
            user.setState(userDto.getState());
            user.setCountry(userDto.getCountry());
            return userRepository.save(user);
        } else
            return null;
    }

    private boolean isExistedUser(String userName){
        User user = userRepository.findByUserName(userName);
        return user != null;
    }
}
