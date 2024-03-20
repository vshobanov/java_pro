package ru.stepup.spring.coins.core.api;

import ru.stepup.spring.coins.core.integrations.dtos.ProductsGetDtoRs;

import java.util.List;

public record GetProductsResponse(List<ProductsGetDtoRs> productsGetDtoRs) {

}
