package ru.inno.core.productservice.dao;

import ru.inno.core.productservice.entities.ProductEntity;

import java.util.List;

public interface ProductDao {

    List<ProductEntity> getAllProducts();

    List<ProductEntity> getProductsByUserId(Long id);

    List<ProductEntity> getProductByProductId(Long id, String userId);

    void addProductByUser(Long userid, ProductEntity product);

}

