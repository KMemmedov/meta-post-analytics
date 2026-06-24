package com.kamal.metapostanalytics.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
public class MetaPostDto {

    private String id;

    @JsonProperty("caption")
    private String message;

    @JsonProperty("timestamp")
    private OffsetDateTime createdTime;

    @JsonProperty("like_count")
    private Integer likeCount;

    @JsonProperty("comments_count")
    private Integer commentCount;

}