package com.kamal.metapostanalytics.dto;

import lombok.Data;
import java.util.List;

@Data
public class InstagramResponseDto {

    private List<MetaPostDto> data;
}