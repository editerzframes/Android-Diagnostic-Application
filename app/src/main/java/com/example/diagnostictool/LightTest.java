package com.example.diagnostictool;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

public class LightTest extends Activity {

    TextView tv;
    SensorManager sm;
    SensorEventListener listener;
    Sensor light;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_test);
        tv = findViewById(R.id.tv);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        light = sm.getDefaultSensor(Sensor.TYPE_LIGHT);

        listener = new SensorEventListener() {

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
                Toast.makeText(LightTest.this, "accuracy changed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSensorChanged(SensorEvent event) {
                tv.setText(String.valueOf(event.values[0]));
                int grayShade = (int) event.values[0];
                if (grayShade > 255) grayShade = 255;

                tv.setTextColor(Color.rgb(255 - grayShade, 255 - grayShade, 255 - grayShade));
                tv.setBackgroundColor(Color.rgb(grayShade, grayShade, grayShade));
            }
        };

        sm.registerListener(listener, light, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(listener, light);
    }
}