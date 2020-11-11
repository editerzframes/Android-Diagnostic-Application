package com.example.diagnostictool;

        import androidx.annotation.RequiresApi;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.os.BatteryManager;
        import android.os.Build;
        import android.os.Bundle;
        import android.util.Log;
        import android.widget.ProgressBar;
        import android.widget.TextView;


/**
 * <p>
 * Title: BatteryStatus.java
 * </p>
 *
 * <p>
 * Description: This is the class which fetch all the Battery information and states the health of battery
 * </p>

 * @author Puneet Garg
 * @version 3.00
 *
 */
public class BatteryStatus extends AppCompatActivity {



    ProgressBar pp;
    TextView t76;
    TextView temp;
    TextView volt;
    TextView stat;
    TextView tech;
    TextView scle;
    TextView health1;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_status);

        TextView t32 = findViewById(R.id.level);

        temp = findViewById(R.id.temp);
        volt = findViewById(R.id.volt);
        stat = findViewById(R.id.status);
        scle = findViewById(R.id.scale);
        tech = findViewById(R.id.technology);
        health1 = findViewById(R.id.health);

        this.registerReceiver(this.batteryInfoReceiver,	new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        pp = findViewById(R.id.progressBar3);

        BatteryManager bm = (BatteryManager)getSystemService(BATTERY_SERVICE);
        int batLevel = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);

        t32.setText(Integer.toString(batLevel) + "%");

        setSignalLevel(batLevel);
    }

    private void setSignalLevel(int level){

        int progress = (int) ((((float)level)/31.0) * 100);


        //set the status
        pp.setProgress(level);

        Log.i("signalLevel ","" + progress);
    }


    private BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int  health= intent.getIntExtra(BatteryManager.EXTRA_HEALTH,0);
            int  icon_small= intent.getIntExtra(BatteryManager.EXTRA_ICON_SMALL,0);
            int  level= intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
            int  plugged= intent.getIntExtra(String.valueOf(BatteryManager.BATTERY_STATUS_CHARGING),0);
            boolean  present= intent.getExtras().getBoolean(BatteryManager.EXTRA_PRESENT);
            int  scale= intent.getIntExtra(BatteryManager.EXTRA_SCALE,0);
            int  status= intent.getIntExtra(BatteryManager.EXTRA_STATUS,0);
            String  technology= intent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY);
            int  temperature= intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,0);
            int  voltage= intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE,0);


            temp.setText(Integer.toString(temperature) + " " + "K");
            volt.setText(Integer.toString(voltage) + " " +"mAh");
            stat.setText(getStatusString(status));
            tech.setText(technology);
            scle.setText(getPlugTypeString(plugged));
            health1.setText(getHealthString(health) );

        }
    };


    /*
     * Charging status of the Battery
     * */
    private String getStatusString(int status) {
        String statusString = "Unknown";

        switch (status) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                statusString = "Charging";
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                statusString = "Discharging";
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                statusString = "Full";
                break;
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                statusString = "Not Charging";
                break;
        }

        return statusString;
    }

    /*
     * Battery Plugged Information
     * */
    private String getPlugTypeString(int plugged) {
        String plugType = "Unknown";

        switch (plugged) {
            case BatteryManager.BATTERY_PLUGGED_AC:
                plugType = "AC";
                break;
            case BatteryManager.BATTERY_PLUGGED_USB:
                plugType = "USB";
                break;
        }

        return plugType;
    }

    /*
     * General health of the Battery
     * */
    private String getHealthString(int health) {
        String healthString = "Unknown";

        switch (health) {
            case BatteryManager.BATTERY_HEALTH_DEAD:
                healthString = "Dead";
                break;
            case BatteryManager.BATTERY_HEALTH_GOOD:
                healthString = "Good";
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                healthString = "Over Voltage";
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                healthString = "Over Heat";
                break;
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                healthString = "Failure";
                break;
        }

        return healthString;
    }

}

