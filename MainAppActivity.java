package com.example.ambulanceservice_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;

public class MainAppActivity extends AppCompatActivity {
    private Button adminLoginBtn, driverLoginBtn, userLoginBtn, newUserBtn, newDriverBtn, gobackbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
        adminLoginBtn = (Button) findViewById(R.id.adminsignin);
        driverLoginBtn = (Button) findViewById(R.id.driversignin);
        userLoginBtn = (Button) findViewById(R.id.usersignin);
        //newDriverBtn = (Button) findViewById(R.id.newdriverbtn);
        newUserBtn = (Button) findViewById(R.id.newuserbtn);
        gobackbtn = (Button) findViewById(R.id.gobackbtn);


        newUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewUserActivity.class);
                startActivity(intent);
            }
        });

        adminLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminLoginActivity.class);
                startActivity(intent);
            }
        });

        userLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserLoginActivity.class);
                startActivity(intent);
            }
        });

        driverLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DriverLoginActivity.class);
                startActivity(intent);
            }
        });

        newUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewUserActivity.class);
                startActivity(intent);
            }
        });

        /*
        newDriverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewDriverActivity.class);
                startActivity(intent);
            }
        });
        */

        gobackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                // on below line we are finishing activity.
                //MainAppActivity.this.finish();
                // on below line we are exiting our activity
                //System.exit(0);
                startActivity(intent);
            }
        });

    }
}