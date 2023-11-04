package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {
    DbHelper DB;
    TextInputLayout useremail;
    TextInputLayout password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.loginBtn);
        Button registerButton = findViewById(R.id.registerBtn);
        useremail = findViewById(R.id.login);
        password = findViewById(R.id.password);

        DB = new DbHelper(this);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the RegisterActivity
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = useremail.getEditText().getText().toString();
                String pass = password.getEditText().getText().toString();
                if (user.equals("") || pass.equals("")){
                    Toast.makeText(Login.this,"please enter all fields",Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean checkuser= DB.checkUsernamePasswordEmail(user,pass);
                    Toast.makeText(Login.this,"email" + user,Toast.LENGTH_SHORT).show();
                    Toast.makeText(Login.this,"pass " + pass,Toast.LENGTH_SHORT).show();

                    if (checkuser){
                        Toast.makeText(Login.this,"login successfully",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(Login.this,"user dont exist, please register",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

    }
}