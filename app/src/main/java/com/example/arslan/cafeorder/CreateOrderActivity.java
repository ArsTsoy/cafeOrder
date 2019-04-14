package com.example.arslan.cafeorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateOrderActivity extends AppCompatActivity {

    private TextView textViewHello;
    private TextView textViewAdditions;
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxSugar;
    private CheckBox checkBoxLemon;
    private Spinner spinnerTea;
    private Spinner spinnerCoffee;

    private String drink;
    private String name;
    private String password;

    private StringBuilder additionsBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);


        Intent intent = getIntent();

        if(intent.hasExtra("Login") && intent.hasExtra("Password")){

            name = intent.getStringExtra("Login");
            password = intent.getStringExtra("Password");
        }else{
            name = getString(R.string.default_name);
            password = getString(R.string.default_pass);
        }
        additionsBuilder = new StringBuilder();



        textViewAdditions = findViewById(R.id.textViewAddition);
        drink = getString(R.string.tea);
        String additions = String.format(getString(R.string.additions), drink);
        textViewAdditions.setText(additions);


        textViewHello = findViewById(R.id.textViewHello);
        checkBoxMilk = findViewById(R.id.checkBoxMilk);
        checkBoxSugar = findViewById(R.id.checkBoxSugar);
        checkBoxLemon = findViewById(R.id.checkBoxLemon);
        spinnerTea = findViewById(R.id.spinnerTea);
        spinnerCoffee = findViewById(R.id.spinnerCoffee);


        String hello = String.format(getString(R.string.hello_user), name );
        textViewHello.setText(hello);
    }

    public void onClickChangeDrink(View view) {
        RadioButton radioButton = (RadioButton)view;
        switch (radioButton.getId()){
            case R.id.radioButtonTea:
                drink = getString(R.string.tea);
                spinnerTea.setVisibility(View.VISIBLE);
                spinnerCoffee.setVisibility(View.INVISIBLE);
                checkBoxLemon.setVisibility(View.VISIBLE);
                break;
            case R.id.radioButtonCoffee:
                drink = getString(R.string.coffee);
                spinnerTea.setVisibility(View.INVISIBLE);
                spinnerCoffee.setVisibility(View.VISIBLE);
                checkBoxLemon.setVisibility(View.INVISIBLE);
                break;
        }
        String additions = String.format(getString(R.string.additions), drink);
        textViewAdditions.setText(additions);

    }

    public void onClickSendOrder(View view) {

        additionsBuilder.setLength(0);
        if(checkBoxMilk.isChecked()){
            additionsBuilder.append(getString(R.string.milk)).append(" ");
        }
        if(checkBoxLemon.isChecked() && drink.equals(getString(R.string.tea))){
            additionsBuilder.append(getString(R.string.lemon)).append(" ");
        }
        if(checkBoxSugar.isChecked() ){
            additionsBuilder.append(getString(R.string.sugar)).append(" ");
        }
        String drink_type = null;
        if(drink.equals(getString(R.string.tea))){
            drink_type = spinnerTea.getSelectedItem().toString();
        }else{
            drink_type = spinnerCoffee.getSelectedItem().toString();
        }

        String order = String.format(getString(R.string.order), name, password, drink, drink_type);


        String additions;
        if(additionsBuilder.length() > 0){
            additions = getString(R.string.need_additions) + additionsBuilder.toString();
        }else{
            additions = "";
        }

        String fullOrder = order + additions;


        Intent intent = new Intent(this, OrderDetailActivity.class);
        intent.putExtra("Order", fullOrder);
        startActivity(intent);



    }
}
