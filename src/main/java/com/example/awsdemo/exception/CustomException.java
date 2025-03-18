package com.example.awsdemo.exception;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class CustomException extends Exception {
    private String message;
    private String errorCode;
}
