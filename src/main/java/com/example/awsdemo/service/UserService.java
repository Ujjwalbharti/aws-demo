package com.example.awsdemo.service;

import com.example.awsdemo.exception.CustomException;
import com.example.awsdemo.models.UserDTO;
import com.example.awsdemo.models.request.LogInRequest;
import com.example.awsdemo.models.request.SignUpRequest;
import com.example.awsdemo.models.response.LoginResponse;

public interface UserService {

    void createUser(SignUpRequest signUpRequest) throws CustomException;

    LoginResponse login(LogInRequest logInRequest) throws CustomException;

    UserDTO getProfile() throws CustomException;

    UserDTO getUserByUsername(String username) throws CustomException;
}
