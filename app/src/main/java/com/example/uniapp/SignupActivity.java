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


public class SignupActivity extends AppCompatActivity {

EditText fname, user, email, password, repass;
Button reg;
DBHelper DB;
TextView backward, loginr;



@SuppressLint("MissingInflatedId")
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signup);

//        login code
    fname= findViewById(R.id.fname);
    user= findViewById(R.id.user);
    email= findViewById(R.id.email);
    password= findViewById(R.id.password);
    repass= findViewById(R.id.repass);
    reg= findViewById(R.id.reg);
    DB=new DBHelper(this);
    backward = findViewById(R.id.backward);
    loginr = findViewById(R.id.loginr);

    backward.setOnClickListener( new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    });

    loginr.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    });

    reg.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String fullname = fname.getText().toString();
            String name = user.getText().toString();
            String emai = email.getText().toString();
            String pass = password.getText().toString();
            String re = repass.getText().toString();

            if (fullname.equals("") || name.equals("") || emai.equals("") || pass.equals("") || re.equals("")) {
                Toast.makeText(SignupActivity.this, "Please Fill in all the Fields", Toast.LENGTH_SHORT).show();
            } else {
                if (pass.equals(re)) {
                    Boolean checkusername = DB.checkusername(name);
                    if (!checkusername) {
                        Boolean insert = DB.insertdata(fullname, name, emai, pass);
                        if (insert) {
                            Toast.makeText(SignupActivity.this, "USER REGISTRATION SUCCESS", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(SignupActivity.this, "SORRY REGISTRATION FAILED", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignupActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        }
    });


}
}
