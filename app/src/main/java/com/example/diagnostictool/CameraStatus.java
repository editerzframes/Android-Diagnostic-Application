package com.example.diagnostictool;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Bundle;
import android.widget.TextView;

/**
 * <p>
 * Title: CameraStatus.java
 * </p>
 *
 * <p>
 * Description: This is the class which allows us to capture a image using camera and status the condition of camera
 * </p>

 * @author Puneet Garg
 * @version 3.00
 *
 */
public class CameraStatus extends AppCompatActivity {

    // Define the pic id
    private static final int pic_id = 123;

    // Define the button and imageview type variable
    Button camera_open_id;
    ImageView click_image_id;
TextView tcm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_status);

// By ID we can get each component
// which id is assigned in XML file
// get Buttons and imageview.
        camera_open_id = (Button)findViewById(R.id.camera_button);
                click_image_id = (ImageView)findViewById(R.id.click_image);
tcm = findViewById(R.id.camStat);
                // Camera_open button is for open the camera
                // and add the setOnClickListener in this button
                camera_open_id.setOnClickListener(new View.OnClickListener() {

@Override
public void onClick(View v)
        {

        // Create the camera_intent ACTION_IMAGE_CAPTURE
        // it will open the camera for capture the image
        Intent camera_intent
        = new Intent(MediaStore
        .ACTION_IMAGE_CAPTURE);

        // Start the activity with camera_intent,
        // and request pic id
        startActivityForResult(camera_intent, pic_id);
        }
        });
        }

// This method will help to retrieve the image
protected void onActivityResult(int requestCode,
        int resultCode,
        Intent data)
        {

        // Match the request 'pic id with requestCode
        if (requestCode == pic_id) {

        // BitMap is data structure of image file
        // which stor the image in memory
        Bitmap photo = (Bitmap)data.getExtras()
        .get("data");

        // Set the image in imageview for display
        click_image_id.setImageBitmap(photo);
        tcm.setText("Good");
        tcm.setTextColor(Color.GREEN);
        }
        }
        }