package com.example.endtermact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class act_buy_options extends AppCompatActivity {
    Button btnBrowse, btnRequest, btnedtRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_buy_options);

        btnBrowse = findViewById(R.id.btnbrowse);
        btnRequest = findViewById(R.id.btnrequest);
        btnedtRequest = findViewById(R.id.btnedtrequest);

        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (act_buy_options.this, act_buy_browse.class);
                startActivity(intent);
            }
        });


        btnedtRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (act_buy_options.this, act_buy_manage.class);
                startActivity(intent);
            }
        });
    }
}