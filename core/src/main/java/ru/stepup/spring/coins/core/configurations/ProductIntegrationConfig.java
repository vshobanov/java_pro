package ru.stepup.spring.coins.core.configurations;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.stepup.spring.coins.core.configurations.properties.ExecutorProperties;
import ru.stepup.spring.coins.core.configurations.properties.ProductsProperties;
import ru.stepup.spring.coins.core.exceptions.RestTemplateResponseErrorHandler;
import ru.stepup.spring.coins.core.integrations.ExecutorIntegration;
import ru.stepup.spring.coins.core.integrations.ExecutorIntegrationRestTemplate;
import ru.stepup.spring.coins.core.integrations.ProductIntegration;
import ru.stepup.spring.coins.core.integrations.ProductIntergationRestTemplate;

@Configuration
public class ProductIntegrationConfig {

    @Bean
    public ProductIntegration productIntegration(
            ProductsProperties productsProperties,
            RestTemplateResponseErrorHandler restTemplateResponseErrorHandler
    ) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(productsProperties.getClient().getUrl())
                .setConnectTimeout(productsProperties.getClient().getConnectTimeout())
                .setReadTimeout(productsProperties.getClient().getReadTimeout())
                .errorHandler(restTemplateResponseErrorHandler)
                .build();
        return new ProductIntergationRestTemplate(restTemplate);
    }
}
