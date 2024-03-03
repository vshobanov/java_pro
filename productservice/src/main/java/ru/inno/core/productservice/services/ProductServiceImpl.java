package ru.inno.core.productservice.services;

import org.springframework.stereotype.Service;
import ru.inno.core.productservice.dao.ProductDao;
import ru.inno.core.productservice.entities.ProductEntity;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<ProductEntity> getProductsByUserId(Long id) {
        return productDao.getProductsByUserId(id);
    }

    @Override
    public List<ProductEntity> getProductByProductId(Long id) {
        return productDao.getProductByProductId(id);
    }

    @Override
    public List<ProductEntity> getProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public void addProduct(Long userid, ProductEntity product) {
        productDao.addProductByUser(userid, product);
    }
}
