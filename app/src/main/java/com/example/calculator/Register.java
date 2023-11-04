package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Register extends AppCompatActivity {
    DbHelper DB;

    Button register ;
    TextInputLayout firstname;
    TextInputLayout lastname;
    TextInputLayout email;
    TextInputLayout password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        DB = new DbHelper(this);

        ImageButton returnButton = findViewById(R.id.returnBtn);
        register = findViewById(R.id.register);
        firstname = findViewById(R.id.firstName);
        lastname = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the RegisterActivity
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = firstname.getEditText().getText().toString();
                String lname = lastname.getEditText().getText().toString();
                String userName= fname + " "+ lname;
                String userEmail = email.getEditText().getText().toString();
                String userPassword = password.getEditText().getText().toString();
                if (fname.equals("") || lname.equals("") || userEmail.equals("") || userPassword.equals("")){
                    Toast.makeText(Register.this,"please enter all fields",Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean checkuser = DB.checkUser(userEmail);
                    if(!checkuser){
                        boolean insert = DB.insertData(fname,userEmail,userPassword);
                        if ( insert ) {
                            Toast.makeText(Register.this,"Registration Scucess",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Register.this, MainActivity.class);
                        startActivity(intent);
                        }
                        else {
                            Toast.makeText(Register.this,"Registration failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(Register.this,"user already exists, Sign in",Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });


    }
}