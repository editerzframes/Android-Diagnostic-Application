package com.example.diagnostictool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * <p>
 * Title: DiagnosisActivity.java
 * </p>
 *
 * <p>
 * Description: This is the class which allows the user to select either full diagnosis option or custom diagnosis option
 * </p>

 * @author Puneet Garg
 * @version 3.00
 *
 */
public class DiagnosActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnos);

        CardView c1 = findViewById(R.id.c3);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view ) {
                Intent c1intent = new Intent(DiagnosActivity.this, fulldia.class);
                startActivity(c1intent);
            }
        });

        final CardView c2 = findViewById(R.id.c4);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c2intent = new Intent(DiagnosActivity.this, customdiagnosis.class);
                startActivity(c2intent);
            }
        });

    }
}