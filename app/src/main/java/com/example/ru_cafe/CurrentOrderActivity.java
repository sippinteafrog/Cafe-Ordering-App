package com.example.ru_cafe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This activity class contains the variables and methods of the Current Order GUI and handles all
 * user input.
 * @author AbdulAhad Butt, Paul Cusa
 */
public class CurrentOrderActivity extends AppCompatActivity {

    private ListView currentOrderListView;
    private TextView subtotalTextView;
    private TextView taxTextView;
    private TextView totalTextView;
    private Button placeOrderButton;

    final static double TAX_RATE = .06625;
    private int selectedItem = -1;
    private final double PERCENT = 100.00;

    DecimalFormat formatter = new DecimalFormat("'$'0.00");
    private double subtotal = 0.00;
    private double tax = 0.00;
    private double total = 0.00;

    private ArrayList<MenuItem> orderCopy = MainActivity.order.getItems();

    /**
     * This method initializes all necessary aspects of the Current Order GUI upon its creation.
     * @param savedInstanceState saved instance state of the program
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_order);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        subtotalTextView = findViewById(R.id.subtotalTextView);
        taxTextView = findViewById(R.id.taxTextView);
        totalTextView = findViewById(R.id.totalTextView);

        placeOrderButton = findViewById(R.id.placeOrderButton);

        currentOrderListView = findViewById(R.id.currentOrderListView);

        this.setListView();
    }

    /**
     * This method sets the currentOrderListView to contain the contents of the current order.
     */
    private void setListView() {
        ArrayList<String> orderString = new ArrayList<>();

        for (MenuItem item : orderCopy) {
            String temp = item.toString();
            orderString.add(temp);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, orderString);
        currentOrderListView.setAdapter(arrayAdapter);

        currentOrderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /**
             * This method sets up the onClick listener for the currentOrderListView.
             * @param parent adapter assigned to the ListView
             * @param view view that contains the ListView
             * @param position item that is selected in the ListView
             * @param id id of the ListView
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = position;

                AlertDialog.Builder builder = new AlertDialog.Builder(CurrentOrderActivity.this);
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
                        orderCopy.remove(selectedItem);
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

        subtotal = Math.round(MainActivity.order.getSubtotal() * PERCENT) / PERCENT;
        tax = Math.round(subtotal * TAX_RATE * PERCENT) / PERCENT;
        total = Math.round((subtotal + tax) * PERCENT) / PERCENT;

        subtotalTextView.setText("Subtotal: " + formatter.format(subtotal));
        taxTextView.setText("Tax: " + formatter.format(tax));
        totalTextView.setText("Total: " + formatter.format(total));
    }

    /**
     * This method adds the users current order to their list of stored orders.
     * @param view that has been clicked
     */
    public void placeOrder(View view) {

        if (MainActivity.order.getSize() == 0) {
            Toast.makeText(this, "Unable to place order, order is empty.", Toast.LENGTH_SHORT).show();
        }

        else {
            MainActivity.storeOrders.add(MainActivity.order);
            Toast.makeText(this, "Order has been placed.", Toast.LENGTH_SHORT).show();
            MainActivity.order = new Order();
            orderCopy = MainActivity.order.getItems();
            setListView();
        }
    }
}
