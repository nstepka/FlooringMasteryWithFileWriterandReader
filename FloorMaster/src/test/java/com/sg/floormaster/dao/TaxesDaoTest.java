/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Taxes;
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
public class TaxesDaoTest {
    TaxesDao dao = new TaxesDaoImpl();
    public TaxesDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getTaxes method, of class TaxesDao.
     */
    @Test
    public void testGetTaxes() throws Exception {
    Taxes productToTest = dao.getTaxes("MN");
    assertNotNull(productToTest);
    
    }

    
    
}
