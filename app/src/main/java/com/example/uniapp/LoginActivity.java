package com.example.uniapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText user2, password2;
    Button loginB;
    DBHelper DB;
    TextView backward;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user2 = findViewById(R.id.user);
        password2 = findViewById(R.id.password);
        loginB = findViewById(R.id.loginB);
        DB = new DBHelper(this);
        backward = findViewById(R.id.backward);

        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

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
