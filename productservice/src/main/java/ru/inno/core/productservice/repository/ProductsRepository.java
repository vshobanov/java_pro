package ru.inno.core.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inno.core.productservice.entities.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository extends JpaRepository<ProductEntity,Long> {

    Optional<ProductEntity> getProductsByUserId(Long id);
    Optional<ProductEntity> getProductByProductIdAndUserId(Long id, Long userId);
}
