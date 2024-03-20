package ru.inno.core.limits.dtos;

import lombok.Data;

import java.util.List;
@Data
public class PageDto<T> {
    private List<T> content;

    public PageDto(List<T> content) {
        this.content = content;
    }
}
