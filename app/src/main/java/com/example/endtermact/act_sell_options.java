package com.example.endtermact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class act_sell_options extends AppCompatActivity {
    Button btnCreate, btnselledit, btnbuyrequests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_sell_options);

        btnCreate = findViewById(R.id.btnbrowse);
        btnselledit = findViewById(R.id.btnrequest);
        btnbuyrequests = findViewById(R.id.btnedtrequest);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (act_sell_options.this, act_sell.class);
                startActivity(intent);
            }
        });

        btnselledit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (act_sell_options.this, act_sell_manage.class);
                startActivity(intent);
            }
        });

    }
}