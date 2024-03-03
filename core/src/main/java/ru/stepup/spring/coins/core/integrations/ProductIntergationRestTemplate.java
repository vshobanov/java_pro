package ru.stepup.spring.coins.core.integrations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import ru.stepup.spring.coins.core.api.GetProductsResponse;
import ru.stepup.spring.coins.core.integrations.dtos.ProductsGetDtoRs;

import java.util.List;

public class ProductIntergationRestTemplate implements ProductIntegration {
    private final RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ExecutorIntegrationRestTemplate.class.getName());

    public ProductIntergationRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public GetProductsResponse getProducts(String userId) {

        ProductsGetDtoRs[] prodArray = restTemplate.getForObject("/user/{userId}", ProductsGetDtoRs[].class, userId);
        GetProductsResponse response = new GetProductsResponse(List.of(prodArray));
        logger.info("response: {}", response);
        return response;
    }

    @Override
    public ProductsGetDtoRs[] getProduct(String userId, String productId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("USERID", userId);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ProductsGetDtoRs[] prodArray = restTemplate.exchange("/{productId}", HttpMethod.GET, entity, ProductsGetDtoRs[].class, productId).getBody();
        GetProductsResponse response = new GetProductsResponse(List.of(prodArray));
        logger.info("ProductService response: {}", response);
        return prodArray;
    }
}
