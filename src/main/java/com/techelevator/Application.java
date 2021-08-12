package com.techelevator;

import com.techelevator.view.MenuDrivenCLI;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Application {

    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String MAIN_MENU_OPTION_SALES_REPORT = "";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_SALES_REPORT};

    private static final String SUB_MENU_OPTION_FEED_MONEY = "Feed money";
    private static final String SUB_MENU_OPTION_SELECT_PRODUCT = "Select product";
    private static final String SUB_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] SUB_MENU_OPTIONS = {SUB_MENU_OPTION_FEED_MONEY, SUB_MENU_OPTION_SELECT_PRODUCT, SUB_MENU_OPTION_FINISH_TRANSACTION};

    private final MenuDrivenCLI ui = new MenuDrivenCLI();
    private Scanner userInput = new Scanner(System.in);
    private VendingMachine vm = new VendingMachine();

    public static void main(String[] args) throws FileNotFoundException {
        Application application = new Application();
        application.run();
    }

    public void run() throws FileNotFoundException {
        File inputFile = new File("inventory.txt");
        vm.populateInventory(inputFile);
        System.out.println("  _   __            __         __  ___     __  _       ____ ___  ___ \n | | / /__ ___  ___/ /__  ____/  |/  /__ _/ /_(_)___  / __// _ \\/ _ \\\n | |/ / -_) _ \\/ _  / _ \\/___/ /|_/ / _ `/ __/ / __/ / _ \\/ // / // /\n |___/\\__/_//_/\\_,_/\\___/   /_/  /_/\\_,_/\\__/_/\\__/  \\___/\\___/\\___/ \n                                                                     ");
        while (true) {
            String selection = ui.promptForSelection(MAIN_MENU_OPTIONS);
            switch (selection) {
                case MAIN_MENU_OPTION_DISPLAY_ITEMS:
                    // display vending machine items
                    vm.printInventory();
                    break;
                case MAIN_MENU_OPTION_PURCHASE:
                    subMenu();

                    // do purchase
                    break;
                case MAIN_MENU_OPTION_EXIT:
                    vm.getAudit().createAuditFile();
                    System.exit(0);
                case MAIN_MENU_OPTION_SALES_REPORT:
                    vm.getSalesReport().totalSalesCalculated();
                    default:

                    break;
            }
        }
    }

    public void subMenu() {
        label:
        while (true) {
            System.out.println();
            String subSelection = ui.promptForSelection(SUB_MENU_OPTIONS);

            switch (subSelection) {
                case SUB_MENU_OPTION_FEED_MONEY:
                    System.out.println("How Much To Deposit?");
                    BigDecimal deposit = new BigDecimal(userInput.nextLine());
                    // if your balance has a decimal in it and you deposit more money on top, it removes the value after the decimal
                    vm.feedMoney(deposit);
                    System.out.format("Balance: $%.2f", vm.getBalance());
                    break;
                case SUB_MENU_OPTION_SELECT_PRODUCT:
                    // first time you select this it does not allow an input
                    vm.printInventory();
                    System.out.println("Select Product: ");
                    String selectedCode = userInput.nextLine();
                    vm.addToCart(selectedCode);
                    System.out.format("Balance: $%.2f", vm.getBalance());
                    break;
                case SUB_MENU_OPTION_FINISH_TRANSACTION:
                    vm.getChange();
                    break label;
            }
        }
    }


}
