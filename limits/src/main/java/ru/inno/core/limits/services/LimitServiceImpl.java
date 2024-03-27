package ru.inno.core.limits.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.inno.core.limits.api.LimitsRs;
import ru.inno.core.limits.configurations.properties.LimitsProperties;
import ru.inno.core.limits.entities.LimitEntity;
import ru.inno.core.limits.exceptions.BadRequestException;
import ru.inno.core.limits.repository.LimitsRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LimitServiceImpl implements LimitService {

    private final LimitsRepository limitsRepository;
    private final LimitsProperties limitsProperties;


    @Override
    public List<LimitEntity> getLimitByUserId(Long userId) {
        List<LimitEntity> limitEntities = limitsRepository.getLimitEntitiesByUserId(userId).stream().toList();
        if (limitEntities.isEmpty()) {
            limitsRepository.addLimitEntitiesByUserId(userId, limitsProperties.getInitialLimitAmount());
            log.info("Added record for new user {}", userId);
            limitEntities = limitsRepository.getLimitEntitiesByUserId(userId).stream()
                    .toList();
        }
        return limitEntities;
    }

    @Override
    @Transactional
    public LimitsRs updateLimit(Long userId, Long limit) {
        List<LimitEntity> limitEntities = getLimitByUserId(userId);
        long currLimit = limitEntities.stream().map(LimitEntity::getDailyLimit).findFirst().get() + limit;
        if (currLimit <= 0) {
            throw new BadRequestException("Insufficient limit to carry out a transaction in requested amount", "INSUFFICIENT_AMOUNT_LIMIT");
        }
        if (currLimit > limitsProperties.getInitialLimitAmount()) {
            throw new BadRequestException(String.format("The maximum limit value - %2d has been reached", limitsProperties.getInitialLimitAmount()), "MAX_LIMIT_REACHED");
        }
        limitsRepository.updateLimitEntitiesByUserId(userId, currLimit);
        return new LimitsRs(currLimit, "LIMIT_UPDATED", String.format("Limit successfully updated at %2d", limit));
    }

    @Override
    public void restoreLimit() {
        limitsRepository.restoreLimitEntities(limitsProperties.getInitialLimitAmount());
        log.info("Limits have been updated for all clients. New limit is {}", limitsProperties.getInitialLimitAmount());

    }


}
