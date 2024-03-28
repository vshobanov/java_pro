package ru.inno.core.limits.configurations.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        LimitsProperties.class
})
public class PropertiesConfig {
}
