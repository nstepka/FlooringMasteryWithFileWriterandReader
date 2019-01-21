/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.service;

import com.sg.floormaster.dao.OrdersDao;
import com.sg.floormaster.dao.OrdersDaoException;
import com.sg.floormaster.dao.ProductDao;
import com.sg.floormaster.dao.ProductDaoException;
import com.sg.floormaster.dao.TaxesDao;
import com.sg.floormaster.dao.TaxesDaoException;
import com.sg.floormaster.dao.TaxesDaoImpl;
import com.sg.floormaster.dto.Orders;
import com.sg.floormaster.dto.Product;
import com.sg.floormaster.dto.Taxes;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nstep
 */
public class ServiceImpl implements Service {

    String date;
    OrdersDao dao;
    ProductDao daoProduct;
    TaxesDao daoTax = new TaxesDaoImpl();

    public ServiceImpl() {

    }

    public ServiceImpl(OrdersDao dao, ProductDao daoProduct, TaxesDao daoTax) {
        this.dao = dao;
        this.daoProduct = daoProduct;
        this.daoTax = daoTax;
    }

    @Override
    public List<Orders> getOrders(String Date) throws OrdersDaoException {
        dao.loadOrders(Date);
        return dao.getAllOrders(Date);

    }

    @Override
    public void addOrder(Orders order) throws TaxesDaoException, IOException, OrdersDaoException {

        order = calcOrder(order);

        order.setOrderNumber(dao.getOrderCounter());

        dao.addOrder(order);

    }

    @Override
    public Product getProduct(String itemLocation) throws ProductDaoException {

        return daoProduct.getProduct(itemLocation);

    }

    @Override
    public Taxes getTaxes(String state) throws TaxesDaoException {

        return daoTax.getTaxes(state);
    }

    @Override
    public Orders calcOrder(Orders order) throws TaxesDaoException, IOException {

        Taxes tax = order.getState();

        Product product = order.getProduct();
        order.setCustomerName(order.getCustomerName());
        order.setState(tax);
        order.setProduct(product);
        
        //order.setArea(order.getArea());   
        order.setMaterialCost(product.getCostPerSqFoot().multiply(order.getArea()));
        order.setLaborCost(product.getLaborCostPerSqFoot().multiply(order.getArea()));
        BigDecimal totalOfMaterialandLabor = order.getMaterialCost().add(order.getLaborCost());
        BigDecimal taxToal = totalOfMaterialandLabor.multiply(tax.getTaxRate());
        BigDecimal totalCost = taxToal.add(totalOfMaterialandLabor.setScale(2, RoundingMode.HALF_UP));
        order.setTotalTax(taxToal.setScale(2, RoundingMode.HALF_UP));
        order.setTotalCost(totalCost.setScale(2, RoundingMode.HALF_UP));
        return order;
    }

    @Override
    public void editOrder(String date, int orderNumber, Orders order) throws OrdersDaoException {
        try {
            order = calcOrder(order);
        } catch (TaxesDaoException | IOException ex) {
            throw new OrdersDaoException("someting screwedup!", ex);
        }

        try {
            dao.editOrder(date, orderNumber, order);
        } catch (OrdersDaoException ex) {
            throw new OrdersDaoException("could not find order", ex);
        } catch (IOException ex) {
            throw new OrdersDaoException("someting screwedup!2", ex);
        }

    }

    @Override
    public Orders getOrder(String dateOfOrder, int orderNumber) throws OrdersDaoException {

        try {
            return dao.getOrder(dateOfOrder, orderNumber);
        } catch (OrdersDaoException ex) {
            throw new OrdersDaoException("could not find order", ex);
        }
    }

    @Override
    public void removeOrder(String date, int orderNumber, Orders order) throws OrdersDaoException, IOException {
        dao.removeOrder(date, orderNumber, order);

    }

}
