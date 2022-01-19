package com.example.ru_cafe;

import java.util.ArrayList;

/**
 * Store Orders class that defines the Store Orders object which holds all orders in the store.
 * @author AbdulAhad Butt, Paul Cusa
 */
public class StoreOrders implements Customizable {
    private ArrayList<Order> storeOrders;

    /**
     * Constructor for the list of all the store orders.
     */
    public StoreOrders() {
        this.storeOrders = new ArrayList<>();
    }

    /**
     * Adds an order to the store.
     * @param obj the object to be added
     * @return true if the order was added, false if not
     */
    @Override
    public boolean add(Object obj) {
        
    	if (obj instanceof Order) {
            Order toAdd = (Order) obj;
            this.storeOrders.add(toAdd);
            
            return true;
        } 
        
        else {
            return false;
        }
    }

    /**
     * This getter method returns the ArrayList containing all of the stored orders
     * @return this.storeOrders ArrayList
     */
    public ArrayList<Order> getOrders() {
        return this.storeOrders;
    }

    /**
     * Removes an order from the store.
     * @param obj the object to be removed
     * @return true if the order was removed, false if not
     */
    @Override
    public boolean remove(Object obj) {
        
    	if (obj instanceof Order) {
            Order toRemove = (Order) obj;
            return this.storeOrders.remove(toRemove);
        } 
    	
    	else {
            return false;
        }
    }

}