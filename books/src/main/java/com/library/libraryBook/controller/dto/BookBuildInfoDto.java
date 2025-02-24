package com.library.libraryBook.controller.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "build-info")
public class  BookBuildInfoDto {
    private String message;
    private Map<String, String> contactDetails;
}
