/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dao;

/**
 *
 * @author nstep
 */
public class ProductDaoException extends Exception {
    public ProductDaoException(String message) {
        super(message);
    }

    public ProductDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
