package com.techelevator;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuditEntry {
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private String date;
    private BigDecimal balance;
    private String slotNumber;
    private String itemName;
    private BigDecimal itemPrice;
    private BigDecimal amount_change;
    private String type;

    public SimpleDateFormat getFormatter() {
        return formatter;
    }

    public void setFormatter(SimpleDateFormat formatter) {
        this.formatter = formatter;
    }

    public String getDate() {
        return date;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public BigDecimal getAmount_change() {
        return amount_change;
    }

    public AuditEntry(BigDecimal balance, BigDecimal change, String type) {
        this.balance = balance;
        this.amount_change = change;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.date = dtf.format(now);
        this.type = type;
    }

    public AuditEntry(BigDecimal balance, String slotNumber, String itemName, BigDecimal itemPrice, String type) {
        this.balance = balance;
        this.slotNumber = slotNumber;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.date = dtf.format(now);
        this.type = type;
    }

}
