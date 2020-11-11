package com.example.diagnostictool;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * <p>
 * Title: FullDia.java
 * </p>
 *
 * <p>
 * Description: This is the class which allows the user to fully diagnos the application
 * </p>

 * @author Puneet Garg
 * @version 3.00
 *
 */
public class fulldia extends AppCompatActivity {

    ListView list;
    String[] itemname= {"Battery","Bluetooth","GPS", "Internal Storage","SDCard","CPU",
            "NetworkInfo","Sensor","Fingerprint","ProximitySensor","Vibrator","Speaker","EarpieceSpeaker","Microphone",
            "Earphones","USB","VolumeButtons","TouchScreen","MultiTouch","DeadSpots","Brightness","BackCamera","FrontCamera",
            "Flashlight"};

    String[] software = {"Bluetooth", "GPS", "Network Info", "Sensor", "Fingerprint", "ProximitySensor" };

    String[] hardware = {"Battery", "Internal Storage", "SDCard", "CPU", "Speaker", "EarpieceSpeaker", "Microphone", "USB", "VolumeButtons", "BackCamera", "FrontCamera"};

    String[] others = {"Vibrator", "Earphones", "TouchScreen", "MultiTouch", "DeadSpots", "Brightness", "Flashlight"};


    Integer[] imgid={
            R.drawable.icon_battery,
            R.drawable.icon_bluetooth,
            R.drawable.icon_gps,
            R.drawable.icon_storage,
            R.drawable.icon_sd,
            R.drawable.icon_cpu,
            R.drawable.icon_network,
            R.drawable.icon_sensor,
            R.drawable.icon_fingerprint,
            R.drawable.icon_sensor,
            R.drawable.icon_vibrate,
            R.drawable.icon_speaker,
            R.drawable.icon_earpeice,
            R.drawable.icon_microphone,
            R.drawable.icon_earphones,
            R.drawable.icon_usb,
            R.drawable.icon_volume,
            R.drawable.icon_touch,
            R.drawable.icon_touch,
            R.drawable.icon_deadspot,
            R.drawable.icon_brightness,
            R.drawable.icon_backcam,
            R.drawable.icon_frontcam,
            R.drawable.icon_flashlight,
            R.drawable.editerzframes,
    };
    Integer[] soft ={
            R.drawable.icon_bluetooth,
            R.drawable.icon_gps,
            R.drawable.icon_network,
            R.drawable.icon_sensor,
            R.drawable.icon_fingerprint,
            R.drawable.icon_sensor,

    };
    Integer[] hard={
            R.drawable.icon_battery,
            R.drawable.icon_storage,
            R.drawable.icon_sd,
            R.drawable.icon_cpu,
            R.drawable.icon_speaker,
            R.drawable.icon_earpeice,
            R.drawable.icon_microphone,
            R.drawable.icon_usb,
            R.drawable.icon_volume,
            R.drawable.icon_backcam,
            R.drawable.icon_frontcam,
    };

    Integer[] other={
            R.drawable.icon_vibrate,
            R.drawable.icon_earphones,
            R.drawable.icon_touch,
            R.drawable.icon_touch,
            R.drawable.icon_deadspot,
            R.drawable.icon_brightness,
            R.drawable.icon_flashlight,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fulldia);

        final Button start = findViewById(R.id.button4);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startdia = new Intent(fulldia.this, Resultscreen.class);
                startActivity(startdia);
            }
        });


        CustomListAdapter adapter = new CustomListAdapter(this, itemname, imgid);
        list = (ListView) findViewById(R.id.list2);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = itemname[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.filter);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // do something to filter items on listview
                if (checkedId==R.id.radioButton6) {
                    software();
                }
                else if(checkedId == R.id.radioButton4){
                    defaul();
                }
                else if(checkedId==R.id.radioButton5){
                    hardware();
                }
                else if(checkedId==R.id.radioButton7){
                    other();
                }
            }
        });

    }

    private void software() {
      CustomListAdapter  adapter1 = new CustomListAdapter(this, software, soft);
        list = (ListView) findViewById(R.id.list2);
        list.setAdapter(adapter1);

        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = software[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });


    }
    private void hardware() {
        CustomListAdapter  adapter1 = new CustomListAdapter(this, hardware, hard);
        list = (ListView) findViewById(R.id.list2);
        list.setAdapter(adapter1);

        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = hardware[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });


    }
    private void other() {
        CustomListAdapter  adapter1 = new CustomListAdapter(this, others, other);
        list = (ListView) findViewById(R.id.list2);
        list.setAdapter(adapter1);

        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = others[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });


    }
    private void defaul() {
        CustomListAdapter  adapter1 = new CustomListAdapter(this, itemname, imgid);
        list = (ListView) findViewById(R.id.list2);
        list.setAdapter(adapter1);

        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = itemname[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });


    }
}
