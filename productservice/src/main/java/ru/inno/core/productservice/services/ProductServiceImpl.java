package ru.inno.core.productservice.services;

import org.springframework.stereotype.Service;
import ru.inno.core.productservice.dtos.ProductDto;
import ru.inno.core.productservice.entities.ProductEntity;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDto productDto;

    public ProductServiceImpl(ProductDto productDto) {
        this.productDto = productDto;
    }

    @Override
    public List<ProductEntity> getProductsByUserId(Long id) {
        return productDto.getProductsByUserId(id);
    }

    @Override
    public List<ProductEntity> getProductByProductId(Long id) {
        return productDto.getProductByProductId(id);
    }

    @Override
    public List<ProductEntity> getProducts() {
        return productDto.getAllProducts();
    }

    @Override
    public void addProduct(Long userid, ProductEntity product) {
        productDto.addProductByUser(userid, product);
    }
}
