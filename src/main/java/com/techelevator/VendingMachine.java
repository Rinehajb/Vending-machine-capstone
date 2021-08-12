package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    private List<FoodItem> inventory = new ArrayList<>();
    private BigDecimal balance = new BigDecimal(0);
    private List<FoodItem> shoppingCart = new ArrayList<>();
    private Audit audit = new Audit();
    private SalesReport salesReport = new SalesReport();

    public Audit getAudit() {
        return audit;
    }

    public void feedMoney(BigDecimal deposit) {
        balance = balance.add(deposit);
        AuditEntry moneyFeed = new AuditEntry(deposit, balance, "feed money");
        audit.addToAudit(moneyFeed);
    }



    public void printInventory() {
        System.out.format("| %2s|   %-20s | $%5s |\n", "Code", "Item", "Price");
        for (FoodItem item : inventory) {
            if (item.getStock() == 0) {
                System.out.format("| %3s |   %-20s | $%5s  |\n", item.getCode(), item.getName(), "SOLD OUT");
            } else {
                System.out.format("| %3s |   %-20s | $%.2f  |\n", item.getCode(), item.getName(), item.getPrice());
            }
        }

    }


    public void populateInventory(File inputFile) {
        try (Scanner fileScanner = new Scanner(inputFile)) {
            while (fileScanner.hasNextLine()) {
                FoodItem newItem = new FoodItem();
                String line = fileScanner.nextLine();
                String[] lineArr = line.split("\\|");
                newItem.setCode(lineArr[0]);
                newItem.setName(lineArr[1]);
                newItem.setPrice(new BigDecimal(lineArr[2]));
                newItem.setType(lineArr[3]);
                inventory.add(newItem);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void addToCart(String selection) {
        for (FoodItem item : inventory) {
            if (item.getCode().equalsIgnoreCase(selection)) {
                if (item.getStock() > 0) {
                    if (item.getPrice().compareTo(getBalance()) <= 0) {
                        balance = balance.subtract(item.getPrice());
                        item.removeItem();
                        System.out.println(item.getName());
                        if (item.getType().equals("Chip")) {
                            System.out.println("Crunch Crunch, Yum!");
                        } else if (item.getType().equals("Candy")) {
                            System.out.println("Munch Munch, Yum!");
                        } else if (item.getType().equals("Drink")) {
                            System.out.println("Glug Glug, Yum!");
                        } else {
                            System.out.println("Chew Chew, Yum!");
                        }
                        AuditEntry purchaseItem = new AuditEntry(balance, item.getCode(), item.getName(), item.getPrice(), "purchase");
                        audit.addToAudit(purchaseItem);
                        if(!salesReport.getFoodItemsSold().contains(item)) {
                            salesReport.addToList(item);
                        }
                    } else {
                        System.out.println(ANSI_RED + "Insufficient Funds" + ANSI_RESET);
                    }
                } else if (item.getStock() == 0) {
                    item.removeItem();
                }

            }
        }
    }

    public void getChange() {
        int pennyBalance = (int) (balance.doubleValue() * 100);
        int numQuarters = pennyBalance / 25;
        int numDimes = (pennyBalance - (numQuarters * 25)) / 10;
        int leftover = pennyBalance - (numDimes * 10 + numQuarters * 25);
        int numNickel;
        if (leftover > 0) {
            // this can output as a long decimal and equal greater than .05
            numNickel = 1;
            System.out.println("Your change is $" + balance + " in " + numQuarters + " quarters, " + numDimes + " dimes, and " + numNickel + " nickels.");
        } else if (numDimes > 0) {
            System.out.println("Your change is $" + balance + " in " + numQuarters + " quarters and " + numDimes + " dimes.");
        } else {
            System.out.println("Your change is $" + balance + " in " + numQuarters + " quarters.");
        }
        BigDecimal changeBalance = balance;
        balance = balance.subtract(balance);
        AuditEntry giveChange = new AuditEntry(balance, changeBalance, "give change");
        audit.addToAudit(giveChange);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public SalesReport getSalesReport() {
        return salesReport;
    }
}

