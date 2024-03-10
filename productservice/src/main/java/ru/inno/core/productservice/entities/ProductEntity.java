package ru.inno.core.productservice.entities;


public class ProductEntity {

    AccType accType;
    private Long productId;
    private Long accountNumber;
    private Long balans;

    public ProductEntity(Long productId, Long accountNumber, Long balans, AccType accType) {
        this.productId = productId;
        this.accountNumber = accountNumber;
        this.balans = balans;
        this.accType = accType;
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

    public AccType getAccType() {
        return accType;
    }

    public void setAccType(AccType accType) {
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

    public enum AccType {card, account;}
}
