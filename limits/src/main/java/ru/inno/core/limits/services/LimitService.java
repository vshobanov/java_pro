package ru.inno.core.limits.services;

import ru.inno.core.limits.entities.LimitEntity;

import java.util.List;

public interface LimitService {
    List<LimitEntity> getLimitByUserId(Long userId);
    void updateLimit (Long userId, Long limit);
    void restoreLimit ();
    void addLimit (Long userId);

}
