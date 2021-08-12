package com.techelevator;

import java.math.BigDecimal;

public class FoodItem {
    private String code;
    private String name;
    private BigDecimal price;
    private String type;
    private int stock = 5;

    public FoodItem() {
    }

    public FoodItem(String code, String name, BigDecimal price, String type) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public int removeItem() {
        if (stock > 0) {
            stock--;
        } else if (stock == 0) {
            System.out.println("Sold Out!");
        }
        return stock;
    }
    public int getStock() {
        return stock;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
