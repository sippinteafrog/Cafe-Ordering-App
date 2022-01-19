package com.example.ru_cafe;

import java.text.DecimalFormat;

/**
 * Donut class that creates a donut object. 
 * @author AbdulAhad Butt, Paul Cusa
 */
public class Donut extends MenuItem {

    private String flavor;
    private int quantity;

    final static double DONUT_PRICE = 1.39;
    
    private DecimalFormat formatter = new DecimalFormat("'$'0.00");

    /**
     * Constructor for the donut objects.
     * @param flavor the donut flavor
     * @param quantity the number of donuts
     */
    public Donut(String flavor, int quantity) {
        this.flavor = flavor;
        this.quantity = quantity;
        this.itemPrice();
    }
    
    /**
     * Calculates the price of the specific donut type chosen. 
     */
    public void itemPrice() {

        this.price = this.quantity *  DONUT_PRICE;

    }

    /**
     * Gets the price of the specific donut chosen. 
     */
    @Override
    public double getItemPrice() {
        return this.price;
    }

    /**
     * String implementation for the donut type.
     */
    @Override
    public String toString() {
        return this.quantity + "," + this.flavor + "," + formatter.format(this.price);
    }

    /**
     * This method formats the Donut item's subtotal into '$*.**'
     * @return formatted subtotal string
     */
    public String subtotalToString() {
        return formatter.format(this.price);
    }
   
}