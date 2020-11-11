package com.example.diagnostictool;


import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;

/**
 * <p>
 * Title: OrientationSensor.java
 * </p>
 *
 * <p>
 * Description: This is the class which allows us to run the code for orientation sensor
 * </p>

 * @author Puneet Garg
 * @version 3.00
 *
 */
public class OrientationSensor extends Activity {

    Button button;
    TextView textview;
    Activity activity;
    String orientation;
    int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation_sensor);
        activity = OrientationSensor.this;
        textview = (TextView)findViewById(R.id.textView1);
        button = (Button)findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                value = activity.getResources().getConfiguration().orientation;

                if (value == Configuration.ORIENTATION_PORTRAIT) {

                    orientation = "Portrait";
                }

                if (value == Configuration.ORIENTATION_LANDSCAPE) {

                    orientation = "Landscape";
                }

                textview.setText(" Current Screen Orientation = " + orientation);

            }
        });
    }
}