package com.example.diagnostictool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

/**
 * <p>
 * Title: GPSInfo.java
 * </p>
 *
 * <p>
 * Description: This is the class which helps us diagnosing our GPS system on android phone
 * </p>

 * @author Puneet Garg
 * @version 3.00
 *
 */
public class GPSInfo extends AppCompatActivity {


    Button btnShowLocation;

    TextView longi;
    TextView lat;

    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    // GPSTracker class
    GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpsinfo);

        // check if the permission is granted
        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnShowLocation = (Button) findViewById(R.id.gpslocation);
        longi = findViewById(R.id.longitude);
        lat = findViewById(R.id.laditude);

        // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // create class object
                gps = new GPSTracker(GPSInfo.this);

                // check if GPS enabled
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
//
//                    // \n is for new line
//                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: "
//                            + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
//
                    longi.setText(Double.toString(longitude));
                    lat.setText(Double.toString(latitude));
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }

            }
        });
    }
    }

