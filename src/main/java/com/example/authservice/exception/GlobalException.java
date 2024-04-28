package com.example.authservice.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class GlobalException extends RuntimeException{

    private HttpStatus httpStatus;
    private String message;

}
















