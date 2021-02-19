package com.savannahinformatics.SavannahService.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("item")
    private String item;
    @JsonProperty("amount")
    private BigDecimal amount;

    public OrderRequest() {
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "item='" + item + '\'' +
                ", amount=" + amount +
                '}';
    }
}
