package com.example.diagnostictool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * <p>
 * Title: GravitySensor.java
 * </p>
 *
 * <p>
 * Description: This is the class which allows us to run the code for gravity sensor
 * </p>

 * @author Puneet Garg
 * @version 3.00
 *
 */
public class GravitySensor extends AppCompatActivity {

    private ImageView bg;
    private GravityView gravityView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravity_sensor);
        bg = (ImageView) findViewById(R.id.bg);
        gravityView = GravityView.getInstance(this)
                .setImage(bg, R.drawable.landingbg)
                .center();

        if (!gravityView.deviceSupported()) {
            Toast.makeText(getBaseContext(), "Gyroscope sensor not available in your device", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        gravityView.registerListener();
    }

    @Override
    protected void onStop() {
        super.onStop();
        gravityView.unRegisterListener();
    }
}