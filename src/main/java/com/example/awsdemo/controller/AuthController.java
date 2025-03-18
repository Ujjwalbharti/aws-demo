package com.example.awsdemo.controller;

import com.example.awsdemo.models.UserDTO;
import com.example.awsdemo.models.request.LogInRequest;
import com.example.awsdemo.models.request.SignUpRequest;
import com.example.awsdemo.models.response.LoginResponse;
import com.example.awsdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/sqs")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequest signUpRequest) throws Exception {
        userService.createUser(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created user successfully");
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LogInRequest logInRequest) throws Exception {
        LoginResponse response = userService.login(logInRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("profile")
    public ResponseEntity<UserDTO> getProfile() throws Exception {
        UserDTO user = userService.getProfile();
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
