package ru.inno.core.productservice.dtos;

public class ProductEntityDto {

    private Long productId;
    private Long accountNumber;
    private Long balans;
    private String accType;

    public Long getProductId() {
        return productId;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public Long getBalans() {
        return balans;
    }

    public String getAccType() {
        return accType;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalans(Long balans) {
        this.balans = balans;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public ProductEntityDto(Long productId, Long accountNumber, Long balans, String accType) {
        this.productId = productId;
        this.accountNumber = accountNumber;
        this.balans = balans;
        this.accType = accType;
    }

    @Override
    public String toString() {
        return "ProductEntityDto{" +
                "productId=" + productId +
                ", accountNumber=" + accountNumber +
                ", balans=" + balans +
                ", accType='" + accType + '\'' +
                '}';
    }
}
