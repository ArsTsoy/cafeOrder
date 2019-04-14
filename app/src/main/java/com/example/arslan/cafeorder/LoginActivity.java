package com.example.arslan.cafeorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText login, password;
    private Button enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.editTextLogin);
        password = findViewById(R.id.editTextPassword);
        enter = findViewById(R.id.buttonEnter);

        if(savedInstanceState != null){
            login.setText(savedInstanceState.getString("login"));
            password.setText(savedInstanceState.getString("password"));
        }


    }


    public void onClickEnterButton(View view){

        String login = this.login.getText().toString().trim();
        String password = this.password.getText().toString().trim();
        if(!login.isEmpty() && !password.isEmpty()){

            Intent intent = new Intent(this, CreateOrderActivity.class);

            intent.putExtra("Login", login);
            intent.putExtra("Password", password);
            startActivity(intent);
        }else{
            Toast.makeText(this, getResources().getString(R.string.toast_add_info), Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("login", login.getText().toString());
        outState.putString("password", password.getText().toString());
    }
}
