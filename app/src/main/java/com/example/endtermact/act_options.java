package com.example.endtermact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class act_options extends AppCompatActivity {
    Button btnBuy,btnSell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_options);

        btnBuy = findViewById(R.id.btnrequest);
        btnSell = findViewById(R.id.btnedtrequest);

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (act_options.this, act_buy_options.class);
                startActivity(intent);
            }
        });

        btnSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (act_options.this,act_sell_options.class);
                startActivity(intent);
            }
        });


    }
}