package com.example.ambulanceservice_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminMainActivity extends AppCompatActivity {
    private Button newdriverbtn, newambulancebtn, viewdriversbtn, viewambulancebtn, viewusersbtn, gobackbtn, viewacceptedambulancebtn,
    viewrejectedambulancebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        newdriverbtn=(Button)findViewById(R.id.newdriverbtn);
        newambulancebtn = (Button) findViewById(R.id.newambulancebtn);
        viewambulancebtn = (Button) findViewById(R.id.viewambulancebtn);
        viewacceptedambulancebtn = (Button) findViewById(R.id.adminviewambulanceacceptedbtn);
        viewrejectedambulancebtn = (Button) findViewById(R.id.adminviewambulancerejectedbtn);
        viewdriversbtn = (Button) findViewById(R.id.viewdriverbtn);
        viewusersbtn = (Button) findViewById(R.id.viewuserbtn);
        gobackbtn = (Button) findViewById(R.id.gobackbtn);

        newdriverbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewDriverActivity.class);
                startActivity(intent);
            }
        });

        viewacceptedambulancebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminViewAcceptedActivity.class);
                startActivity(intent);
            }
        });

        viewrejectedambulancebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminViewRejectedActivity.class);
                startActivity(intent);
            }
        });

        newambulancebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NewAmbulanceActivity.class);
                startActivity(intent);
            }
        });

        viewusersbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminViewUsersActivity.class);
                startActivity(intent);
            }
        });

        viewambulancebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminViewAmbulancesActivity.class);
                startActivity(intent);
            }
        });

        viewdriversbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminViewDriversActivity.class);
                startActivity(intent);
            }
        });

        gobackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}