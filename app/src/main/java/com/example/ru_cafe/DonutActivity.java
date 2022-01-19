package com.example.ru_cafe;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This activity class contains the variables and methods of the Donut GUI and handles all
 * user input.
 * @author AbdulAhad Butt, Paul Cusa
 */
public class DonutActivity extends AppCompatActivity {

    private Donut donutAdd = new Donut("Boston Cream", 0);
    private Spinner donutQuantity;
    TextView subtotalDonuts;
    Toast toast;
    final static int QUANTITY_ONE = 1;
    final static int QUANTITY_TWO = 2;
    final static int QUANTITY_THREE = 3;
    final static int QUANTITY_FOUR = 4;
    final static int QUANTITY_FIVE = 5;
    final static int QUANTITY_SIX = 6;
    final static int QUANTITY_SEVEN = 7;
    final static int QUANTITY_EIGHT = 8;
    final static int QUANTITY_NINE = 9;
    final static int QUANTITY_TEN = 10;

    /**
     * This method initializes all necessary aspects of the Donut GUI upon its creation.
     * @param savedInstanceState saved instance state of the program
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_donuts);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        subtotalDonuts = findViewById(R.id.subtotalDonuts);

            List<Integer> spinnerArray = new ArrayList<Integer>();
            spinnerArray.add(QUANTITY_ONE);
            spinnerArray.add(QUANTITY_TWO);
            spinnerArray.add(QUANTITY_THREE);
            spinnerArray.add(QUANTITY_FOUR);
            spinnerArray.add(QUANTITY_FIVE);
            spinnerArray.add(QUANTITY_SIX);
            spinnerArray.add(QUANTITY_SEVEN);
            spinnerArray.add(QUANTITY_EIGHT);
            spinnerArray.add(QUANTITY_NINE);
            spinnerArray.add(QUANTITY_TEN);

            ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,
                    android.R.layout.simple_spinner_item, spinnerArray);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            donutQuantity = findViewById(R.id.donutQuantity);
            donutQuantity.setAdapter(adapter);

            donutQuantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                /**
                 * This method creates the listener for the donutQuantity Spinner.
                 * @param adapterView adapter view assigned to the spinner
                 * @param view view that contains the spinner
                 * @param position index of the selected item in the spinner
                 * @param id id of the spinner
                 */
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view,
                                           int position, long id) {
                        int quantity = Integer.parseInt(adapterView.getItemAtPosition(position).toString());
                        donutAdd = new Donut(getFlavor(), quantity);
                        setSubtotal();

                }

                /**
                 * This method is not used so it is left blank.
                 * @param adapterView adapter assigned to the spinner
                 */
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


        this.setSubtotal();
    }

    /**
     * Displays the running subtotal in the text view.
     */
    public void setSubtotal() {
        subtotalDonuts.setText("Subtotal: " + this.donutAdd.subtotalToString());
    }

    /**
     * Gets the flavor that the user has selected from the spinner.
     * @return flavor the user chose
     */
    public String getFlavor() {
        Spinner donutFlavorChosen = findViewById(R.id.flavorSpinner);
        return donutFlavorChosen.getSelectedItem().toString();
    }

    /**
     * Gets the quantity that the user has selected from the spinner.
     * @return quantity the user chose
     */
    public int getQuantity() {
        Spinner donutQuantityChosen = findViewById(R.id.donutQuantity);
        return Integer.parseInt(donutQuantityChosen.getSelectedItem().toString());
    }

    /**
     * Creates a donut object based on given parameters.
     * @param flavor Flavor of donut
     * @param quantity Quantity of donuts
     * @return donut the created donut
     */
    private MenuItem createDonut(String flavor, int quantity) {
        MenuItem donut = new Donut(flavor, quantity);
        return donut;
    }

    /**
     * Adds the donut to the current order when button is tapped.
     * @param view to interact with button
     */
    public void addDonuts(View view) {

        if (!getFlavor().equals("--Select a Flavor--")) {
            MenuItem donut = createDonut(getFlavor(), getQuantity());
            MainActivity.order.add(donut);

            toast = Toast.makeText(this, "Donuts Added!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
        }

        else {
            toast = Toast.makeText(this, "Please Select A Flavor!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
        }
    }

}
