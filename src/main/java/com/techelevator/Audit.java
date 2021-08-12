package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Audit {
    private List<AuditEntry> auditData = new ArrayList<>();

    public List<AuditEntry> getAuditData() {
        return auditData;
    }

    public void createAuditFile() throws FileNotFoundException {
        File auditFile = new File("auditFile.txt");
        try (PrintWriter writer = new PrintWriter(auditFile)) {
            for (AuditEntry line : auditData) {
                if (line.getType().equalsIgnoreCase("feed money")) {
                    String entry = ("FEED MONEY " + line.getDate() + " | Change: " + line.getAmount_change() + " | Balance: " + line.getBalance());
                    writer.println(entry);
                } else if (line.getType().equalsIgnoreCase("purchase")) {
                    String entry = ("Purchase " + line.getDate() + " | Item: " + line.getItemName() + " | Slot: " + line.getSlotNumber() + " | Price: " + line.getItemPrice() + " | Balance: " + line.getBalance());
                    writer.println(entry);
                } else {
                    String entry = ("GIVE CHANGE " + line.getDate() + " | Change: " + line.getAmount_change() + " | Balance: " + line.getBalance());
                    writer.println(entry);
                }
            }
        }
    }


    public void addToAudit(AuditEntry data) {
        auditData.add(data);
    }


}
