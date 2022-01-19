package com.example.ru_cafe;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * The activity for the Main menu view.
 * @author Paul Cusa, AbdulAhad Butt
 */
public class MainActivity extends AppCompatActivity {

    static Order order = new Order();
    public static StoreOrders storeOrders = new StoreOrders();

    /**
     * This method initializes the Main (Parent) GUI.
     * @param savedInstanceState saved instance state of the program
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * This method launches the DonutActivity.
     * @param view parameter of View type
     */
    public void launchDonut(View view) {
        Intent intent = new Intent(this, DonutActivity.class);
        startActivity(intent);
    }

    /**
     * This method launches the CoffeeActivity.
     * @param view parameter of View type
     */
    public void launchCoffee(View view) {
        Intent intent = new Intent(this, CoffeeActivity.class);
        startActivity(intent);
    }

    /**
     * This method launches the CurrentOrderActivity.
     * @param view parameter of View type
     */
    public void launchCurrentOrder(View view) {
        Intent intent = new Intent(this, CurrentOrderActivity.class);
        startActivity(intent);
    }

    /**
     * This method launches the StoredOrdersActivity.
     * @param view parameter of View type
     */
    public void launchStoredOrders(View view) {
        Intent intent = new Intent(this, StoredOrdersActivity.class);
        startActivity(intent);
    }

}