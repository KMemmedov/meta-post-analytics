package com.kamal.metapostanalytics.controller;

import com.kamal.metapostanalytics.dto.AnalyticsResponseDto;
import com.kamal.metapostanalytics.service.AnalyticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/api/analytics")
    public AnalyticsResponseDto getAnalytics() {
        return analyticsService.analyzePosts();
    }
}