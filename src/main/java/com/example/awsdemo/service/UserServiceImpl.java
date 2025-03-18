package com.example.awsdemo.service;

import com.example.awsdemo.entity.User;
import com.example.awsdemo.models.UserDTO;
import com.example.awsdemo.models.request.LogInRequest;
import com.example.awsdemo.models.request.SignUpRequest;
import com.example.awsdemo.models.response.LoginResponse;
import com.example.awsdemo.repository.UserRepository;
import com.example.awsdemo.security.JwtUtil;
import com.example.awsdemo.security.SecurityHelper;
import com.example.awsdemo.exception.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void createUser(SignUpRequest signUpRequest) throws CustomException {
        User user = userRepository.findByUserName(signUpRequest.getUserName());
        if (user != null) {
            throw new UserNameAlreadyExistsException();
        }
        user = userRepository.findByEmail(signUpRequest.getEmail());
        if (user != null) {
            throw new EmailAlreadyExistsException();
        }
        user = new User();
        BeanUtils.copyProperties(signUpRequest, user);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userRepository.save(user);
    }

    @Override
    public LoginResponse login(LogInRequest logInRequest) throws CustomException {
        User user = null;
        if (StringUtils.isNotEmpty(logInRequest.getEmail())) {
            user = userRepository.findByEmail(logInRequest.getEmail());
        }
        if (StringUtils.isNotEmpty(logInRequest.getUsername())) {
            user = userRepository.findByUserName(logInRequest.getUsername());
        }
        if (user == null) {
            throw new UserNotFoundException();
        }
        if (!passwordEncoder.matches(logInRequest.getPassword(), user.getPassword())) {
            throw new PasswordNotMatchException();
        }
        return getLoginResponse(user);
    }

    @Override
    public UserDTO getProfile() throws CustomException {
        String userName = SecurityHelper.getUserNameFromSecurityContext();
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            return null;
        }
        user.setId(null);
        return getDto(user);

    }

    @Override
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUserName(username);
        return getDto(user);
    }

    private LoginResponse getLoginResponse(User user) {
        return LoginResponse.builder()
                .accessToken(jwtUtil.generateAccessToken(user.getUserName(), user.getRole()))
                .refreshToken(jwtUtil.generateRefreshToken(user.getUserName()))
                .build();
    }

    private UserDTO getDto(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }
}
