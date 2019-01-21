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
public class OrdersDaoException extends Exception {

   public OrdersDaoException(String message) {
        super(message);
    }

    public OrdersDaoException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
