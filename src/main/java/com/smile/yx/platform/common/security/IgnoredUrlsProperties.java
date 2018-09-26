package com.smile.yx.platform.common.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * spring security 忽略url
 *
 * @author smile
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "security.ignore")
public class IgnoredUrlsProperties {

    private List<String> urls = new ArrayList<>();
}
