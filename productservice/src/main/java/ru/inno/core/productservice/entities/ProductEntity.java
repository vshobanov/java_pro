package ru.inno.core.productservice.entities;

import java.util.UUID;

public class ProductEntity {
    private Long productId;
    private Long accountNumber;
    private Long balans;
    private String accType;

    public ProductEntity(Long productId, Long accountNumber, Long balans, String accType) {
        this.productId = productId;
        this.accountNumber = accountNumber;
        this.accType = accType;
        this.balans = balans;
    }

    public ProductEntity() {

    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public Long getBalans() {
        return balans;
    }

    public void setBalans(Long balans) {
        this.balans = balans;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + productId +
                ", accountNumber=" + accountNumber +
                ", accType='" + accType + '\'' +
                ", balans=" + balans +
                '}';
    }
}
