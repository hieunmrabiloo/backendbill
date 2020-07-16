package com.example.backendbill.controller;

import com.example.backendbill.config.security.JwtTokenProvider;
import com.example.backendbill.entity.LoginForm;
import com.example.backendbill.entity.User;
import com.example.backendbill.entity.UserForm;
import com.example.backendbill.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    private static final String USERNAME_PASSWORD_WRONG = "Wrong username or password!";
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public UserController(JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager,
                          UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestBody LoginForm loginForm) throws JsonProcessingException {
        try {
            String username = loginForm.getUsername();
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(username, loginForm.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtTokenProvider.generateToken(authentication);
            LOGGER.info("Username: [{}] login success", loginForm.getUsername());
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Username: [{}] , password: [{}] login fail", loginForm.getUsername(), loginForm.getPassword());
            return new ResponseEntity<>(USERNAME_PASSWORD_WRONG, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/register")
    public ResponseEntity<?> registerUser(@RequestBody UserForm userForm) {
        System.out.println(userForm.getUsername());
        System.out.println(userForm.getRoom().getId());
        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        user.setRoom(userForm.getRoom());
        userRepository.save(user);
        return ResponseEntity.ok().body("ok");
    }
}

