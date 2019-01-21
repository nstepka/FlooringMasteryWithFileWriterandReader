/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.service;

import com.sg.floormaster.dao.OrdersDaoException;
import com.sg.floormaster.dao.ProductDaoException;
import com.sg.floormaster.dao.TaxesDaoException;
import com.sg.floormaster.dto.Orders;
import com.sg.floormaster.dto.Product;
import com.sg.floormaster.dto.Taxes;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author nstep
 */
public interface Service {

    public List<Orders> getOrders(String Date) throws OrdersDaoException;

    public void addOrder(Orders order) throws TaxesDaoException, IOException, OrdersDaoException;

    public Product getProduct(String itemLocation) throws ProductDaoException;
    
    public Taxes getTaxes(String state) throws TaxesDaoException;

    public void editOrder(String date, int orderNumber, Orders order) throws OrdersDaoException;
    
    public Orders getOrder(String dateOfOrder, int orderNumber) throws OrdersDaoException;
    
     public void removeOrder(String date, int orderNumber, Orders order) throws OrdersDaoException, IOException; 
     public Orders calcOrder(Orders order) throws TaxesDaoException, IOException;
// 
//
//    public Order addOrder(Order order) throws DaoPersistanceException {
//
//
//    public Order removeOrder(LocalDate date, int orderNumber) throws DaoPersistanceException {
//
//
//    public Order getOrder(LocalDate date, int orderNumber) throws DaoPersistanceException {
//
//
//    public List<Order> getOrders(LocalDate date) throws DaoPersistanceException, DataValidationException {

    }
