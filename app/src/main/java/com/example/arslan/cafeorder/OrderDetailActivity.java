package com.example.arslan.cafeorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OrderDetailActivity extends AppCompatActivity {

    private TextView orderDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        orderDetail = findViewById(R.id.orderDetail);

        Intent intent = getIntent();
        if(intent.hasExtra("Order")){
            String order = intent.getStringExtra("Order");
            orderDetail.setText(order);
        } else{
            Intent backToLogin = new Intent(this, LoginActivity.class);
            startActivity(backToLogin);
        }
    }
}
