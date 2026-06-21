package com.kamal.metapostanalytics.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MetaPostDto {

    private String id;
    private String message;
    private LocalDateTime createdTime;
    private Integer likeCount;
    private Integer commentCount;

}