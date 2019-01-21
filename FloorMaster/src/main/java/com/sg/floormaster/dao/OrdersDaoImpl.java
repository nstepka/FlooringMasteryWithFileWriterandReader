/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Orders;
import com.sg.floormaster.dto.Product;
import com.sg.floormaster.dto.Taxes;
import com.sg.floormaster.view.UserIO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author nstep
 */
public class OrdersDaoImpl implements OrdersDao {

    public static final String DELIMITER = ",";

    private UserIO io;
    private List<Orders> order = new ArrayList<>();
    Scanner scanner;
    public String dateOfFile;
    public int currentOrder;
    String orderCounter = "OrderCounter.txt";
    LocalDate date = LocalDate.now();
    public int manager;
    Taxes currentState = new Taxes();
    Product currentProduct = new Product();

    public OrdersDaoImpl() {
    }

    //add an order to the doa.  gets sent in with a completed order 
    //uses the order size to be able to assign it to the right spot..
    //havent tested it yet, but i assume it writes at line 0, if not i think
    //i need to add +1 to orderSize
    @Override
    public void addOrder(Orders newOrder) throws OrdersDaoException, IOException {
        
        newOrder.setOrderNumber(getOrderCounter());
        this.dateOfFile = date.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        loadOrders(dateOfFile);
        order.add(newOrder);
        try {
            writeOrders();
        } catch (IOException ex) {
            throw new IOException("Could not save to file!", ex);
        }

    }

    @Override
    public void removeOrder(String date, int orderNumber, Orders userOrder) throws OrdersDaoException, IOException {
        this.dateOfFile = date;
        userOrder.setOrderNumber(orderNumber);
        loadOrders(date);
        for (int x = 0; x < order.size(); x++) {
            if (order.get(x).getOrderNumber() == orderNumber) {
                order.remove(x);
            }
        }

        writeOrders();

    }
// user sends in the date, orderNumber, and the new order to be
// to be changed/saved in memory until later save.  I believe
// this will make it so it rewrites over the currnt line...
// does this solve charlies problem... or my future problem?

    @Override
    public void editOrder(String date, int orderNumber, Orders userOrder) throws OrdersDaoException, IOException {
        this.dateOfFile = date;
        userOrder.setOrderNumber(orderNumber);
        loadOrders(date);
        for (int x = 0; x < order.size(); x++) {
            if (order.get(x).getOrderNumber() == orderNumber) {
                order.set(x, userOrder);
            }
        }

        try {
            writeOrders();
        } catch (IOException ex) {
            throw new IOException("Could not save order!", ex);
        }
    }

    //gets an order from the date the user searches and the number
    // of the order in the spot it should be
    @Override
    public Orders getOrder(String localDate, int orderNumber) throws OrdersDaoException {
        this.dateOfFile = localDate;
        loadOrders(localDate);
        ///For if loop to cycle the clist to find the actual spot?
        for (int x = 0; x < order.size(); x++) {
            if (order.get(x).getOrderNumber() == orderNumber) {
                return order.get(x);
            }
        }
        return null;

    }

    private int trainingOrManager() throws FileNotFoundException {
        String orderCounter = "Mode.txt";
        Scanner sc = null;
        File file = new File(orderCounter);
        sc = new Scanner(
                new BufferedReader(new FileReader(orderCounter)));
        String currentLine;
        String[] currentTokens1;
        int inMethodCounter = 0;
        while (sc.hasNextLine()) {
            // get the next line in the file
            currentLine = sc.nextLine();
            currentTokens1 = currentLine.split(DELIMITER);
            inMethodCounter = Integer.parseInt(currentTokens1[0]);
            this.manager = inMethodCounter;
        }
        sc.close();
        return manager;
    }

    @Override
    public int getOrderCounter() throws FileNotFoundException, IOException {
        String orderCounter = "OrderCounter.txt";
        Scanner sc = null;
        File file = new File(orderCounter);
        sc = new Scanner(
                new BufferedReader(new FileReader(orderCounter)));
        String currentLine;
        String[] currentTokens1;
        int inMethodCounter;
        while (sc.hasNextLine()) {
            // get the next line in the file
            currentLine = sc.nextLine();
            currentTokens1 = currentLine.split(DELIMITER);
            inMethodCounter = Integer.parseInt(currentTokens1[0]);
            this.currentOrder = inMethodCounter;
        }
        sc.close();

        return this.currentOrder;
    }

