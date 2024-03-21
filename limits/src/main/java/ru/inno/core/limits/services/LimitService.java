package ru.inno.core.limits.services;

import ru.inno.core.limits.api.LimitsRs;
import ru.inno.core.limits.entities.LimitEntity;

import java.util.List;

public interface LimitService {
    List<LimitEntity> getLimitByUserId(Long userId);

    LimitsRs updateLimit(Long userId, Long limit);

    void restoreLimit();

}
