package ru.inno.core.limits.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LimitEntityDto {
    private Long userId;
    private Long dailyLimit;

    public LimitEntityDto(Long userId, Long dailyLimit) {
        this.userId = userId;
        this.dailyLimit = dailyLimit;
    }
}
