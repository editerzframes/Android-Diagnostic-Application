package com.example.diagnostictool;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;

/**
 * <p>
 * Title: InternalMemoryStatus.java
 * </p>
 *
 * <p>
 * Description: This is the class which fetch the internal storage information of every android phone and displays it
 * </p>

 * @author Puneet Garg
 * @version 3.00
 *
 */
public class InternalMemoryStatus extends AppCompatActivity {

    TextView t566;
    TextView t567;
    TextView t568;
    TextView t569;

    ProgressBar improgress;
    long totalInternalValue;
    long freeInternalValue;
    long totalMemory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_memory_status);

        t566 = findViewById(R.id.im);
        t568 = findViewById(R.id.imfree);
        t567 = findViewById(R.id.imused);
        t569 = findViewById(R.id.ramavailable);

        improgress = findViewById(R.id.improgress);

         totalInternalValue = getTotalInternalMemorySize();
         freeInternalValue = getAvailableInternalMemorySize();
        long usedInternalValue = totalInternalValue - freeInternalValue;

        totalInternalValue = (totalInternalValue/(1024*1024));
        freeInternalValue = freeInternalValue/ (1024*1024);
        usedInternalValue = usedInternalValue/(1024*1024);

        t566.setText(Long.toString(totalInternalValue) + " " + "MB");

        t567.setText(Long.toString(usedInternalValue) + " " + "MB");

        t568.setText(Long.toString(freeInternalValue)+" "+ "MB");
        setSignalLevel(freeInternalValue, totalInternalValue);

        ActivityManager actManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();

        actManager.getMemoryInfo(memInfo);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            totalMemory = memInfo.totalMem / (1024 * 1024);
        }

        t569.setText(Long.toString(totalMemory) + " " + "MB");

    }

    // method to get the memory available in your android phone
    public static long getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    // method to get the total memory size of your android phone
    public static long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }

    //method to display the memory usage in the form of progress bar
    private void setSignalLevel(long free, long total){

        int progress = (int) ((((float)free)/total) * 100);

        improgress.setProgress((int)progress);

        Log.i("signalLevel ","" + progress);
    }
}
