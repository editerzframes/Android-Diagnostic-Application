package com.example.diagnostictool;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;


/**
 * <p>
 * Title: AboutPhoneActivity.java
 * </p>
 *
 * <p>
 * Description: This is the class which help us to get the device information like network operator, ram, etc.
 * </p>

 * @author Puneet Garg
 * @version 3.00	updated device information class showing all the required info of phone
 *
 */
public class AboutPhoneActivity extends AppCompatActivity {

    TextView t11;
    TextView t12;
    TextView t13;
    TextView t14;
    TextView t15;
    TextView t16;
    TextView t17;
    TextView t18;
    TextView t19;
    TextView t20;
    TextView t21;

    String IMEINumber;
    private static final int REQUEST_CODE = 101;
    long totalMemory;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_phone);

        t11 = findViewById(R.id.t11about);
        t12 = findViewById(R.id.t12about);
        t13 = findViewById(R.id.t13about);
        t14 = findViewById(R.id.t14about);
        t15 = findViewById(R.id.t15about);
        t16 = findViewById(R.id.t16about);
        t17 = findViewById(R.id.t17about);
        t18 = findViewById(R.id.t18about);
        t19 = findViewById(R.id.t19about);
        t20 = findViewById(R.id.t20about);
        t21 = findViewById(R.id.t21about);


        ActivityManager actManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();

        actManager.getMemoryInfo(memInfo);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            totalMemory = memInfo.totalMem / (1024 * 1024);
        }

        String t1text = android.os.Build.MODEL;
        String t3text = Build.BRAND;
        String t4text = Build.HARDWARE;
        String AndroidVersion = android.os.Build.VERSION.RELEASE;
        long totalInternalValue = getTotalInternalMemorySize();
        totalInternalValue = (totalInternalValue / 1073741824);


        Context context = getApplicationContext(); // or activity.getApplicationContext()
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();

        String myVersionName = "not available"; // initialize String

        try {
            myVersionName = packageManager.getPackageInfo(packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        TelephonyManager tel = (TelephonyManager) getBaseContext()
                .getSystemService(Context.TELEPHONY_SERVICE);

        try {
            for (Enumeration en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = (NetworkInterface) en.nextElement();
                for (Enumeration enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        t20.setText(inetAddress.getHostAddress());
                    }
                }
            }
        } catch (Exception e) {
            Log.e("------------", e.toString());
        }

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE);
            return;
        }
        IMEINumber = telephonyManager.getDeviceId();
        t19.setText(IMEINumber);


        t11.setText(t1text);
        t12.setText(AndroidVersion);
        t13.setText(t3text);
        t14.setText(t4text);
        t15.setText(Long.toString(totalMemory) + " " + "MB");
        t16.setText(Long.toString(totalInternalValue) + " " + "GB");
        t17.setText(myVersionName);
        t18.setText(tel.getNetworkOperatorName().toString());
        t21.setText(getMacAddr());
    }

    public static long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return (totalBlocks * blockSize);
    }
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

    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif: all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b: macBytes) {
                    //res1.append(Integer.toHexString(b & 0xFF) + ":");
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {}
        return "02:00:00:00:00:00";
    }

}
