package ru.inno.core.limits.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="limits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LimitEntity {
    @Id
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "dailylimit")
    private Long dailyLimit;

}