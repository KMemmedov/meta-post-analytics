package com.kamal.metapostanalytics.service;

import com.kamal.metapostanalytics.client.MetaGraphClient;
import com.kamal.metapostanalytics.dto.AnalyticsResponseDto;
import com.kamal.metapostanalytics.dto.MetaPostDto;
import com.kamal.metapostanalytics.dto.TopPostDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class AnalyticsService {

    private final MetaGraphClient metaGraphClient;

    public AnalyticsService(MetaGraphClient metaGraphClient) {
        this.metaGraphClient = metaGraphClient;
    }

    public AnalyticsResponseDto analyzePosts() {
        List<MetaPostDto> posts = getMockPosts();

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

    private List<MetaPostDto> getMockPosts() {
        MetaPostDto post1 = new MetaPostDto();
        post1.setId("1");
        post1.setMessage("Java backend post");
        post1.setCreatedTime(LocalDateTime.now().minusDays(1));
        post1.setLikeCount(120);
        post1.setCommentCount(30);

        MetaPostDto post2 = new MetaPostDto();
        post2.setId("2");
        post2.setMessage("Spring Boot tips");
        post2.setCreatedTime(LocalDateTime.now().minusDays(2));
        post2.setLikeCount(200);
        post2.setCommentCount(40);

        MetaPostDto post3 = new MetaPostDto();
        post3.setId("3");
        post3.setMessage("PostgreSQL performance");
        post3.setCreatedTime(LocalDateTime.now().minusDays(3));
        post3.setLikeCount(90);
        post3.setCommentCount(20);

        MetaPostDto post4 = new MetaPostDto();
        post4.setId("4");
        post4.setMessage("REST API design");
        post4.setCreatedTime(LocalDateTime.now().minusDays(4));
        post4.setLikeCount(180);
        post4.setCommentCount(50);

        return List.of(post1, post2, post3, post4);
    }

    private String findBestDayForLikes(List<MetaPostDto> posts) {

        return posts.stream()
                .max(Comparator.comparingInt(MetaPostDto::getLikeCount))
                .map(post -> post.getCreatedTime()
                        .getDayOfWeek()
                        .toString())
                .orElse("Unknown");
    }

}