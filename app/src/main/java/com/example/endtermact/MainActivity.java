package com.example.endtermact;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    Button login,register;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.edtusername);
        password = findViewById(R.id.edtpassword);
        login    = findViewById(R.id.btnlogin);
        register = findViewById(R.id.btnsignup);

        sharedPreferences = getSharedPreferences("Userinfo", 0);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();

                String registeredUsername = sharedPreferences.getString("username", "");
                String registeredPassword = sharedPreferences.getString("password", "");

                if (usernameValue.equals(registeredUsername) && passwordValue.equals(registeredPassword)) {

                    Intent intent = new Intent(MainActivity.this, act_options.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Unauthorized User", Toast.LENGTH_SHORT).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}