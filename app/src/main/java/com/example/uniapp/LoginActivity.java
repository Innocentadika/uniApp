package com.example.uniapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText user2, password2;
    Button loginB;
    DBHelper DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize UI elements
        user2 = findViewById(R.id.user2);
        password2 = findViewById(R.id.password2);
        loginB = findViewById(R.id.loginB);
        DB = new DBHelper(this);

        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = user2.getText().toString();
                String pass = password2.getText().toString();

                if (name.equals("") || pass.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please Fill in all the Fields", Toast.LENGTH_SHORT).show();
                } else {
                    // method to check username & password
                    Boolean checkUserPass = DB.checkusernamepassword(name, pass);
                    if (checkUserPass) {
                        Toast.makeText(LoginActivity.this, "USER LOGIN SUCCESS", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
