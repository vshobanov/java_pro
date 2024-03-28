package ru.inno.core.limits.configurations.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "limits")
@Data
@AllArgsConstructor
public class LimitsProperties {
    private long initialLimitAmount;
}
