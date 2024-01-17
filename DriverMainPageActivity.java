package com.example.ambulanceservice_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DriverMainPageActivity extends AppCompatActivity {

    private Button viewbookedbtn, acceptbtn, rejectbtn, gobackbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main_page);
        gobackbtn=(Button)findViewById(R.id.gobackbtn) ;
        viewbookedbtn=(Button)findViewById(R.id.viewbookedbtn) ;
        acceptbtn=(Button)findViewById(R.id.driveracceptbtn) ;
        rejectbtn=(Button)findViewById(R.id.driverrejectbtn) ;

        gobackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        viewbookedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DriverViewBookedActivity.class);
                startActivity(intent);
            }
        });

        acceptbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DriverAcceptActivity.class);
                startActivity(intent);
            }
        });

        rejectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DriverRejectActivity.class);
                startActivity(intent);
            }
        });

    }
}