package ru.inno.core.productservice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.inno.core.productservice.dtos.ProductEntityDto;
import ru.inno.core.productservice.entities.ProductEntity;
import ru.inno.core.productservice.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/products")
public class ProductsController {
    private static final Logger logger = LoggerFactory.getLogger(ProductsController.class.getName());
    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public List<ProductEntityDto> getProducts() {
        logger.info("Requested method  getProducts()");
        logger.info(String.valueOf(productService.getProducts()));
        //   return null;
        return productService.getProducts().stream().map(i -> new ProductEntityDto(i.getProductId(), i.getAccountNumber(), i.getBalans(), i.getAccType())).collect(Collectors.toList());
    }

    @GetMapping("/{productId}")
    public List<ProductEntityDto> getProductByProductId(@PathVariable Long productId, @RequestHeader(value = "USERID") String userId) {
        logger.info("Requested method getProductByProductId() with {} and userId {}", productId, userId);
        logger.info(String.valueOf(productService.getProductByProductId(productId, userId)));
        return productService.getProductByProductId(productId, userId).stream().map(i -> new ProductEntityDto(i.getProductId(), i.getAccountNumber(), i.getBalans(), i.getAccType())).collect(Collectors.toList());
    }

    @GetMapping("user/{userId}")
    public List<ProductEntityDto> getProductByUserId(@PathVariable Long userId) {
        logger.info("Requested method getProductByUserId() with {}", userId);
        logger.info(String.valueOf(productService.getProductsByUserId(userId)));
        return productService.getProductsByUserId(userId).stream().map(i -> new ProductEntityDto(i.getProductId(), i.getAccountNumber(), i.getBalans(), i.getAccType())).collect(Collectors.toList());
    }

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProducts(@PathVariable Long userId, @RequestBody ProductEntity product) {
        logger.info("Added ProductEntity {} for UserID {}", product.toString(), userId);
        productService.addProduct(userId, product);

    }

}
