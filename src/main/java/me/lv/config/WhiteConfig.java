package me.lv.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author plume
 * @date 2021/8/26 10:41
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "white")
public class WhiteConfig {
    private List<String> ip;
}
