package com.example.diagnostictool;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import android.view.View;

import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * <p>
 * Title: WindowActivity.java
 * </p>
 *
 * <p>
 * Description: This is the class which opens after the login page and displays the option to either diagnos our phone, locate us and feedback.
 * </p>

 * @author Puneet Garg
 * @version 3.00
 *
 */
public class WindowActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView h1;
    String s2;
    String s1;
    ImageView m1;
    CardView viewData;

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        Bundle b1 = getIntent().getExtras();

         s1 = b1.getString("user");
        s2 = b1.getString("email");

        //DrawerLayout drawer2 = findViewById(R.id.drawer_layout);
        //if (drawer2.isDrawerOpen(GravityCompat.START)) {

        TextView windowname = findViewById(R.id.window_name);
        windowname.setText(s1);

        m1 = findViewById(R.id.imageView10);
        viewData = findViewById(R.id.c32);

        m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ImageView m2 = findViewById(R.id.imageView15);
        m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent help = new Intent(WindowActivity.this, feedback.class);
                startActivity(help);
            }
        });
        CardView c1 = findViewById(R.id.c1);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view ) {
                Intent c1intent = new Intent(WindowActivity.this, customdiagnosis.class);
                startActivity(c1intent);
            }
        });

        CardView c2 = findViewById(R.id.c2);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c2intent = new Intent(WindowActivity.this, AboutPhoneActivity.class);
                startActivity(c2intent);
            }
        });

        CardView c31 = findViewById(R.id.c31);
        c31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent locate = new Intent(WindowActivity.this, ContactUs.class);
                startActivity(locate);
            }
        });

        myDb = new DatabaseHelper(WindowActivity.this);
        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent help = new Intent(WindowActivity.this, Resultscreen.class);
                startActivity(help);
            }
        });
        //         h1 = findViewById(R.id.textView4);
//         String str = Build.MODEL;
//        h1.setText(str);
//        }

    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.window, menu);
        TextView h3 = findViewById(R.id.header1);
        TextView h4 = findViewById(R.id.textVview);
        h4.setText(s2);
        h3.setText(s1);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.diagnosPhone) {
            Intent choose = new Intent(WindowActivity.this, customdiagnosis.class);
            startActivity(choose);
        } else if (id == R.id.aboutPhone) {
            Intent aboutphone = new Intent(WindowActivity.this, AboutPhoneActivity.class);
            startActivity(aboutphone);
        }
        else if(id == R.id.contact){
            Intent contactus = new Intent(WindowActivity.this, ContactUs.class);
            startActivity(contactus);
        }
        else if(id == R.id.feed){
            Intent feedback = new Intent(WindowActivity.this, feedback.class);
            startActivity(feedback);
        }
        else if (id == R.id.logout){
            finish();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
