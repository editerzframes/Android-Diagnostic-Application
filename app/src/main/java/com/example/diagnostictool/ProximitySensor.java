package com.example.diagnostictool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * <p>
 * Title: ProximitySensor.java
 * </p>
 *
 * <p>
 * Description: This is the class which allows us to run the code for Proximity Sensor
 * </p>

 * @author Puneet Garg
 * @version 3.00
 *
 */
public class ProximitySensor extends AppCompatActivity {

    TextView ProximitySensor, data;
    SensorManager mySensorManager;
    Sensor myProximitySensor;
    ImageView lightonoff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_sensor);
        ProximitySensor = (TextView) findViewById(R.id.proximitySensor);
        data = (TextView) findViewById(R.id.data);
        lightonoff = (ImageView) findViewById(R.id.main_light);

        mySensorManager = (SensorManager) getSystemService(
                Context.SENSOR_SERVICE);
        myProximitySensor = mySensorManager.getDefaultSensor(
                Sensor.TYPE_PROXIMITY);
        if (myProximitySensor == null) {
            ProximitySensor.setText("No Proximity Sensor!");
        } else {
            mySensorManager.registerListener(proximitySensorEventListener,
                    myProximitySensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    SensorEventListener proximitySensorEventListener
            = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onSensorChanged(SensorEvent event) {
            // TODO Auto-generated method stub
            if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                if (event.values[0] == 0) {
                    data.setText("Near");
                    lightonoff.setImageResource(R.drawable.lightoffpp);
                } else {
                    data.setText("Away");
                    lightonoff.setImageResource(R.drawable.lightonpp);
                }
            }
        }
    };
}