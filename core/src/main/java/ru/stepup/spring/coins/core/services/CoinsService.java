package ru.stepup.spring.coins.core.services;

import org.springframework.stereotype.Service;
import ru.stepup.spring.coins.core.api.ExecuteCoinsRequest;
import ru.stepup.spring.coins.core.api.ExecuteCoinsResponse;
import ru.stepup.spring.coins.core.api.GetProductsResponse;
import ru.stepup.spring.coins.core.exceptions.BadRequestException;
import ru.stepup.spring.coins.core.configurations.properties.CoreProperties;
import ru.stepup.spring.coins.core.exceptions.IntegrationErrorDto;
import ru.stepup.spring.coins.core.exceptions.IntegrationException;
import ru.stepup.spring.coins.core.integrations.dtos.ProductsGetDtoRs;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class CoinsService {
    private final CoreProperties coreProperties;
    private final ExecutorService executorService;
    private final ProductService productService;

    public CoinsService(CoreProperties coreProperties, ExecutorService executorService, ProductService productService) {
        this.coreProperties = coreProperties;
        this.executorService = executorService;
        this.productService = productService;
    }

    public ExecuteCoinsResponse execute(ExecuteCoinsRequest request, String userId) {
        if (coreProperties.getNumbersBlockingEnabled()) {
            if (coreProperties.getBlockedNumbers().contains(request.number())) {
                throw new BadRequestException("Указан заблокированный номер кошелька", "BLOCKED_ACCOUNT_NUMBER");
            }
        }
        ProductsGetDtoRs[] productsGetDtoRs = productService.getProduct(userId, request.productId());
        if (Arrays.stream(productsGetDtoRs).toList().isEmpty()) {
            throw new BadRequestException("Запрошен несуществующий номер продукта для клиента", "PRODUCT_NOT_AVAILABLE");
        }
        if (0 >= ((int) Arrays.stream(productsGetDtoRs).collect(Collectors.toList()).stream().findAny().get().balans())) {
            throw new BadRequestException("Недостаточно средств для проведения операции", "NOT_ENOUGH_BALANS");
        }
        ExecuteCoinsResponse response = executorService.execute(request);
        return response;
    }


    public GetProductsResponse getProducts(String userId) {
        return productService.getProducts(userId);
    }
}
