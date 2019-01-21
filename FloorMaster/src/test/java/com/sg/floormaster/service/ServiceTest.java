/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.service;

import com.sg.floormaster.dao.OrdersDao;
import com.sg.floormaster.dao.OrdersDaoException;
import com.sg.floormaster.dao.OrdersDaoImpl;
import com.sg.floormaster.dao.ProductDao;
import com.sg.floormaster.dao.ProductDaoException;
import com.sg.floormaster.dao.ProductDaoImpl;
import com.sg.floormaster.dao.TaxesDao;
import com.sg.floormaster.dao.TaxesDaoException;
import com.sg.floormaster.dao.TaxesDaoImpl;
import com.sg.floormaster.dto.Orders;
import com.sg.floormaster.dto.Product;
import com.sg.floormaster.dto.Taxes;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import junit.framework.Assert;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author nstep
 */
public class ServiceTest {
    // I WANT TO STUB RIGHT????/??
    
    private Service service;
    private OrdersDao dao = new OrdersDaoImpl();
    private Orders order;
    private ProductDao productDao;
    private TaxesDao taxDao;
    
    String todaysDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMddyyyy"));
    
    private String date = "02121954";
    Orders newOrder1 = new Orders();
    //    Service service = new ServiceImpl(order, product, tax);
    
    public ServiceTest() {
//     dao = new OrdersDaoImpl();
//     product = new Product();
//     tax = new Taxes();
//     productDao = new ProductDaoImpl();
//     taxDao = new TaxesDaoImpl();  
//     service = new ServiceImpl(dao, productDao,taxDao);
//     
     ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
    dao = ctx.getBean("dao", OrdersDao.class);
    productDao = ctx.getBean("productDao", ProductDao.class);
    taxDao = ctx.getBean("taxDao", TaxesDao.class);
    service = ctx.getBean("service", Service.class);
            
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws OrdersDaoException {
        
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getOrders method, of class Service.
     */
    @Test
    public void testGetOrders() throws Exception {
      
        
        assertNotNull(service.getOrders(todaysDate));
        
    }

    /**
     * Test of addOrder method, of class Service.
     */
    @Test
    public void testAddOrder() throws Exception {
       int x  = dao.getOrderCounter();
       Orders orderToAdd = service.getOrder("02121954",94);
        
        service.addOrder(orderToAdd);
        Orders orderToTest = dao.getOrder(todaysDate, x);
        assertEquals(orderToTest,orderToAdd);
    
    
    }

    /**
     * Test of getProduct method, of class Service.
     */
    @Test
    public void testGetProduct() throws Exception {
        Product product = new Product();
        product.setType("Wood");
        Product getProduct = newOrder1.getProduct();
        assertEquals(product, getProduct);
    }

    /**
     * Test of getTaxes method, of class Service.
     */
    @Test
    public void testGetTaxes() throws Exception {
        Taxes tax = service.getTaxes("MN");
        assertNotNull(tax);
    }

    /**
     * Test of editOrder method, of class Service.
     */
    @Test
    public void testEditOrder() throws Exception {
       Orders orderToTest = service.getOrder("02121954",95);
       Orders orderToVerify = orderToTest;
       orderToTest.setCustomerName("Chuck");
       service.editOrder("02121954", 94, orderToTest);
       orderToTest = service.getOrder("02121954", 95);
       assertThat(orderToTest, is(not(orderToVerify)));
    }
//
//    /**
//     * Test of getOrder method, of class Service.
//     */
//    @Test
//    public void testGetOrder() throws Exception {
//        
//    }

    /**
     * Test of removeOrder method, of class Service.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        int x  = dao.getOrderCounter();
        Orders orderToAdd = service.getOrder("02121954",94);
        service.addOrder(orderToAdd);
        service.removeOrder(todaysDate, x, orderToAdd);
        assertNull(service.getOrder(todaysDate, x));
        
    
    
    }

    
    
}
