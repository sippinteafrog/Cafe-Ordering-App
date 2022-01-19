package com.example.ru_cafe;

import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This activity class contains the variables and methods of the Coffee GUI and handles all
 * user input.
 * @author AbdulAhad Butt, Paul Cusa
 */
public class CoffeeActivity extends AppCompatActivity {

    private TextView subtotal;
    private Coffee coffee = new Coffee("Short", 1);
    private CheckBox milkCheckbox;
    private CheckBox caramelCheckbox;
    private CheckBox syrupCheckbox;
    private CheckBox whippedCreamCheckbox;
    private CheckBox creamCheckbox;
    private Button addCoffeeButton;
    private Spinner coffeeSizeSpinner;

    /**
     * This method initializes all necessary aspects of the Coffee GUI upon its creation.
     * @param savedInstanceState saved instance state of the program
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_coffee);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        subtotal = findViewById(R.id.subtotalTextView);

        milkCheckbox = findViewById(R.id.milkCheckbox);
        caramelCheckbox = findViewById(R.id.caramelCheckbox);
        syrupCheckbox = findViewById(R.id.syrupCheckbox);
        whippedCreamCheckbox = findViewById(R.id.whippedCreamCheckbox);
        creamCheckbox = findViewById(R.id.creamCheckbox);

        addCoffeeButton= findViewById(R.id.addCoffeeButton);

        List<String> spinnerArray = new ArrayList<>();
        spinnerArray.add("Short");
        spinnerArray.add("Tall");
        spinnerArray.add("Grande");
        spinnerArray.add("Venti");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coffeeSizeSpinner = findViewById(R.id.coffeeSizeSpinner);
        coffeeSizeSpinner.setAdapter(adapter);

        coffeeSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            /**
             * This method creates the listener for the coffeeSizeSpinner.
             * @param adapterView adapter view assigned to the spinner
             * @param view view that contains the spinner
             * @param position index of the selected item in the spinner
             * @param id id of the spinner
             */
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                String size = adapterView.getItemAtPosition(position).toString();
                coffee = new Coffee(size, 1);
                setAddIns();
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
     * This method sets the subtotal TextView to the appropriate value.
     */
    private void setSubtotal() {
        subtotal.setText("Subtotal: " + this.coffee.subtotalToString());
    }

    /**
     * This method adds the selected add-ins to the coffee object and makes sure no extra
     * add-ins have been added.
     */
    private void setAddIns() {
        if (milkCheckbox.isChecked()) {
            this.coffee.add("Milk");
        }

        else {
            this.coffee.remove("Milk");
        }

        if (caramelCheckbox.isChecked()) {
            this.coffee.add("Caramel");
        }

        else {
            this.coffee.remove("Caramel");
        }

        if (syrupCheckbox.isChecked()) {
            this.coffee.add("Syrup");
        }

        else {
            this.coffee.remove("Syrup");
        }

        if (whippedCreamCheckbox.isChecked()) {
            this.coffee.add("Whipped Cream");
        }

        else {
            this.coffee.remove("Whipped Cream");
        }

        if (creamCheckbox.isChecked()) {
            this.coffee.add("Cream");
        }

        else {
            this.coffee.remove("Cream");
        }
    }

    /**
     * This method first sets the add-ins for the coffee object, then sets the subtotal
     * TextView to the appropriate value.
     * @param view view that has been clicked by user
     */
    public void checkBoxSelected(View view) {
        this.setAddIns();
        this.setSubtotal();
    }

    /**
     * This method adds the users desired coffee to their current order, then displays a
     * Toast message informing the user of the coffee addition (or failure of addition, if that occurs).
     * @param view view that has been clicked by user
     */
    public void addCoffee(View view) {

        if (MainActivity.order.add(this.coffee)) {
            Toast.makeText(this, "Coffee added to order.", Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(this, "Unable to add coffee to order, please try again.", Toast.LENGTH_SHORT).show();
        }

        coffeeSizeSpinner.setSelection(0);
        clearCheckboxes();
        this.coffee = new Coffee("Short", 1);
        this.setAddIns();
        this.setSubtotal();
    }

    /**
     * This method sets all the checkboxes of the Coffee GUI to their default (unchecked).
     */
    private void clearCheckboxes() {
        milkCheckbox.setChecked(false);
        syrupCheckbox.setChecked(false);
        whippedCreamCheckbox.setChecked(false);
        creamCheckbox.setChecked(false);
        caramelCheckbox.setChecked(false);
    }


}
