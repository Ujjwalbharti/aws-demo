package com.example.awsdemo.security;

import com.example.awsdemo.exception.CustomException;
import com.example.awsdemo.exception.UserNotAuthorisedException;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class SecurityHelper {
    public String getUserNameFromSecurityContext() throws CustomException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UserNotAuthorisedException();
        }
        var userDetails = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        return userDetails.getUsername();
    }
}
