package com.example.ru_cafe;

/**
 * This class represents a menu item such as donuts and coffee.
 * @author AbdulAhad Butt, Paul Cusa
 */
public class MenuItem {
    protected double price;

    /**
     * Method to get the price of item. 
     * It is overridden in Coffee and Donut class.
     * @return double the price of item
     */
    public double getItemPrice() {
        return this.price;
    }

    /**
     * Returns the string implementation.
     * It is overridden in Donut and Coffee class.
     * @return string empty initially
     */
    public String toString() {
        return "";
    }
}