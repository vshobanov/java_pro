package ru.inno.core.limits.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.inno.core.limits.entities.LimitEntity;
import ru.inno.core.limits.repository.LimitsRepository;

import java.util.List;

@Service
@Slf4j
public class LimitServiceImpl implements LimitService{

    private final LimitsRepository limitsRepository;

    public LimitServiceImpl(LimitsRepository limitsRepository) {
        this.limitsRepository = limitsRepository;
    }

    @Value("${initial-limit-amount}")
    private Long amount;

    @Override
    public List<LimitEntity> getLimitByUserId(Long userId) {
        if (limitsRepository.getLimitEntitiesByUserId(userId).stream().toList().isEmpty())
        {
            addLimit(userId);
            log.info("Added record for new user {}",userId);
        }
        return limitsRepository.getLimitEntitiesByUserId(userId).stream().toList();
    }

    @Override
    public void updateLimit(Long userId, Long limit) {
        Long currLimit = limitsRepository.getLimitEntitiesByUserId(userId).get().getDailyLimit() - limit ;
        limitsRepository.updateLimitEntitiesByUserId(userId, currLimit);
    }

    @Override
    public void restoreLimit() {
        limitsRepository.restoreLimitEntities(amount);
        log.info("Limits have been updated for all clients. New limit is {}", amount);

    }

    @Override
    public void addLimit(Long userId) {
        limitsRepository.addLimitEntitiesByUserId(userId);
    }
}
