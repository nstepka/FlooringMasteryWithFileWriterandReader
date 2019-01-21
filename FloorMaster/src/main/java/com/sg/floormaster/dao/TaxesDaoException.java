/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

import java.io.FileNotFoundException;

/**
 *
 * @author nstep
 */
public class TaxesDaoException extends Exception {

    public TaxesDaoException(String message) {
        super(message);
    }

    public TaxesDaoException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
