package com.kamal.metapostanalytics.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String message;

    private Integer status;

    private LocalDateTime timestamp;

}