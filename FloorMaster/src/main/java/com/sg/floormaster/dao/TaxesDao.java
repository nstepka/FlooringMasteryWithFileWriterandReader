/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import com.sg.floormaster.dto.Taxes;

/**
 *
 * @author nstep
 */
public interface TaxesDao {
    
    public Taxes getTaxes(String state) throws TaxesDaoException;
    
}
