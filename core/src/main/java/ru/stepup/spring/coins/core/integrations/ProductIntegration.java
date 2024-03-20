package ru.stepup.spring.coins.core.integrations;

import ru.stepup.spring.coins.core.api.GetProductsResponse;
import ru.stepup.spring.coins.core.integrations.dtos.ProductsGetDtoRs;

public interface ProductIntegration {

    GetProductsResponse getProducts(String userId);

    ProductsGetDtoRs[] getProduct(String userId, String productId);

}
