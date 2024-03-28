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
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LimitServiceImpl implements LimitService {

    private final LimitsRepository limitsRepository;
    private final LimitsProperties limitsProperties;


    @Override
    public Optional<LimitEntity> getLimitByUserId(Long userId) {
        Optional<LimitEntity> limitEntity = limitsRepository.getLimitEntitiesByUserId(userId);
        if (limitEntity.isEmpty()) {
            LimitEntity  newlimitEntity = new LimitEntity(userId,limitsProperties.getInitialLimitAmount() );
            limitsRepository.addLimitEntitiesByUserId(newlimitEntity.getUserId(), newlimitEntity.getDailyLimit());
            log.info("Added record for new user {}", userId);
            limitEntity = Optional.of(newlimitEntity);
        }
        return limitEntity;
    }

    @Override
    @Transactional
    public LimitsRs updateLimit(Long userId, Long limit) {
        Optional<LimitEntity> limitEntity = getLimitByUserId(userId);
        long currLimit = limitEntity.get().getDailyLimit() + limit;
        if (currLimit <= 0) {
            throw new BadRequestException("Insufficient limit to carry out a transaction in requested amount", "INSUFFICIENT_AMOUNT_LIMIT");
        }
        if (currLimit > limitsProperties.getInitialLimitAmount()) {
            currLimit = limitsProperties.getInitialLimitAmount();
            log.warn("Updating the limit exceeding the maximum value has been requested. The limit has been adjusted to the maximum value ");
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
