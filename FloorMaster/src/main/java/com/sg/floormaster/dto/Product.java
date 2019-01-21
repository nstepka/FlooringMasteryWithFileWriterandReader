/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author nstep
 */
public class Product {
    
private String productName;
private BigDecimal costPerSqFoot;
private BigDecimal laborCostPerSqFoot;

    public Product() {
    }

    public Product(String productName, BigDecimal costPerSqFoot, BigDecimal laborCostPerSqFoot) {
        this.productName = productName;
        this.costPerSqFoot = costPerSqFoot;
        this.laborCostPerSqFoot = laborCostPerSqFoot;
    }

    public Product(String productName) {
        this.productName = productName;
    }

    public BigDecimal getLaborCostPerSqFoot() {
        return laborCostPerSqFoot;
    }

    public void setLaborCostPerSqFoot(BigDecimal laborCostPerSqFoot) {
        this.laborCostPerSqFoot = laborCostPerSqFoot;
    }

    public BigDecimal getCostPerSqFoot() {
        return costPerSqFoot;
    }

    public void setCostPerSqFoot(BigDecimal costPerSqFoot) {
        this.costPerSqFoot = costPerSqFoot;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Object getType() {
        return productName;
    }

    public void setType(String linePart) {
   
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.productName);
        hash = 67 * hash + Objects.hashCode(this.costPerSqFoot);
        hash = 67 * hash + Objects.hashCode(this.laborCostPerSqFoot);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.productName, other.productName)) {
            return false;
        }
        if (!Objects.equals(this.costPerSqFoot, other.costPerSqFoot)) {
            return false;
        }
        if (!Objects.equals(this.laborCostPerSqFoot, other.laborCostPerSqFoot)) {
            return false;
        }
        return true;
    }
    


    
}
