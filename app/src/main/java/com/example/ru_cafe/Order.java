package com.example.ru_cafe;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This class defines the order object which contains the ordered items and order number. 
 * @author AbdulAhad Butt, Paul Cusa
 */
public class Order implements Customizable {

	final static int INITIAL = 1;
    private static int currID = INITIAL;
    private final static double TAX_RATE = 0.06625;
    private ArrayList<MenuItem> items;
    private int orderNum;

   /**
    * Constructor for the order object.
    */
    public Order() {
        this.orderNum = currID++;
        this.items = new ArrayList<>();
    }
    
    /**
     * Gets the items in the array. 
     * @return items the items in the array
     */
    public ArrayList<MenuItem> getItems() {
        return this.items;
    }


    /**
     * Checks if object is a menuitem and adds it to the order.
     * @param obj Object to possibly be added
     * @return true if object was added successfully, false otherwise
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof MenuItem) {
            this.items.add((MenuItem) obj);
            return true;
        }
        return false;
    }

    /**
     * Checks if obj is a menuitem and removes it from the order.
     * @param obj Object to possibly be removed
     * @return true if obj was removed successfully, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof MenuItem) {
            this.items.remove(obj);
        }
        return false;
    }

    /**
     * Gets the size of the current order.
     * @return size of the order
     */
    public int getSize() {
        return this.items.size();
    }
    
    /**
     * Calculates the total price of all items in the current order.
     * @return price of all items in the order
     */
    public double getSubtotal() {
        double subtotal = 0;
        for (MenuItem orderItem : this.items) {
            subtotal += orderItem.getItemPrice();
        }

        return subtotal;
    }

    /**
     * This method returns the string representation of the store orders.
     * @return String the store order
     */
    @Override
    public String toString() {
        String orderToString = "Order Number: " + this.orderNum + "\n";

        for (MenuItem item : this.items) {
            orderToString = orderToString + "\t-" + item.toString() + "\n";
        }

        DecimalFormat formatter = new DecimalFormat("'$'0.00");
        double subtotal = getSubtotal();
        orderToString = orderToString + "(Subtotal: " + formatter.format(subtotal);
        double tax = subtotal * TAX_RATE;
        double total = subtotal + tax;
        orderToString = orderToString + " Tax: " + formatter.format(tax) + ")";
        String totalString = formatter.format(total);
        orderToString += "\n\n\tTotal Price: " + totalString + "\n";
        return orderToString;
    }
}