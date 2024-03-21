package ru.inno.core.limits.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.inno.core.limits.api.LimitsRq;
import ru.inno.core.limits.api.LimitsRs;
import ru.inno.core.limits.dtos.LimitEntityDto;
import ru.inno.core.limits.dtos.PageDto;
import ru.inno.core.limits.services.LimitService;

import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/v1/limits")
public class LimitsController {

    private final LimitService limitService;

    public LimitsController(LimitService limitService) {
        this.limitService = limitService;
    }

    @GetMapping
    public PageDto<LimitEntityDto> getLimitsByUserId(@RequestHeader(value = "USERID") String userId) {
        log.info("Requested method getLimitsByUserId() with userId {}", userId);
        log.info(String.valueOf(limitService.getLimitByUserId(Long.valueOf(userId))));
        return new PageDto<>(limitService.getLimitByUserId(Long.valueOf(userId)).stream().map(i -> new LimitEntityDto(i.getUserId(), i.getDailyLimit())).collect(Collectors.toList()));
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public LimitsRs update(@RequestHeader(value = "USERID") String userId, @RequestBody LimitsRq request) {
        log.info("Updating limit for {} initiated", userId);
        return limitService.updateLimit(Long.valueOf(userId), request.paymentAmount());
    }

    @PostMapping("/payment")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public LimitsRs payment(@RequestHeader(value = "USERID") String userId, @RequestBody LimitsRq request) {
        log.info("Updating limit for {} initiated", userId);
        return limitService.updateLimit(Long.valueOf(userId), request.paymentAmount());
    }


}


