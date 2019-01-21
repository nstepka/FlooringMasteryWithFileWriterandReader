/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Product;
import com.sg.floormaster.view.View;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nstep
 */
public class ProductDaoImpl implements ProductDao {
    public static final String PRODUCT_FILE = "Products.txt";
    public static final String DELIMITER = ",";

    public List<Product> products = new ArrayList<>();
    public static String collectionFileName;
    View io;
    Scanner scanner;
    
   
   
    private void loadProducts() throws ProductDaoException  {
        
        try {
            scanner = new Scanner(new File(PRODUCT_FILE));
        } catch (FileNotFoundException ex) {
                        throw new ProductDaoException("Could not load Products", ex);
        }
       String currentLine;
       int currentLineNum = 0;
       String[] currentTokens;
       Product currentProductItem;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            currentProductItem = new Product(currentTokens[0]);

            currentProductItem.setCostPerSqFoot(new BigDecimal (currentTokens[1]));
            currentProductItem.setLaborCostPerSqFoot(new BigDecimal(currentTokens[2]));
            products.add(currentProductItem);
            currentLineNum++;
        }
        scanner.close();
        
    }
   
    @Override
    public Product getProduct(String itemLocation) throws ProductDaoException {
        loadProducts();
       for(Product product: products) {
           if(product.getProductName().equals(itemLocation)) {
               return product;
           }
       } throw new ProductDaoException("Not a product");
    }
}
