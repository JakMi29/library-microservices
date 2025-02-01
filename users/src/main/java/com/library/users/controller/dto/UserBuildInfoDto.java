package com.library.users.controller.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "build-info")
public record UserBuildInfoDto(String message, Map<String, String> contactDetails) {
}
