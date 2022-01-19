package com.example.ru_cafe;

import java.text.DecimalFormat;

/**
 * This class defines the abstract Coffee data type and encapsulates all data fields and
 * methods of a Coffee Object. This class is a subclass of the MenuItem class and
 * implements the Customizable interface.
 * @author AbdulAhad Butt, Paul Cusa
 */
public class Coffee extends MenuItem implements Customizable {

    final static double ADD_IN_PRICE = .20;
    final static double SHORT_PRICE = 1.99;
    final static double TALL_PRICE = 2.49;
    final static double GRANDE_PRICE = 2.99;
    final static double VENTI_PRICE = 3.49;

    private boolean hasCream;
    private boolean hasMilk;
    private boolean hasCaramel;
    private boolean hasWhippedCream;
    private boolean hasSyrup;
    private int quantity;
    private String size;
    private DecimalFormat formatter = new DecimalFormat("'$'0.00");

    /**
     * This constructor creates an instance of the Coffee class of a certain size and
     * quantity and computes the price using this info.
     * @param size string representation of the size option selected by user
     * @param quantity number of coffees selected by user
     */
    public Coffee(String size, int quantity) {
        this.size = size;
        this.hasCream = false;
        this.hasMilk = false;
        this.hasCaramel = false;
        this.hasWhippedCream = false;
        this.hasSyrup = false;
        this.quantity = quantity;
        this.itemPrice();
    }

    /**
     * This method counts the number add-ins that the Coffee object has in order to
     * calculate the Coffee's price. This method is called in the itemPrice() method.
     * @return integer number of add-ins
     */
    public int getNumAddIns() {
        int count = 0;
        
        if (this.hasCaramel) {
            count++;
        }
        
        if (this.hasCream) {
            count++;
        }
        
        if (this.hasMilk) {
            count++;
        }
        
        if (this.hasSyrup) {
            count++;
        }
        
        if (this.hasWhippedCream) {
            count++;
        }
        
        return count;
    }

    /**
     * This method calculates the Coffee object's price. This method calls the
     * .getNumAddIns() method.
     */
    public void itemPrice() {
       
    	switch(this.size) {
    	
            case "Short" : {
                this.price = (SHORT_PRICE * this.quantity) + (this.getNumAddIns() *
                        this.quantity * ADD_IN_PRICE);
                break;
            }
            
            case "Tall" : {
                this.price = (TALL_PRICE * this.quantity) + (this.getNumAddIns() *
                        this.quantity * ADD_IN_PRICE);
                break;
            }
            
            case "Grande" : {
                this.price = (GRANDE_PRICE * this.quantity) + (this.getNumAddIns() *
                        this.quantity * ADD_IN_PRICE);
                break;
            }
            
            default : {
                this.price = (VENTI_PRICE * this.quantity) + (this.getNumAddIns() *
                        this.quantity * ADD_IN_PRICE);
            }
        }
    }

    /**
     * This getter method returns the Coffee object's price;
     * @return the Coffee's price
     */
    @Override
    public double getItemPrice() {

        return this.price;
    }

    /**
     * This method is the implementation of the add() method from the Customizable
     * interface. This method adds an add-in to a Coffee object.
     * @param obj String representation of Coffee add-in
     * @return true if the add-in is successfully added, false if not
     */
    public boolean add(Object obj) {
        if (!(obj instanceof String)) {
            return false;
        }
        String str = (String) obj;
        
        switch (str) {
        
            case "Cream" : {
                
            	if (this.hasCream) {
                    return false;
                }
            	
                this.hasCream = true;
                this.price = this.price + ADD_IN_PRICE;
                return true;
            }
            
            case "Milk" : {
                
            	if (this.hasMilk) {
                    return false;
                }
            	
                this.hasMilk = true;
                this.price = this.price + ADD_IN_PRICE;
                return true;
            }
            
            case "Caramel" : {
                
            	if (this.hasCaramel) {
                    return false;
                }
            	
                this.hasCaramel = true;
                this.price = this.price + ADD_IN_PRICE;
                return true;
            }
            
            case "Whipped Cream" : {
                
            	if (this.hasWhippedCream) {
                    return false;
                }
            	
                this.hasWhippedCream = true;
                this.price = this.price + ADD_IN_PRICE;
                return true;
            }
            
            case "Syrup" : {
                
            	if (this.hasSyrup) {
                    return false;
                }
            	
                this.hasSyrup = true;
                this.price = this.price + ADD_IN_PRICE;
                return true;
            }
            
            default : {
                return false;
            }
        }
    }

    /**
     * This method is the implementation of the remove() method from the Customizable
     * interface. This method removes an add-in from a Coffee object.
     * @param obj String representation of Coffee add-in
     * @return true if the add-in is successfully removed, false if not
     */
    public boolean remove(Object obj) {
        
    	if (!(obj instanceof String)) {
            return false;
        }
    	
        String str = (String) obj;
        switch (str) {
        
        	case "Cream" : {
                if (!(this.hasCream)) {
                    return false;
                }
                this.hasCream = false;
                this.price = this.price - ADD_IN_PRICE;
                return true;
            }
        	
            case "Milk" : {
                if (!(this.hasMilk)) {
                    return false;
                }
                this.hasMilk = false;
                this.price = this.price - ADD_IN_PRICE;
                return true;
            }
            
            case "Caramel" : {
                if (!(this.hasCaramel)) {
                    return false;
                }
                this.hasCaramel = false;
                this.price = this.price - ADD_IN_PRICE;
                return true;
            }
            
            case "Whipped Cream" : {
                if (!(this.hasWhippedCream)) {
                    return false;
                }
                this.hasWhippedCream = false;
                this.price = this.price - ADD_IN_PRICE;
                return true;
            }
            
            default : {
                
            	if (!(this.hasSyrup)) {
                    return false;
                }
            	
                this.hasSyrup = false;
                this.price = this.price - ADD_IN_PRICE;
                return true;
            }
        }
    }

    /**
     * This method formats a string representation of the Coffee object's add-ins.
     * This method is called by the toString() override in this class.
     * @return String representation of the Coffee add-ins
     */
    private String addInsToString() {
        String addInsString = "";
        
        if (this.hasCream) {
            addInsString = addInsString + " add cream,";
        }
        
        if (this.hasMilk) {
            addInsString = addInsString + " add milk,";
        }
        
        if (this.hasSyrup) {
            addInsString = addInsString + " add syrup,";
        }
        
        if (this.hasCaramel) {
            addInsString = addInsString + " add caramel,";
        }
        
        if (this.hasWhippedCream) {
            addInsString = addInsString + " add whipped cream,";
        }
        return addInsString;
    }

    /**
     * This method formats a string representation of the Coffee object. This method
     * overrides the toString() method and calls the addInsToString() method.
     * @return String representation of Coffee object
     */
    @Override
    public String toString() {
        return this.quantity +  " " + this.size + " Coffee," + this.addInsToString() +
                " Price: " + formatter.format(this.price);
    }

    /**
     * This method formats the Coffee item's subtotal into '$*.**'
     * @return formatted subtotal string
     */
    public String subtotalToString() {
        return formatter.format(this.price);
    }
}
