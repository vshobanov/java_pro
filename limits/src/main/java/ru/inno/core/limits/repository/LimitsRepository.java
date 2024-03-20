package ru.inno.core.limits.repository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.inno.core.limits.entities.LimitEntity;

import java.util.Optional;

public interface LimitsRepository extends JpaRepository<LimitEntity,Long> {
    Optional<LimitEntity> getLimitEntitiesByUserId(Long id);

    @Transactional
    @Query("insert into LimitEntity (userId, dailyLimit) VALUES (:id, 10000)")
     void addLimitEntitiesByUserId(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update LimitEntity set dailyLimit = :refund where userId = :id")
    void updateLimitEntitiesByUserId(@Param("id") Long id, @Param("refund") Long refund);

    @Transactional
    @Modifying
    @Value("${initial-limit-amount}")
    @Query("update LimitEntity set dailyLimit = :amount")
    void restoreLimitEntities(@Param("amount") Long amount);

}
