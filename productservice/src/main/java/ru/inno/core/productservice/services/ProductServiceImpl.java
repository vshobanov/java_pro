package ru.inno.core.productservice.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.inno.core.productservice.dao.ProductDao;
import ru.inno.core.productservice.entities.ProductEntity;
import ru.inno.core.productservice.exceptions.BadRequestException;

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
    public List<ProductEntity> getProductByProductId(Long id, String userId) {

       if  (productDao.getProductByProductId(id, userId).isEmpty()) {
           throw new BadRequestException("Не найдено продуктов по указанному запросу","EMPTY_RESPONSE");

       }

        return productDao.getProductByProductId(id, userId);
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
