package ru.stepup.spring.coins.core.controllers;

import org.springframework.web.bind.annotation.*;
import ru.stepup.spring.coins.core.api.ExecuteCoinsRequest;
import ru.stepup.spring.coins.core.api.ExecuteCoinsResponse;
import ru.stepup.spring.coins.core.api.GetProductsResponse;
import ru.stepup.spring.coins.core.services.CoinsService;

@RestController
@RequestMapping("/api/v1/coins")
public class CoinsController {
    private final CoinsService coinsService;

    public CoinsController(CoinsService coinsService) {
        this.coinsService = coinsService;
    }

    @PostMapping("/execute")
    public ExecuteCoinsResponse execute(@RequestBody ExecuteCoinsRequest request, @RequestHeader(value = "USERID") String userId) {
        return coinsService.execute(request, userId);
    }

    @GetMapping("/products")
    public GetProductsResponse getProducts(@RequestHeader(value = "USERID") String userId) {
        return coinsService.getProducts(userId);
    }

}
