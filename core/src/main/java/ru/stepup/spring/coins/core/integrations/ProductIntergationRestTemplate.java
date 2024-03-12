package ru.stepup.spring.coins.core.integrations;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import ru.stepup.spring.coins.core.api.GetProductsResponse;
import ru.stepup.spring.coins.core.exceptions.BadRequestException;
import ru.stepup.spring.coins.core.integrations.dtos.ProductsGetDtoRs;

import java.util.List;
@Slf4j
public class ProductIntergationRestTemplate implements ProductIntegration {
    private final RestTemplate restTemplate;



    public ProductIntergationRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public GetProductsResponse getProducts(String userId) {

        ProductsGetDtoRs[] prodArray = restTemplate.getForObject("/user/{userId}", ProductsGetDtoRs[].class, userId);
        GetProductsResponse response = new GetProductsResponse(List.of(prodArray));
        log.info("response: {}", response);
        return response;
    }

    @Override
    public ProductsGetDtoRs[] getProduct(String userId, String productId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("USERID", userId);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        log.info(String.valueOf(restTemplate.exchange("/{productId}", HttpMethod.GET, entity, ProductsGetDtoRs[].class, productId).getStatusCode().is2xxSuccessful()));
        ProductsGetDtoRs[] prodArray = restTemplate.exchange("/{productId}", HttpMethod.GET, entity, ProductsGetDtoRs[].class, productId).getBody();
        GetProductsResponse response = new GetProductsResponse(List.of(prodArray));
        log.info("ProductService response: {}", response);
        return prodArray;
    }
}
