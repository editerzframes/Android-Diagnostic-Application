package com.example.diagnostictool;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <p>
 * Title: ResultScreen.java
 * </p>
 *
 * <p>
 * Description: This is the class which shows the result after the diagnosis activity in a proper manner to the user.
 * </p>

 * @author Puneet Garg
 * @version 3.00
 *
 */
public class Resultscreen extends AppCompatActivity {

    // Define the variable of CalendarView type
    // and TextView type;
    CalendarView calender;
    TextView date_view;
    Button viewFinal;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultscreen);

        final Button feedsend = findViewById(R.id.button);
        viewFinal = findViewById(R.id.viewFinalResults);

        myDb = new DatabaseHelper(Resultscreen.this);

        viewFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = date_view.getText().toString();
                if (message.equals("Set the Date")) {
                    showMessage("Message", "Please Select a date first");

                } else {
                    Cursor res = myDb.getAllData(message);
                    if (res.getCount() == 0) {
                        // show message
                        showMessage("Error", "Nothing found");
                        return;
                    }

                    StringBuffer buffer = new StringBuffer();
                    String mo=null;
                    while (res.moveToNext()) {
                        buffer.append("Id :" + res.getString(0) + "\n");
                        buffer.append("Name :" + res.getString(1) + "\n");
                        buffer.append("Time :" + res.getString(2) + "\n");
                        buffer.append("Status :" + res.getString(3) + "\n");
                       mo = buffer + "\n";
                    }

                    // Show all data
                    showMessage("Data", buffer.toString());
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        createPdf(mo);
                    }
                }
            }
        });


        feedsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent send = new Intent(Resultscreen.this, feedback.class);
                startActivity(send);
            }
        });

        calender = (CalendarView)
                findViewById(R.id.calender);
        date_view = (TextView)
                findViewById(R.id.date_view);
        // Add Listener in calendar
        calender
                .setOnDateChangeListener(
                        new CalendarView
                                .OnDateChangeListener() {
                            @Override

                            // In this Listener have one method
                            // and in this method we will
                            // get the value of DAYS, MONTH, YEARS
                            public void onSelectedDayChange(
                                    @NonNull CalendarView view,
                                    int year,
                                    int month,
                                    int dayOfMonth) {

                                // Store the value of date with
                                // format in String type Variable
                                // Add 1 in month because month
                                // index is start with 0
                                String Date
                                        = dayOfMonth + "-"
                                        + (month + 1) + "-" + year;

                                // set this date in TextView for Display
                                date_view.setText(Date);
                                Toast.makeText(Resultscreen.this, Date, Toast.LENGTH_LONG).show();

                            }
                        });


    }

    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void createPdf(String sometext) {
        // create a new document
        PdfDocument document = new PdfDocument();
        // crate a page description
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
        // start a page
        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();

        canvas.drawText(sometext, 5, 50, paint);

        document.finishPage(page);

        // write the document content
        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/mypdf/";
        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String targetPdf = directory_path + "test-2.pdf";
        File filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(this, "Done", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Log.e("main", "error " + e.toString());
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }
        // close the document
        document.close();

    }

}

