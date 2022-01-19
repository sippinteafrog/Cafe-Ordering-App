package com.example.ru_cafe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This activity class contains the variables and methods of the Stored Orders GUI and handles all
 * user input.
 * @author AbdulAhad Butt, Paul Cusa
 */
public class StoredOrdersActivity extends AppCompatActivity {

    private ListView storeOrdersList;
    private int selectedItem = -1;
    private ArrayList<Order> orderListCopy = MainActivity.storeOrders.getOrders();

    /**
     * This method initializes all necessary aspects of the Stored Orders GUI upon its creation.
     * @param savedInstanceState saved instance state of the program
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stored_orders);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        storeOrdersList = findViewById(R.id.storeOrdersList);
        this.setListView();
    }

    /**
     * This method sets the storeOrdersList ListView to contain the list of the stored orders.
     */
    private void setListView() {

        ArrayList<String> orders = new ArrayList<>();

        for (Order order : orderListCopy) {
            orders.add(order.toString());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, orders);
        storeOrdersList.setAdapter(arrayAdapter);

        storeOrdersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /**
             * This method sets up the onClick listener for the storeOrdersList ListView.
             * @param parent adapter assigned to the ListView
             * @param view view that contains the ListView
             * @param position item that is selected in the ListView
             * @param id id of the ListView
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = position;

                AlertDialog.Builder builder = new AlertDialog.Builder(StoredOrdersActivity.this);
                builder.setMessage("Would you like to remove this item?");
                builder.setTitle("Item Selected");
                builder.setCancelable(false);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    /**
                     * This method handles the actions when an item in the Alert is clicked.
                     * @param dialog dialog
                     * @param which item that is selected
                     */
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        orderListCopy.remove(selectedItem);
                        setListView();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    /**
                     * This method handles the actions when an item in the Alert is clicked.
                     * @param dialog dialog
                     * @param which item that is selected
                     */
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

}
