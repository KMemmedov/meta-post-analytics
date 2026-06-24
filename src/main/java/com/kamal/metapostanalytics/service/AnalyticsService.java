package com.kamal.metapostanalytics.service;

import com.kamal.metapostanalytics.client.MetaGraphClient;
import com.kamal.metapostanalytics.dto.AnalyticsResponseDto;
import com.kamal.metapostanalytics.dto.MetaPostDto;
import com.kamal.metapostanalytics.dto.TopPostDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    private final MetaGraphClient metaGraphClient;

    public AnalyticsService(MetaGraphClient metaGraphClient) {
        this.metaGraphClient = metaGraphClient;
    }

    public AnalyticsResponseDto analyzePosts() {
        List<MetaPostDto> posts = metaGraphClient.getPosts();

        List<TopPostDto> topPosts = posts.stream()
                .sorted(Comparator.comparingInt(this::calculateEngagement).reversed())
                .limit(3)
                .map(this::mapToTopPostDto)
                .toList();

        AnalyticsResponseDto response = new AnalyticsResponseDto();
        response.setTopPosts(topPosts);
        response.setBestDayForLikes(
                findBestDayForLikes(posts)
        );
        response.setSummary("Top 3 posts are calculated based on likes + comments.");

        return response;
    }

    private Integer calculateEngagement(MetaPostDto post) {
        return post.getLikeCount() + post.getCommentCount();
    }

    private TopPostDto mapToTopPostDto(MetaPostDto post) {
        TopPostDto dto = new TopPostDto();
        dto.setPostId(post.getId());
        dto.setMessage(post.getMessage());
        dto.setEngagement(calculateEngagement(post));
        return dto;
    }


    private String findBestDayForLikes(List<MetaPostDto> posts) {

        return posts.stream()
                .collect(Collectors.groupingBy(
                        post -> post.getCreatedTime().getDayOfWeek(),
                        Collectors.summingInt(MetaPostDto::getLikeCount)
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> entry.getKey().toString())
                .orElse("Unknown");
    }
    public List<MetaPostDto> getInstagramPosts() {
        return metaGraphClient.getPosts();
    }

}