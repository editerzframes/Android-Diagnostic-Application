package com.example.diagnostictool;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * Title: CustomDiagnosis.java
 * </p>
 *
 * <p>
 * Description: This is the class which allows the user to diagnos the complete application
 * </p>

 * @author Puneet Garg
 * @version 3.00
 *
 */
public class customdiagnosis extends AppCompatActivity {

    ListView list;
    CheckBox cb;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyUserChoice" ;
    ArrayList<String> selectedItems = new ArrayList<String>();
    DatabaseHelper myDb;

    //arrays to store the content to be shown in the list view
    String[] itemname= {"Battery","Bluetooth","GPS","InternalStorage","Accelerometer",
            "NetworkInfo","Gyroscope","Fingerprint","ProximitySensor","Vibrator","Speaker","EarpieceSpeaker","Sound Test",
            "Magnetometer","Gravity Sensor","VolumeButtons","Orientation Sensor","Rotational Vector Sensor","DeadSpots","Light Test","BackCamera","FrontCamera",
            "Flashlight"};

    String[] software = {"Bluetooth", "GPS", "Network Info", "Gyroscope", "Fingerprint", "ProximitySensor" };

    String[] hardware = {"Battery", "Internal Storage", "Accelerometer", "Speaker", "EarpieceSpeaker", "Sound Test", "Gravity Sensor", "VolumeButtons", "BackCamera", "FrontCamera"};

    String[] others = {"Vibrator", "Magnetometer", "Orientation Sensor", "Rotational Vector Sensor", "DeadSpots", "Light Test", "Flashlight"};


