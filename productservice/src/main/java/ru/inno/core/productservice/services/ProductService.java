package ru.inno.core.productservice.services;

import ru.inno.core.productservice.entities.ProductEntity;

import java.util.List;

public interface ProductService {
    List<ProductEntity> getProductsByUserId(Long id);

    List<ProductEntity> getProductByProductId(Long id);

    List<ProductEntity> getProducts();

    void addProduct(Long userid, ProductEntity product);
}
