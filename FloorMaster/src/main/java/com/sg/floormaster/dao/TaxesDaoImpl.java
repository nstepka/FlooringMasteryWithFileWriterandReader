/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import static com.sg.floormaster.dao.ProductDaoImpl.DELIMITER;
import static com.sg.floormaster.dao.ProductDaoImpl.PRODUCT_FILE;
import com.sg.floormaster.dto.Product;
import com.sg.floormaster.dto.Taxes;
import com.sg.floormaster.view.UserIO;
import com.sg.floormaster.view.UserIOConsoleImpl;
import com.sg.floormaster.view.View;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * public void loadTaxes(); public Taxes getTaxes(String state);
 *
 * @author nstep
 */
public class TaxesDaoImpl implements TaxesDao {

    public static final String PRODUCT_FILE = "Taxes.txt";
    public static final String DELIMITER = ",";

    public List<Taxes> tax = new ArrayList<>();
    public static String collectionFileName;
   
    Scanner scanner;

    private void loadTaxes() throws TaxesDaoException {
        try {
            scanner = new Scanner(new File(PRODUCT_FILE));
        } catch (FileNotFoundException ex) {
            throw new TaxesDaoException("Could not load States and their taxes", ex);
        }
        String currentLine;
        int currentLineNum = 0;
        String[] currentTokens;
        Taxes currentTax;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            currentTax = new Taxes(currentTokens[0]);

            currentTax.setTaxRate(new BigDecimal(currentTokens[1]));
            tax.add(currentTax);
            currentLineNum++;
        }
        scanner.close();

    }

    @Override
    public Taxes getTaxes(String state) throws TaxesDaoException {
        loadTaxes();
            for (Taxes taxes : tax) {
                if (taxes.getStateName().equals(state)) {
                    return taxes; //returns if found state

                }
            } throw new TaxesDaoException("Not a state in file");
    }
    
    
    
    
    
}
