package com.example.diagnostictool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * <p>
 * Title: LoginActivity.java
 * </p>
 *
 * <p>
 * Description: This is the class which asks the user for login id and password and allows to navigate to the next window on submission
 * </p>

 * @author Puneet Garg
 * @version 3.00
 *
 */
public class LoginActivity extends AppCompatActivity {
    public EditText editTxt;
    public EditText editTxt2;
    public EditText editTxt4;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTxt = findViewById(R.id.editText);
        editTxt2 = findViewById(R.id.editText2);
        editTxt4 = findViewById(R.id.editText4);

        login();

    }

    public void login() {
        ImageView b1 = (ImageView) findViewById(R.id.imageView9);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(editTxt.getText().toString().isEmpty()){
                    editTxt.setError("Field cannot be left blank.");
                }
                    if(editTxt2.getText().toString().isEmpty()){
                        editTxt2.setError("Please Enter Password.");
                    }

                if(editTxt4.getText().toString().isEmpty()) {
editTxt4.setError("Enter Valid Email Address");                }
                else
                    if (!editTxt4.getText().toString().trim().matches(emailPattern)) {
editTxt4.setError("Invalid Email Address");                    }

                else {
                    Intent myintent = new Intent(LoginActivity.this, WindowActivity.class);
                    myintent.putExtra("user", editTxt.getText().toString());
                    myintent.putExtra("email", editTxt4.getText().toString());
                    startActivity(myintent);
                }
            }
        });
    }



}
