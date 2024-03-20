package ru.inno.core.productservice.services;

import org.springframework.data.domain.Page;
import ru.inno.core.productservice.entities.ProductEntity;

import java.util.List;

public interface ProductService {
    Page<ProductEntity> getProductsByUserId(Long id);

    Page<ProductEntity> getProductByProductId(Long id, String userId);

    Page<ProductEntity> getProducts();

    void addProduct(Long userid, ProductEntity product);
}
