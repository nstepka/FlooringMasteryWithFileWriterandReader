/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Orders;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author nstep
 */
public interface OrdersDao {

public void loadOrders(String date) throws OrdersDaoException;

public List<Orders> getAllOrders(String Date) throws OrdersDaoException;
public void addOrder(Orders newOrder) throws OrdersDaoException, IOException;
public void editOrder(String date, int orderNumber, Orders userOrder) throws OrdersDaoException, IOException;
public Orders getOrder(String localDate, int orderNumber) throws OrdersDaoException;
public int getOrderCounter() throws FileNotFoundException, IOException;
public void removeOrder(String date, int orderNumber, Orders userOrder) throws OrdersDaoException, IOException;
public void writeOrders() throws IOException;
}
