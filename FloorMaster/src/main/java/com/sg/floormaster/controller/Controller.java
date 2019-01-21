/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.controller;

import com.sg.floormaster.dao.OrdersDaoException;
import com.sg.floormaster.dao.ProductDaoException;
import com.sg.floormaster.dao.TaxesDaoException;
import com.sg.floormaster.dto.Orders;
import com.sg.floormaster.dto.Product;
import com.sg.floormaster.dto.Taxes;
import com.sg.floormaster.service.Service;
import com.sg.floormaster.service.ServiceImpl;
import com.sg.floormaster.view.View;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nstep
 */
public class Controller {

    View view;
    Service service;

    public Controller(View view, Service service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        boolean keepGoing = true;
        while (keepGoing) {
            try {
                switch (showMenu()) {
                    case 1: {

                        // display orders
                        displayOrders();

                    }
                    break;
                    case 2: {

                        // add an order
                        addOrder();

                    }

                    break;
                    case 3: {

                        editOrder();

                    }
                    //  keepGoing = false;
                    break;
                    case 4:  //remove an order
                        removeOrder();
                        break;
                    case 5: //quit
                        exitMessage();
                        keepGoing = false;
                        break;
                    default:
                        view.notANumber();

                }
            } catch (OrdersDaoException | TaxesDaoException | ProductDaoException | IOException error) {
                view.errorMessage(error);
            }
        }

    }

    private int showMenu() {
        return view.displayMainMenu();

    }

    private void displayOrders() throws OrdersDaoException {
        List<Orders> listOfOrders;
        String orderDate = view.askForDayofOrder();
        try {
            listOfOrders = service.getOrders(orderDate);

//        String orderDate = view.askForDayofOrder();
//        try {
//           service.getOrders(orderDate);
//        } catch (OrdersDaoException ex) {
//            throw new OrdersDaoException("Could not find orders!", ex);
//        }
//        
        } catch (OrdersDaoException ex) {
            throw new OrdersDaoException("Could not find any orders for that day", ex);
        }
        view.displayOrders(listOfOrders);

    }

    private void addOrder() throws TaxesDaoException, ProductDaoException, IOException, OrdersDaoException {
        String companyName = view.askForCompany();
        String companyState = view.askForState();

        int x = 0;
        while (x == 0) {

            try {
                Taxes tax = service.getTaxes(companyState);
                x = 2;
            } catch (TaxesDaoException ex) {
                companyState = view.askForState();
            }
        }

        String productName = view.askForProduct();
        int y = 0;
        while (y == 0) {

            try {
                Product product = service.getProduct(productName);
                y = 2;
            } catch (ProductDaoException ex) {
                productName = view.askForProduct();
            }

        }

        BigDecimal area = view.askForArea();

        if (view.infoToAdd(companyName, companyState, productName, area) == true) {
            Orders order = new Orders();
            order.setCustomerName(companyName);
            order.setArea(area);
            order.setState(service.getTaxes(companyState));
            order.setProduct(service.getProduct(productName));
            service.addOrder(order);

        } else {
            view.orderNotAdded();
        }

    }

    private void editOrder() throws OrdersDaoException, TaxesDaoException, ProductDaoException {
        Product product = new Product();
        Taxes tax = new Taxes();
        List<Orders> listOfOrders;
        String orderDate = view.askForDayofOrder();
        listOfOrders = service.getOrders(orderDate);
        view.displayOrders(listOfOrders);
        int orderNumber = view.orderNumberEdit();
        Orders orderToEdit = service.getOrder(orderDate, orderNumber);
        String companyName = view.orderToEditCompany(orderToEdit);
        orderToEdit.setCustomerName(companyName);

        String companyState = view.orderToEditState(orderToEdit);
        
       
                tax = service.getTaxes(companyState);
          
        
        orderToEdit.setState(tax);

        String productName = view.OrderToEditProduct(orderToEdit);
        int y = 0;
        while (y == 0) {

            try {
                product = service.getProduct(productName);
                y = 2;
            } catch (ProductDaoException ex) {
                productName = view.OrderToEditProduct(orderToEdit);
            }

        }

        orderToEdit.setProduct(product);

        BigDecimal area = view.orderToEditArea(orderToEdit);
        orderToEdit.setArea(area);

        service.editOrder(orderDate, orderNumber, orderToEdit);

    }

    private void removeOrder() throws OrdersDaoException, IOException {
        List<Orders> listOfOrders;
        String orderDate = view.askForDayofOrder();
        listOfOrders = service.getOrders(orderDate);
        view.displayOrders(listOfOrders);
        int orderNumber = view.orderNumberEdit();
        Orders orderToRemove = service.getOrder(orderDate, orderNumber);
        service.removeOrder(orderDate, orderNumber, orderToRemove);

    }

    private void exitMessage() {
        view.goodBye();
    }

}