    //pulls the order out from a file to be saved in memory of the doa
    // tosses a you screwed up if it cant find the file from that day
    //date has to be changed to a string before it sends to this... service?
    public void loadOrders(String date) throws OrdersDaoException {

        //added at 6pm thursday makes it so it doesnt repeat the order...
        order = new ArrayList<>();
        String ORDER_FILE = "Orders_" + date + ".txt";
        Scanner sc = null;
        File file = new File(ORDER_FILE);
        if (file.exists() == false) {
            System.out.println("Could not find the order!");
        }
        try {
            sc = new Scanner(
                    new BufferedReader(new FileReader(ORDER_FILE)));
        } catch (FileNotFoundException ex) {
            throw new OrdersDaoException("Could not find orders for that day", ex);
        }
        String currentLine;
        String[] currentTokens;
        Orders currentOrder;

        while (sc.hasNextLine()) {
            // get the next line in the file
            currentLine = sc.nextLine();
            // break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);
            currentOrder = new Orders();
            currentOrder.setOrderNumber(Integer.parseInt(currentTokens[0]));
            currentOrder.setCustomerName(currentTokens[1]);
            currentState.setStateName(currentTokens[2]);
           

            currentState.setTaxRate(new BigDecimal(currentTokens[3])); //
            
            currentProduct.setProductName(currentTokens[4]);
         

            currentOrder.setArea(new BigDecimal(currentTokens[5]));
            currentProduct.setCostPerSqFoot(new BigDecimal(currentTokens[6]));
            currentProduct.setLaborCostPerSqFoot(new BigDecimal(currentTokens[7]));
       
            currentOrder.setMaterialCost(new BigDecimal(currentTokens[8]));
            currentOrder.setLaborCost(new BigDecimal(currentTokens[9]));
            currentOrder.setTotalTax(new BigDecimal(currentTokens[10]));
            currentOrder.setTotalCost(new BigDecimal(currentTokens[11]));

            currentOrder.setState(currentState);
            currentOrder.setProduct(currentProduct);
            order.add(currentOrder);

        }
        sc.close();
    }

    public void writeOrders() throws IOException {
        trainingOrManager();
        getOrderCounter();
        if (manager == 1) {
            PrintWriter out = null;
            try {
                out = new PrintWriter(new FileWriter("Orders_" + dateOfFile + ".txt"));
            } catch (IOException ex) {
                throw new IOException("Could not load writer for file date", ex);
            }
            String CurrentLine;
            String[] currentTokens;
            for (Orders currentOrder : order) {
                out.println(currentOrder.getOrderNumber() + DELIMITER
                        + currentOrder.getCustomerName() + DELIMITER
                        + currentOrder.getState().getStateName() + DELIMITER
                        + currentOrder.getState().getTaxRate().toString() + DELIMITER
                        + currentOrder.getProduct().getProductName() + DELIMITER
                        + currentOrder.getArea().toString() + DELIMITER
                        + currentOrder.getProduct().getCostPerSqFoot() + DELIMITER
                        + currentOrder.getProduct().getLaborCostPerSqFoot() + DELIMITER
                        + currentOrder.getMaterialCost().toString() + DELIMITER
                        + currentOrder.getLaborCost().toString() + DELIMITER
                        + currentOrder.getTotalTax().toString() + DELIMITER
                        + currentOrder.getTotalCost().toString());

                out.flush();
            }
            out.close();

            try {
                out = new PrintWriter(new FileWriter(orderCounter));
            } catch (IOException ex) {
                throw new IOException("Could not load order numbers", ex);
            }
            this.currentOrder++;
            out.print(currentOrder);
            out.flush();
            out.close();
            order = new ArrayList<>();
        }
    }

    @Override
    public List<Orders> getAllOrders(String Date) throws OrdersDaoException {
        try {
            loadOrders(Date);
        } catch (OrdersDaoException ex) {
            throw new OrdersDaoException("Could not find orders for that day", ex);
        }
        return order;

    }
}
