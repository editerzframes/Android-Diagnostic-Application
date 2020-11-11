package com.example.diagnostictool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import org.w3c.dom.Text;

/**
 * <p>
 * Title: MainActivity.java
 * </p>
 *
 * <p>
 * Description: This is the main page of our android application which has the basic information about the application.
 * </p>

 * @author Puneet Garg
 * @version 3.00
 *
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginactivity();

        //image view to open instagram id
        ImageView insta = (ImageView) findViewById(R.id.imageView3);
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://instagram.com/_u/puneet____garg");


                Intent i= new Intent(Intent.ACTION_VIEW,uri);

                i.setPackage("com.instagram.android");

                try {
                    startActivity(i);
                } catch (ActivityNotFoundException e) {

                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/puneet____garg")));
                }
            }
        });

        // image view to open facebook id
        ImageView fb = (ImageView) findViewById(R.id.imageView2);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.facebook.com/puneet.garg.77377692");


                Intent i= new Intent(Intent.ACTION_VIEW,uri);

                i.setPackage("com.facebook.android");

                try {
                    startActivity(i);
                } catch (ActivityNotFoundException e) {

                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.facebook.com/puneet.garg.77377692")));
                }
            }
        });

        CardView c34 = findViewById(R.id.c31);
        c34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent firstabout = new Intent(MainActivity.this, ContactUs.class);
                startActivity(firstabout);
            }
        });
    }

    //this method opens the login activity to allow user to login
    public void loginactivity() {
        ImageView b1 = (ImageView) findViewById(R.id.imageView6);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(myintent);
            }
        });
    }
}
