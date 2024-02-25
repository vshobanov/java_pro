package ru.inno.core.productservice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.inno.core.productservice.entities.ProductEntity;
import ru.inno.core.productservice.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductsController {
    private static final Logger logger = LoggerFactory.getLogger(ProductsController.class.getName());
    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public List<ProductEntity> getProducts() {
        logger.info("Requested method  getProducts()");
        logger.info(String.valueOf(productService.getProducts()));
        //   return null;
        return productService.getProducts();
    }

    @GetMapping("/{product_id}")
    public List<ProductEntity> getProductByProductId(@PathVariable Long product_id) {
        logger.info("Requested method getProductByProductId() with {}", product_id);
        logger.info(String.valueOf(productService.getProductByProductId(product_id)));
        //   return null;
        return productService.getProductByProductId(product_id);
    }

    @GetMapping("user/{user_id}")
    public List<ProductEntity> getProductByUserId(@PathVariable Long user_id) {
        logger.info("Requested method getProductByUserId() with {}", user_id);
        logger.info(String.valueOf(productService.getProductsByUserId(user_id)));
        return productService.getProductsByUserId(user_id);
    }

    @PostMapping("/{user_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProducts(@PathVariable Long user_id,@RequestBody ProductEntity product) {
        logger.info("Added ProductEntity {} for UserID {}", product.toString(), user_id);
        productService.addProduct(user_id,product);

    }

}
