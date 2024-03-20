package ru.inno.core.limits.configurations;

import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.inno.core.limits.services.LimitService;

@Configuration
@EnableScheduling
@Slf4j
public class SchedulersConfig {
    private final LimitService limitService;

    public SchedulersConfig(LimitService limitService) {
        this.limitService = limitService;
    }

    @Scheduled(cron = "${schedulers.restore-limit-task}")
    public void restoreLimit() {
        log.info("Restoring limits for clients");
        limitService.restoreLimit();
        log.info("Limits have been updated");
    }
}
