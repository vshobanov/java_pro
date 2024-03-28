package ru.inno.core.limits.services;

import ru.inno.core.limits.api.LimitsRs;
import ru.inno.core.limits.entities.LimitEntity;

import java.util.List;
import java.util.Optional;

public interface LimitService {
    Optional<LimitEntity> getLimitByUserId(Long userId);

    LimitsRs updateLimit(Long userId, Long limit);

    void restoreLimit();

}
