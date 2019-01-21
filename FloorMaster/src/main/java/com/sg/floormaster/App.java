/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster;

import com.sg.floormaster.controller.Controller;
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
import com.sg.floormaster.service.Service;
import com.sg.floormaster.service.ServiceImpl;
import com.sg.floormaster.view.UserIO;
import com.sg.floormaster.view.UserIOConsoleImpl;
import com.sg.floormaster.view.View;
import java.io.IOException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author nstep
 */
public class App {
    
    public static void main(String[] args) throws TaxesDaoException, ProductDaoException, OrdersDaoException, IOException {
        
//    UserIO myIO = new UserIOConsoleImpl();
//    ProductDao product = new ProductDaoImpl();
//    TaxesDao tax = new TaxesDaoImpl();
//    OrdersDao order = new OrdersDaoImpl();
//    View view = new View(myIO);
//    Service service = new ServiceImpl(order, product, tax);
//    Controller controller = new Controller(view, service);
//    controller.run();
//    
    ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
        Controller controller = 
           ctx.getBean("controller", Controller.class);
        controller.run();
    
    }
}
