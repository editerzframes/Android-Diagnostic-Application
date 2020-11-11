package com.example.diagnostictool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * <p>
 * Title: NetworkInfo.java
 * </p>
 *
 * <p>
 * Description: This is the class which fetch all the network information and displays it to the user.
 * </p>

 * @author Puneet Garg
 * @version 3.00
 *
 */
public class NetworkInfo extends AppCompatActivity {

    TelephonyManager telephonyManager;
    myPhoneStateListener psListener;
    TextView txtSignalStr;
    TextView t982;
    TextView t983;
    TextView t984;
    TextView t985;
    TextView t986;
    ImageView net;
    TextView t987;
    private static final int REQUEST_CODE = 101;
    private static final int EXCELLENT_LEVEL = 75;
    private static final int GOOD_LEVEL = 50;
    private static final int MODERATE_LEVEL = 25;
    private static final int WEAK_LEVEL = 0;
    public int signalStrengthValue;
    String signalLevelString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_info);
        txtSignalStr = (TextView)findViewById(R.id.signalService);
        t982 = (TextView)findViewById(R.id.connectionState);
        t983 = (TextView)findViewById(R.id.deviceID);
        t984 = (TextView)findViewById(R.id.networkOperator);
        t985 = (TextView)findViewById(R.id.simNumber);
        t986 = (TextView)findViewById(R.id.simCountry);
        net = findViewById(R.id.netState);
        t987 = (TextView)findViewById(R.id.levelsignal);


        psListener = new myPhoneStateListener();
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(psListener,PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);

        ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();

        //check the if the connection is established and the type of connection
        if(networkInfo != null && networkInfo.isConnected()==true && networkInfo.getType() == ConnectivityManager.TYPE_WIFI)
        {
            t982.setText("Wifi Connected");
            net.setBackgroundResource(R.drawable.wificonnected);


        }
        else if(networkInfo != null && networkInfo.isConnected()==true && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE)
        {
            t982.setText("Sim Internet Connected");
            net.setBackgroundResource(R.drawable.internetconnected);


        }
        else
        {
            t982.setText("Not Connected");
            net.setBackgroundResource(R.drawable.wifinotconnected );

        }

        //check if the permission is granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE);
            return;
        }

        //get the IMEI ID
        String IMEINumber = telephonyManager.getDeviceId();
        t983.setText(IMEINumber);

        //get the network operator name
        String networkOper = telephonyManager.getNetworkOperatorName();
        t984.setText(networkOper);

        //get the phone number of the network operator
        String simNumber = telephonyManager.getLine1Number();
        t985.setText(simNumber);

        //get the country code (for ex: IN)
        String country = telephonyManager.getSimCountryIso();
        t986.setText(country);

    }

    // class to get the signal strength
    public class myPhoneStateListener extends PhoneStateListener {

        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            if (signalStrength.isGsm()) {
                if (signalStrength.getGsmSignalStrength() != 99)
                    signalStrengthValue = signalStrength.getGsmSignalStrength() * 2 - 113;
                else
                    signalStrengthValue = signalStrength.getGsmSignalStrength();
            } else {
                signalStrengthValue = signalStrength.getCdmaDbm();
            }
            txtSignalStr.setText(Integer.toString(signalStrengthValue));
            String value = getSignalLevelString(signalStrengthValue);

            t987.setText(value);

        }
    }

    //method to check the permission granted by the user
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission denied.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    // method to get the feedback about the signal level
    private String getSignalLevelString(int level) {
        signalLevelString = "Poor";
        
        if(level > EXCELLENT_LEVEL)             signalLevelString = "Excellent";
        else if(level > GOOD_LEVEL)             signalLevelString = "Good";
        else if(level > MODERATE_LEVEL) 		signalLevelString = "Moderate";
        else if(level > WEAK_LEVEL)             signalLevelString = "Weak";

        return signalLevelString;
    }

}
