package ru.stepup.spring.coins.core.services;

import org.springframework.stereotype.Service;
import ru.stepup.spring.coins.core.api.GetProductsResponse;
import ru.stepup.spring.coins.core.integrations.ExecutorIntegration;
import ru.stepup.spring.coins.core.integrations.ProductIntegration;
import ru.stepup.spring.coins.core.integrations.ProductIntergationRestTemplate;
import ru.stepup.spring.coins.core.integrations.dtos.ProductsGetDtoRs;

@Service
public class ProductService {
    private final ProductIntegration productIntegration;

    public ProductService(ProductIntegration productIntegration) {
        this.productIntegration = productIntegration;
    }

    public GetProductsResponse getProducts(String userId) {
        GetProductsResponse response = productIntegration.getProducts(userId);
        return new GetProductsResponse(response.productsGetDtoRs());
    }

    public ProductsGetDtoRs[] getProduct(String userId, String productId) {
        ProductsGetDtoRs[] response = productIntegration.getProduct(userId, productId);
        return response;

    }
}
