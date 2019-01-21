///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.sg.floormaster.dao;
//
//import com.sg.floormaster.dto.Orders;
//import com.sg.floormaster.dto.Product;
//import com.sg.floormaster.dto.Taxes;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author nstep
// */
//public class OrderDaoStubImpl implements OrdersDao {
//    Orders order;
//    Taxes Tax;
//   
//   
//    
//    List<Orders> orderList = new ArrayList<>();
//    
//    public OrderDaoStubImpl() {
//        order = new Orders(1);
//        order.setCustomerName("Test");
//        order.setArea(new BigDecimal("1"));
//        order.setProductName("Wood");
//        order.setStateName("MN");
//        orderList.add(order); 
//        
//    }
//    
//    @Override
//    public void loadOrders(String date) throws OrdersDaoException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public List<Orders> getAllOrders(String Date) throws OrdersDaoException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void addOrder(Orders newOrder) throws OrdersDaoException, IOException {
////        if(order.equals(order.getCustomerName())){
////            return order;
////        }
//    }
//
//    @Override
//    public void editOrder(String date, int orderNumber, Orders userOrder) throws OrdersDaoException, IOException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public Orders getOrder(String localDate, int orderNumber) throws OrdersDaoException {
//        
//        return orderList.get(orderNumber);
//        
//    }
//
//    @Override
//    public int getOrderCounter() throws FileNotFoundException, IOException {
//        
//    }
//
//    @Override
//    public void removeOrder(String date, int orderNumber, Orders userOrder) throws OrdersDaoException, IOException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void writeOrders() throws IOException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//}
