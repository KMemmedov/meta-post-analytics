package com.kamal.metapostanalytics.dto;

import lombok.Data;

@Data
public class TopPostDto {

    private String postId;
    private String message;
    private Integer engagement;

}