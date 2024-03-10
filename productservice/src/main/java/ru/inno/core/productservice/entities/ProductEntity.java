package ru.inno.core.productservice.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="products")
@Data
@NoArgsConstructor
public class ProductEntity {
    @Id
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "account")
    private Long accountNumber;
    @Column(name = "balans")
    private Long balans;
    @Column(name="product_type")
    @Enumerated(EnumType.STRING)
    AccType accType;
    @Column(name="user_id")
    private Long userId;

    public ProductEntity(long productId, long account, long balans, AccType productType) {
    }




    public enum AccType {card, account;}
}
