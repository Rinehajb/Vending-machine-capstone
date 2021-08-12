package com.techelevator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SalesReport {
    private String name;
    private int amountSold;
    private BigDecimal price;
    private List<FoodItem> foodItemsSold = new ArrayList<>();

    public SalesReport(String name, int amountSold, BigDecimal price) {
        this.name = name;
        this.amountSold = amountSold;
        this.price = price;
    }
    public SalesReport(){

    }
    public List<FoodItem> getFoodItemsSold() {
        return foodItemsSold;
    }
    public void totalSalesCalculated(){
        BigDecimal grossSales = new BigDecimal(0);

        for(FoodItem item : foodItemsSold){
            int amountSold = 5-item.getStock();
            BigDecimal itemProfit = item.getPrice().multiply(BigDecimal.valueOf(amountSold));
           grossSales = grossSales.add(itemProfit);
            System.out.println("Item "+ item.getName()+ " Amount Sold " + amountSold);
        }
        System.out.println("\n**TOTAL SALES** $"+grossSales);

    }
    public void addToList(FoodItem item){

        foodItemsSold.add(item);
    }
}
