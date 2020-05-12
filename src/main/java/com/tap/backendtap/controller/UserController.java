package com.tap.backendtap.controller;

import com.tap.backendtap.model.User;
import com.tap.backendtap.repository.UserRepo;
import com.tap.backendtap.security.Model.JwtResponse;
import com.tap.backendtap.security.jwt.JwtUtils;
import com.tap.backendtap.security.services.UserDetailsImpl;
import com.tap.backendtap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Florina on 5/6/2020
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepo userRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestParam String email,
                                              @RequestParam String password) throws UnsupportedEncodingException {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestParam String name,
                                          @RequestParam String surname,
                                          @RequestParam String email,
                                          @RequestParam String password) {
        if (userRepository.existsUserByEmail(email)) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        if (userRepository.existsUserByNameAndSurname(name, surname)) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }
        User user = new User(name, surname, email, encoder.encode(password));
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully!");
    }

    @RequestMapping(value = "/idByEmail", method = GET)
    public ResponseEntity<Long> findIdByEmail(@RequestParam String email) {
        Long userId = userService.findIdUserByEmail(email);
        return new ResponseEntity<Long>(userId, HttpStatus.OK);
    }
}