    Integer[] imgid={
            R.drawable.icon_battery,
            R.drawable.icon_bluetooth,
            R.drawable.icon_gps,
            R.drawable.icon_storage,
            R.drawable.icon_sd,
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
        setContentView(R.layout.activity_customdiagnosis);


        CustomListAdapter2 adapter=new CustomListAdapter2(this, itemname, imgid);

        list=(ListView)findViewById(R.id.list2);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = itemname[+position];

                Toast.makeText(getApplicationContext(), "Test performed: "+Slecteditem, Toast.LENGTH_SHORT).show();


                //if battery is selected, then open the Battery information
                if(Slecteditem == "Battery"){
                   try{ Intent startdia = new Intent(customdiagnosis.this, BatteryStatus.class);
                    startActivity(startdia);
                   insertData(Slecteditem, "pass");
                   }
                   catch (Exception e){
                       insertData(Slecteditem, "fail");
                   }
                }

                //if Backcamera or front camera is selected, then open the camera to diagnos
                else if(Slecteditem == "BackCamera"){
                   try{ Intent camera = new Intent(customdiagnosis.this, CameraStatus.class);
                    startActivity(camera);
                    insertData(Slecteditem, "pass");
                }
                   catch (Exception e){
                    insertData(Slecteditem, "fail");
                }
                }

                else if(Slecteditem == "FrontCamera"){
                    try{Intent frontcamera = new Intent(customdiagnosis.this, CameraStatus.class);
                    startActivity(frontcamera);
                    insertData(Slecteditem, "pass");
                }
                   catch (Exception e){
                    insertData(Slecteditem, "fail");
                }
                }

                //if Internal Storage is selected, then open the INternal Storage information
                else if(Slecteditem == "InternalStorage"){
                 try{   Intent internalstorage = new Intent(customdiagnosis.this, InternalMemoryStatus.class);
                    startActivity(internalstorage);
                    insertData(Slecteditem, "pass");
                }
                   catch (Exception e){
                    insertData(Slecteditem, "fail");
                }
                }

                // If network Info is selected, then open the Network Info class
                else if(Slecteditem == "NetworkInfo"){
                    try{Intent networkInfo = new Intent(customdiagnosis.this, NetworkInfo.class);
                    startActivity(networkInfo);
                    insertData(Slecteditem, "pass");
                }
                   catch (Exception e){
                    insertData(Slecteditem, "fail");
                }
                }

                // if GPS is selected, the show the information about GPS
                else if(Slecteditem == "GPS"){
                   try{ Intent gps = new Intent(customdiagnosis.this, GPSInfo.class);
                    startActivity(gps);
                    insertData(Slecteditem, "pass");
                }
                   catch (Exception e){
                    insertData(Slecteditem, "fail");
                }
                }

                // if flashlight is selected, then open the flashlight class to allow user to perform actions
                else if(Slecteditem == "Flashlight"){
                    try{Intent flash = new Intent(customdiagnosis.this, FlashlightInfo.class);
                    startActivity(flash);
                    insertData(Slecteditem, "pass");
                }
                   catch (Exception e){
                    insertData(Slecteditem, "fail");
                }
                }

                // this will run the accelerometer
                else if(Slecteditem == "Accelerometer"){
                   try{ Intent acclerometer = new Intent(customdiagnosis.this, AccelerometerInfo.class);
                    startActivity(acclerometer);
                    insertData(Slecteditem, "pass");
                }
                   catch (Exception e){
                    insertData(Slecteditem, "fail");
                }
                }

                else if(Slecteditem == "Gyroscope"){
                    try{Intent gyroscope = new Intent(customdiagnosis.this, GyroscopeInfo.class);
                    startActivity(gyroscope);
                    insertData(Slecteditem, "pass");
                }
                   catch (Exception e){
                    insertData(Slecteditem, "fail");
                }
                }

                else if(Slecteditem == "Gravity Sensor"){
                   try{ Intent gravity = new Intent(customdiagnosis.this, GravitySensor.class);
                    startActivity(gravity);
                    insertData(Slecteditem, "pass");
                }
                   catch (Exception e){
                    insertData(Slecteditem, "fail");
                }
                }

                else if(Slecteditem == "ProximitySensor"){
                    try{Intent proxy = new Intent(customdiagnosis.this, ProximitySensor.class);
                    startActivity(proxy);
                    insertData(Slecteditem, "pass");
                }
                   catch (Exception e){
                    insertData(Slecteditem, "fail");
                }
                }

                else if(Slecteditem == "Magnetometer"){
                   try{ Intent magent = new Intent(customdiagnosis.this, Magnetometer.class);
                    startActivity(magent);
                    insertData(Slecteditem, "pass");
                }
                   catch (Exception e){
                    insertData(Slecteditem, "fail");
                }
                }

                else if(Slecteditem == "Light Test"){
                    try{Intent light = new Intent(customdiagnosis.this, LightTest.class);
                    startActivity(light);
                    insertData(Slecteditem, "pass");
                }
                   catch (Exception e){
                    insertData(Slecteditem, "fail");
                }
                }

                else if(Slecteditem == "Sound Test"){
                    try{Intent sound = new Intent(customdiagnosis.this, SoundTest.class);
                    startActivity(sound);
                    insertData(Slecteditem, "pass");
                }
                   catch (Exception e){
                    insertData(Slecteditem, "fail");
                }
                }

                else if(Slecteditem == "Rotational Vector Sensor"){
                    try{Intent rot = new Intent(customdiagnosis.this, RotationalVectorSensor.class);
                    startActivity(rot);
                    insertData(Slecteditem, "pass");
                }
                   catch (Exception e){
                    insertData(Slecteditem, "fail");
                }
                }

                else if(Slecteditem == "Orientation Sensor"){
                    try{Intent ori = new Intent(customdiagnosis.this, OrientationSensor.class);
                    startActivity(ori);
                    insertData(Slecteditem, "pass");
                }
                   catch (Exception e){
                    insertData(Slecteditem, "fail");
                }
                }


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

    //this method will be called when the software tab is selected
    private void software() {
        CustomListAdapter2  adapter1 = new CustomListAdapter2(this, software, soft);
        list = (ListView) findViewById(R.id.list2);
        list.setAdapter(adapter1);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = software[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

                //if battery is selected, then open the Battery information
                if(Slecteditem == "Battery"){
                    try{ Intent startdia = new Intent(customdiagnosis.this, BatteryStatus.class);
                        startActivity(startdia);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                //if Backcamera or front camera is selected, then open the camera to diagnos
                else if(Slecteditem == "BackCamera"){
                    try{ Intent camera = new Intent(customdiagnosis.this, CameraStatus.class);
                        startActivity(camera);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "FrontCamera"){
                    try{Intent frontcamera = new Intent(customdiagnosis.this, CameraStatus.class);
                        startActivity(frontcamera);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                //if Internal Storage is selected, then open the INternal Storage information
                else if(Slecteditem == "InternalStorage"){
                    try{   Intent internalstorage = new Intent(customdiagnosis.this, InternalMemoryStatus.class);
                        startActivity(internalstorage);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                // If network Info is selected, then open the Network Info class
                else if(Slecteditem == "NetworkInfo"){
                    try{Intent networkInfo = new Intent(customdiagnosis.this, NetworkInfo.class);
                        startActivity(networkInfo);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                // if GPS is selected, the show the information about GPS
                else if(Slecteditem == "GPS"){
                    try{ Intent gps = new Intent(customdiagnosis.this, GPSInfo.class);
                        startActivity(gps);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                // if flashlight is selected, then open the flashlight class to allow user to perform actions
                else if(Slecteditem == "Flashlight"){
                    try{Intent flash = new Intent(customdiagnosis.this, FlashlightInfo.class);
                        startActivity(flash);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                // this will run the accelerometer
                else if(Slecteditem == "Accelerometer"){
                    try{ Intent acclerometer = new Intent(customdiagnosis.this, AccelerometerInfo.class);
                        startActivity(acclerometer);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Gyroscope"){
                    try{Intent gyroscope = new Intent(customdiagnosis.this, GyroscopeInfo.class);
                        startActivity(gyroscope);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Gravity Sensor"){
                    try{ Intent gravity = new Intent(customdiagnosis.this, GravitySensor.class);
                        startActivity(gravity);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "ProximitySensor"){
                    try{Intent proxy = new Intent(customdiagnosis.this, ProximitySensor.class);
                        startActivity(proxy);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Magnetometer"){
                    try{ Intent magent = new Intent(customdiagnosis.this, Magnetometer.class);
                        startActivity(magent);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Light Test"){
                    try{Intent light = new Intent(customdiagnosis.this, LightTest.class);
                        startActivity(light);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Sound Test"){
                    try{Intent sound = new Intent(customdiagnosis.this, SoundTest.class);
                        startActivity(sound);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Rotational Vector Sensor"){
                    try{Intent rot = new Intent(customdiagnosis.this, RotationalVectorSensor.class);
                        startActivity(rot);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Orientation Sensor"){
                    try{Intent ori = new Intent(customdiagnosis.this, OrientationSensor.class);
                        startActivity(ori);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

            }
        });

    }

    //this method will be called when hardware tab is selected
    private void hardware() {
        CustomListAdapter2  adapter1 = new CustomListAdapter2(this, hardware, hard);
        list = (ListView) findViewById(R.id.list2);
        list.setAdapter(adapter1);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = hardware[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

                //if battery is selected, then open the Battery information
                if(Slecteditem == "Battery"){
                    try{ Intent startdia = new Intent(customdiagnosis.this, BatteryStatus.class);
                        startActivity(startdia);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                //if Backcamera or front camera is selected, then open the camera to diagnos
                else if(Slecteditem == "BackCamera"){
                    try{ Intent camera = new Intent(customdiagnosis.this, CameraStatus.class);
                        startActivity(camera);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "FrontCamera"){
                    try{Intent frontcamera = new Intent(customdiagnosis.this, CameraStatus.class);
                        startActivity(frontcamera);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                //if Internal Storage is selected, then open the INternal Storage information
                else if(Slecteditem == "InternalStorage"){
                    try{   Intent internalstorage = new Intent(customdiagnosis.this, InternalMemoryStatus.class);
                        startActivity(internalstorage);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                // If network Info is selected, then open the Network Info class
                else if(Slecteditem == "NetworkInfo"){
                    try{Intent networkInfo = new Intent(customdiagnosis.this, NetworkInfo.class);
                        startActivity(networkInfo);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                // if GPS is selected, the show the information about GPS
                else if(Slecteditem == "GPS"){
                    try{ Intent gps = new Intent(customdiagnosis.this, GPSInfo.class);
                        startActivity(gps);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                // if flashlight is selected, then open the flashlight class to allow user to perform actions
                else if(Slecteditem == "Flashlight"){
                    try{Intent flash = new Intent(customdiagnosis.this, FlashlightInfo.class);
                        startActivity(flash);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                // this will run the accelerometer
                else if(Slecteditem == "Accelerometer"){
                    try{ Intent acclerometer = new Intent(customdiagnosis.this, AccelerometerInfo.class);
                        startActivity(acclerometer);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Gyroscope"){
                    try{Intent gyroscope = new Intent(customdiagnosis.this, GyroscopeInfo.class);
                        startActivity(gyroscope);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Gravity Sensor"){
                    try{ Intent gravity = new Intent(customdiagnosis.this, GravitySensor.class);
                        startActivity(gravity);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "ProximitySensor"){
                    try{Intent proxy = new Intent(customdiagnosis.this, ProximitySensor.class);
                        startActivity(proxy);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Magnetometer"){
                    try{ Intent magent = new Intent(customdiagnosis.this, Magnetometer.class);
                        startActivity(magent);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Light Test"){
                    try{Intent light = new Intent(customdiagnosis.this, LightTest.class);
                        startActivity(light);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Sound Test"){
                    try{Intent sound = new Intent(customdiagnosis.this, SoundTest.class);
                        startActivity(sound);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Rotational Vector Sensor"){
                    try{Intent rot = new Intent(customdiagnosis.this, RotationalVectorSensor.class);
                        startActivity(rot);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Orientation Sensor"){
                    try{Intent ori = new Intent(customdiagnosis.this, OrientationSensor.class);
                        startActivity(ori);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

            }
        });


    }

    //this method will be called when the other radio button is selected
    private void other() {
        CustomListAdapter2  adapter1 = new CustomListAdapter2(this, others, other);
        list = (ListView) findViewById(R.id.list2);
        list.setAdapter(adapter1);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = others[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

                //if battery is selected, then open the Battery information
                if(Slecteditem == "Battery"){
                    try{ Intent startdia = new Intent(customdiagnosis.this, BatteryStatus.class);
                        startActivity(startdia);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                //if Backcamera or front camera is selected, then open the camera to diagnos
                else if(Slecteditem == "BackCamera"){
                    try{ Intent camera = new Intent(customdiagnosis.this, CameraStatus.class);
                        startActivity(camera);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "FrontCamera"){
                    try{Intent frontcamera = new Intent(customdiagnosis.this, CameraStatus.class);
                        startActivity(frontcamera);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                //if Internal Storage is selected, then open the INternal Storage information
                else if(Slecteditem == "InternalStorage"){
                    try{   Intent internalstorage = new Intent(customdiagnosis.this, InternalMemoryStatus.class);
                        startActivity(internalstorage);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                // If network Info is selected, then open the Network Info class
                else if(Slecteditem == "NetworkInfo"){
                    try{Intent networkInfo = new Intent(customdiagnosis.this, NetworkInfo.class);
                        startActivity(networkInfo);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                // if GPS is selected, the show the information about GPS
                else if(Slecteditem == "GPS"){
                    try{ Intent gps = new Intent(customdiagnosis.this, GPSInfo.class);
                        startActivity(gps);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                // if flashlight is selected, then open the flashlight class to allow user to perform actions
                else if(Slecteditem == "Flashlight"){
                    try{Intent flash = new Intent(customdiagnosis.this, FlashlightInfo.class);
                        startActivity(flash);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                // this will run the accelerometer
                else if(Slecteditem == "Accelerometer"){
                    try{ Intent acclerometer = new Intent(customdiagnosis.this, AccelerometerInfo.class);
                        startActivity(acclerometer);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Gyroscope"){
                    try{Intent gyroscope = new Intent(customdiagnosis.this, GyroscopeInfo.class);
                        startActivity(gyroscope);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Gravity Sensor"){
                    try{ Intent gravity = new Intent(customdiagnosis.this, GravitySensor.class);
                        startActivity(gravity);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "ProximitySensor"){
                    try{Intent proxy = new Intent(customdiagnosis.this, ProximitySensor.class);
                        startActivity(proxy);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Magnetometer"){
                    try{ Intent magent = new Intent(customdiagnosis.this, Magnetometer.class);
                        startActivity(magent);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Light Test"){
                    try{Intent light = new Intent(customdiagnosis.this, LightTest.class);
                        startActivity(light);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Sound Test"){
                    try{Intent sound = new Intent(customdiagnosis.this, SoundTest.class);
                        startActivity(sound);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Rotational Vector Sensor"){
                    try{Intent rot = new Intent(customdiagnosis.this, RotationalVectorSensor.class);
                        startActivity(rot);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Orientation Sensor"){
                    try{Intent ori = new Intent(customdiagnosis.this, OrientationSensor.class);
                        startActivity(ori);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

            }
        });


    }
    private void defaul() {
        CustomListAdapter2  adapter1 = new CustomListAdapter2(this, itemname, imgid);
        list = (ListView) findViewById(R.id.list2);
        list.setAdapter(adapter1);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = itemname[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

                //if battery is selected, then open the Battery information
                if(Slecteditem == "Battery"){
                    try{ Intent startdia = new Intent(customdiagnosis.this, BatteryStatus.class);
                        startActivity(startdia);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                //if Backcamera or front camera is selected, then open the camera to diagnos
                else if(Slecteditem == "BackCamera"){
                    try{ Intent camera = new Intent(customdiagnosis.this, CameraStatus.class);
                        startActivity(camera);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "FrontCamera"){
                    try{Intent frontcamera = new Intent(customdiagnosis.this, CameraStatus.class);
                        startActivity(frontcamera);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                //if Internal Storage is selected, then open the INternal Storage information
                else if(Slecteditem == "InternalStorage"){
                    try{   Intent internalstorage = new Intent(customdiagnosis.this, InternalMemoryStatus.class);
                        startActivity(internalstorage);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                // If network Info is selected, then open the Network Info class
                else if(Slecteditem == "NetworkInfo"){
                    try{Intent networkInfo = new Intent(customdiagnosis.this, NetworkInfo.class);
                        startActivity(networkInfo);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                // if GPS is selected, the show the information about GPS
                else if(Slecteditem == "GPS"){
                    try{ Intent gps = new Intent(customdiagnosis.this, GPSInfo.class);
                        startActivity(gps);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                // if flashlight is selected, then open the flashlight class to allow user to perform actions
                else if(Slecteditem == "Flashlight"){
                    try{Intent flash = new Intent(customdiagnosis.this, FlashlightInfo.class);
                        startActivity(flash);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                // this will run the accelerometer
                else if(Slecteditem == "Accelerometer"){
                    try{ Intent acclerometer = new Intent(customdiagnosis.this, AccelerometerInfo.class);
                        startActivity(acclerometer);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Gyroscope"){
                    try{Intent gyroscope = new Intent(customdiagnosis.this, GyroscopeInfo.class);
                        startActivity(gyroscope);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Gravity Sensor"){
                    try{ Intent gravity = new Intent(customdiagnosis.this, GravitySensor.class);
                        startActivity(gravity);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "ProximitySensor"){
                    try{Intent proxy = new Intent(customdiagnosis.this, ProximitySensor.class);
                        startActivity(proxy);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Magnetometer"){
                    try{ Intent magent = new Intent(customdiagnosis.this, Magnetometer.class);
                        startActivity(magent);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Light Test"){
                    try{Intent light = new Intent(customdiagnosis.this, LightTest.class);
                        startActivity(light);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Sound Test"){
                    try{Intent sound = new Intent(customdiagnosis.this, SoundTest.class);
                        startActivity(sound);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Rotational Vector Sensor"){
                    try{Intent rot = new Intent(customdiagnosis.this, RotationalVectorSensor.class);
                        startActivity(rot);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

                else if(Slecteditem == "Orientation Sensor"){
                    try{Intent ori = new Intent(customdiagnosis.this, OrientationSensor.class);
                        startActivity(ori);
                        insertData(Slecteditem, "pass");
                    }
                    catch (Exception e){
                        insertData(Slecteditem, "fail");
                    }
                }

            }
        });


    }

    private void insertData(String Slectedite, String ps) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String date = sdf.format(System.currentTimeMillis());
         myDb= new DatabaseHelper(customdiagnosis.this);
        try {
            String time = date.toString();
            boolean isInserted = myDb.insertData(Slectedite, time, ps);
            if (isInserted == true)
                Toast.makeText(customdiagnosis.this, "Data Inserted", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(customdiagnosis.this, "Data not Inserted", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            Toast.makeText(customdiagnosis.this, "Data not Inserted", Toast.LENGTH_LONG).show();

        }
    }

}
