package com.kamal.metapostanalytics.dto;

import lombok.Data;

import java.util.List;

@Data
public class AnalyticsResponseDto {

    private List<TopPostDto> topPosts;

    private String bestDayForLikes;

    private String summary;

}