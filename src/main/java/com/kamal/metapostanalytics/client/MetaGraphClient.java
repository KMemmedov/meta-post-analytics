package com.kamal.metapostanalytics.client;

import com.kamal.metapostanalytics.config.MetaGraphProperties;
import com.kamal.metapostanalytics.dto.InstagramResponseDto;
import com.kamal.metapostanalytics.dto.MetaPostDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class MetaGraphClient {

    private final MetaGraphProperties metaGraphProperties;
    private final RestTemplate restTemplate = new RestTemplate();

    public MetaGraphClient(MetaGraphProperties metaGraphProperties) {
        this.metaGraphProperties = metaGraphProperties;
    }

    public List<MetaPostDto> getPosts() {

        String url =
                metaGraphProperties.getBaseUrl()
                        + "/me/media"
                        + "?fields=id,caption,media_type,timestamp,like_count,comments_count"
                        + "&access_token="
                        + metaGraphProperties.getAccessToken();

        InstagramResponseDto response =
                restTemplate.getForObject(url, InstagramResponseDto.class);

        return response.getData();
    }
}