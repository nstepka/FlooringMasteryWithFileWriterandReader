/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Orders;
import com.sg.floormaster.dto.Product;
import com.sg.floormaster.dto.Taxes;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nstep
 */
public class OrdersDaoTest {

    String todaysDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"));

    private OrdersDao dao = new OrdersDaoImpl();
    private OrdersDao daoCopy = new OrdersDaoImpl();
    int currentOrderNumber;
    Orders newOrder1 = new Orders();
    
    TaxesDao tax1 = new TaxesDaoImpl();
    Product tempProduct = new Product("Wood");

    public OrdersDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws OrdersDaoException, IOException, TaxesDaoException {
      
       
       
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of loadOrders method, of class OrdersDao.
     */
    @Test
    public void testLoadOrders() throws Exception {
      dao.loadOrders(todaysDate);
      assertNotNull(dao);
       
        
    }

    /**
     * Test of getAllOrders method, of class OrdersDao.
     */
    @Test
    public void testGetAllOrders() throws Exception {
        dao.getAllOrders(todaysDate);
        assertNotNull(dao);
    }

    /**
     * Test of addOrder method, of class OrdersDao.
     */
    @Test
    public void testAddGetOrder() throws Exception {
        currentOrderNumber = dao.getOrderCounter();
        
        this.newOrder1.setCustomerName("TestCase1");
        this.newOrder1.setArea(new BigDecimal("1"));
        this.newOrder1.setState(tax1.getTaxes("MN"));
        Product tempProduct = new Product("Wood");
        this.newOrder1.setProduct(tempProduct);
        this.newOrder1.setMaterialCost(new BigDecimal("1"));
        this.newOrder1.setLaborCost(new BigDecimal("1"));
        this.newOrder1.setTotalTax(new BigDecimal("1"));
        
        this.newOrder1.setTotalCost(new BigDecimal("1"));
        tempProduct.setCostPerSqFoot(new BigDecimal("1"));
        tempProduct.setLaborCostPerSqFoot(new BigDecimal("1"));
        Taxes tempTax = new Taxes(new BigDecimal("1").toString());
        dao.addOrder(newOrder1);
        Orders fromOrdersDao = dao.getOrder(todaysDate, currentOrderNumber);
        assertEquals(newOrder1, fromOrdersDao);
        
        
    }

    /**
     * Test of editOrder method, of class OrdersDao.
     */
    @Test
    public void testEditOrder() throws Exception {
        
         currentOrderNumber = dao.getOrderCounter();
        
        this.newOrder1.setCustomerName("TestCase1");
        this.newOrder1.setArea(new BigDecimal("1"));
        this.newOrder1.setState(tax1.getTaxes("MN"));
        Product tempProduct = new Product("Wood");
        this.newOrder1.setProduct(tempProduct);
        this.newOrder1.setMaterialCost(new BigDecimal("1"));
        this.newOrder1.setLaborCost(new BigDecimal("1"));
        this.newOrder1.setTotalTax(new BigDecimal("1"));
        
        this.newOrder1.setTotalCost(new BigDecimal("1"));
        tempProduct.setCostPerSqFoot(new BigDecimal("1"));
        tempProduct.setLaborCostPerSqFoot(new BigDecimal("1"));
        Taxes tempTax = new Taxes(new BigDecimal("1").toString());
        dao.addOrder(newOrder1);
        Orders orderToTest = dao.getOrder(todaysDate, currentOrderNumber);
        orderToTest.setCustomerName("UpdatedName");
        dao.editOrder(todaysDate, currentOrderNumber, newOrder1);
        Orders testOrder = dao.getOrder(todaysDate, currentOrderNumber);
        assertThat(testOrder, is(not(orderToTest)));
        

//         
//        
//        dao.editOrder(todaysDate, currentOrderNumber, newOrder1);
//        
//        Orders testFromOrders = dao.getOrder(todaysDate, currentOrderNumber);
//        assertEquals(newOrder1.getTotalTax(),testFromOrders.getTotalTax());
//        
    }

    /**NOT NEEDED IF I GOT AND RECIEVED!
     * Test of getOrder method, of class OrdersDao.
     */
//    @Test
//    public void testGetOrder() throws Exception {
//        
//    }

    /**
     * Test of getOrderCounter method, of class OrdersDao.
     */
    @Test
    public void testGetOrderCounter() throws Exception {
       int x = dao.getOrderCounter();
       dao.writeOrders();
      
       int y = dao.getOrderCounter();
        assertEquals(x, y-1);
    }

    /**
     * Test of removeOrder method, of class OrdersDao.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        currentOrderNumber = dao.getOrderCounter();
        dao.loadOrders(todaysDate);
        
        this.newOrder1.setCustomerName("TestCase1");
        this.newOrder1.setArea(new BigDecimal("1"));
        this.newOrder1.setState(tax1.getTaxes("MN"));
        Product tempProduct = new Product("Wood");
        this.newOrder1.setProduct(tempProduct);
        this.newOrder1.setMaterialCost(new BigDecimal("1"));
        this.newOrder1.setLaborCost(new BigDecimal("1"));
        this.newOrder1.setTotalTax(new BigDecimal("1"));
        
        this.newOrder1.setTotalCost(new BigDecimal("1"));
        tempProduct.setCostPerSqFoot(new BigDecimal("1"));
        tempProduct.setLaborCostPerSqFoot(new BigDecimal("1"));
        Taxes tempTax = new Taxes(new BigDecimal("1").toString());
        
        dao.addOrder(newOrder1);
    
        
        dao.removeOrder(todaysDate, currentOrderNumber, newOrder1);
        Orders order3 = dao.getOrder(todaysDate, currentOrderNumber);
        
        assertNull(order3);
        
        
    }

    /**If add you must write!
     * Test of writeOrders method, of class OrdersDao.
     */
//    @Test
//    public void testWriteOrders() throws Exception {
//        
//        
//    }

}
