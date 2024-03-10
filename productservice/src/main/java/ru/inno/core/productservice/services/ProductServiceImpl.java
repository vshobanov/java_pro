package ru.inno.core.productservice.services;
import org.springframework.stereotype.Service;
import ru.inno.core.productservice.entities.ProductEntity;
import ru.inno.core.productservice.exceptions.BadRequestException;
import ru.inno.core.productservice.repository.ProductsRepository;

import java.util.List;

import static java.lang.Long.parseLong;

@Service
public class ProductServiceImpl implements ProductService {



    private final ProductsRepository repository;

    public ProductServiceImpl( ProductsRepository repository) {

        this.repository = repository;
    }

    @Override
    public List<ProductEntity> getProductsByUserId(Long id) {
        return repository.getProductsByUserId(id).stream().toList();
    }

    @Override
    public List<ProductEntity> getProductByProductId(Long id, String userId) {

        if (repository.getProductByProductIdAndUserId(id, parseLong(userId)).isEmpty()) {
            throw new BadRequestException("Не найдено продуктов по указанному запросу", "EMPTY_RESPONSE");

        }

        return repository.getProductByProductIdAndUserId(id, parseLong(userId)).stream().toList();
    }

    @Override
    public List<ProductEntity> getProducts() {
        return repository.findAll();
    }

    @Override
    public void addProduct(Long userid, ProductEntity product) {
        repository.save(product);
    }
}
