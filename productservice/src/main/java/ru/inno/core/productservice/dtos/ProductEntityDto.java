package ru.inno.core.productservice.dtos;

import ru.inno.core.productservice.entities.ProductEntity;

public class ProductEntityDto {

    private Long productId;
    private Long accountNumber;
    private Long balans;
    private ProductEntity.AccType accType;

    public ProductEntityDto(Long productId, Long accountNumber, Long balans, ProductEntity.AccType accType) {
        this.productId = productId;
        this.accountNumber = accountNumber;
        this.balans = balans;
        this.accType = accType;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getBalans() {
        return balans;
    }

    public void setBalans(Long balans) {
        this.balans = balans;
    }

    public ProductEntity.AccType getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = ProductEntity.AccType.valueOf(accType);
    }
}
