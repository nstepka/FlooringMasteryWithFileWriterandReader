/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.view;

import com.sg.floormaster.dao.OrdersDaoException;
import com.sg.floormaster.dto.Orders;
import com.sg.floormaster.dto.Product;
import com.sg.floormaster.dto.Taxes;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author nstep
 */
public class View {

    UserIO io;

    public View(UserIO io) {
        this.io = io;
    }

    public int displayMainMenu() {
        io.print("<<Flooring Program>>");
        io.print("1. Display an Order");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Quit");
        return io.readInt("Please select from the above choices.", 1, 5);
    }

    public String askForDayofOrder() {
        return io.readString("What day do you want to see your orders for? (MMDDYYYY)");

    }

    public String askForCompany() {
        return io.readString("What is the name of the company you want"
                + " to create an Order for?");
    }

    public String askForState() {
        System.out.println("What State are they in??");
        return io.readString("Please enter in this format:OH,MN,MI,IN");
    }

    public String askForProduct() {
        System.out.println("They can purchase one of the Following Products");
        System.out.println("Carpet, Laminate, Tile, Wood");
        return io.readString("What is the Product they want to purchase?");
    }

    public BigDecimal askForArea() {

        BigDecimal area = io.readBig("What is the size of the area the want to purchase?");

        return area;

    }

    public Orders orderToAdd(String customerName, String customerState,
            String productType, BigDecimal area) {
        Orders order = new Orders();
        Taxes tax = new Taxes();
        Product product = new Product();
        tax.setStateName(customerState);
        product.setProductName(productType);

        order.setCustomerName(customerName);
        order.setProduct(product);
        order.setState(tax);
        order.setArea(area);
        return order;

    }

    public Boolean infoToAdd(String customerName, String customerState,
            String productType, BigDecimal area) {
        System.out.println("You entered " + customerName + " for the business name.");
        System.out.println("You said they are in " + customerState + ".");
        System.out.println("They are going to get " + productType + ".");
        System.out.println("You entered they need " + area.toString() + ".");
        int isCorrect = io.readInt("Please enter 1 if the information is correct:");
        if (isCorrect == 1) {
            return true;
        } else {
            return false;
        }

    }

    public void orderNotAdded() {
        io.print("*!*!*!*!*!*!*!*!*!*!*Did not add order*!*!*!*!*!*!*!*!*!*!");
    }

    public void displayOrders(List<Orders> listOfOrders) {
        for (Orders order : listOfOrders) {
            io.print("Order Number:        " + order.getOrderNumber());
            io.print("Customer Name:        " + order.getCustomerName());
            io.print("State:                " + order.getState().getStateName());
            io.print("Tax Rate Of State:    " + order.getState().getTaxRate());
            io.print("Product Type:         " + order.getProduct().getType());
            io.print("Area:                 " + order.getArea());
            io.print("Material Cost:      $ " + order.getProduct().getCostPerSqFoot() + " /per Sq Ft");
            io.print("Labor Cost:         $ " + order.getProduct().getLaborCostPerSqFoot() + " /per Sq Ft");
            io.print("Total Material:     $ " + order.getMaterialCost());
            io.print("Total Labor:        $ " + order.getLaborCost());
            io.print("Total Tax:          $ " + order.getTotalTax());
            io.print("Order Total:        $ " + order.getTotalCost());
            io.print("");

        }
    }

    public int orderNumberEdit() {
        return io.readInt("what is the order number you want to edit?");
    }

    public String orderToEditCompany(Orders orderToEdit) {
        String customerName = askForCompany();
        if (customerName.isEmpty()) {
            return orderToEdit.getCustomerName();
        }

        return customerName;
    }

    public String orderToEditState(Orders orderToEdit) {
        String customerState = askForState();
        if (customerState.isEmpty()) {
            return orderToEdit.getState().getStateName();
        }

        return customerState;
    }

    public BigDecimal orderToEditArea(Orders orderToEdit) {
        String areaString = io.readString("What is the size of the area you want to enter?");
        if(areaString.equals("")){
            return orderToEdit.getArea();
        } else {
            return new BigDecimal(areaString);
        }
    }

    public String OrderToEditProduct(Orders orderToEdit) {
        String productName = askForProduct();
        if (productName.equals("")) {
            return orderToEdit.getProduct().getProductName();
        }
        return productName;

    }

    public void goodBye() {
        io.print("Have a great day!");

    }

    public void errorMessage(Exception error) {
        io.print(error.getMessage());
    }

    public void notANumber() {
        io.print("That was not a valid Number");
    }

}
