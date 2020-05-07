package com.tap.backendtap.controller;

import com.tap.backendtap.model.User;
import com.tap.backendtap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Florina on 5/6/2020
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(method = POST)
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User newUser = userService.saveUser(user);
        return new ResponseEntity<User>(newUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/idByEmail", method=GET)
    public ResponseEntity<Long> findIdByEmail(String email){
        Long userId=userService.findIdUserByEmail(email);
        return new ResponseEntity<Long>(userId, HttpStatus.OK);
    }
}
