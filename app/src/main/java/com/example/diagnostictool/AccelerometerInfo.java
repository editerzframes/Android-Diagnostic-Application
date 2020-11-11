package com.example.diagnostictool;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * <p>
 * Title: Accelerometer.java
 * </p>
 *
 * <p>
 * Description: This is the class which allows us to run the code for accelerometer
 * </p>

 * @author Puneet Garg
 * @version 3.00
 *
 */
public class AccelerometerInfo extends AppCompatActivity {

    SensorManager sm = null;
    TextView textView1 = null;
    TextView textView2 = null;
    TextView textView3 = null;
    List list;


    SensorEventListener sel = new SensorEventListener(){
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            textView1.setText(values[0]+" ");
            textView2.setText(values[1]+" ");
            textView3.setText(values[2]+" ");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer_info);
        /* Get a SensorManager instance */
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);

        textView1 = (TextView)findViewById(R.id.txaxis);
        textView2 = (TextView)findViewById(R.id.tyaxis);
        textView3 = (TextView)findViewById(R.id.tzaxis);

        list = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if(list.size()>0){
            sm.registerListener(sel, (Sensor) list.get(0), SensorManager.SENSOR_DELAY_FASTEST);
        }else{
            Toast.makeText(getBaseContext(), "Error: No Accelerometer.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStop() {
        if(list.size()>0){
            sm.unregisterListener(sel);
        }
        super.onStop();
    }
}