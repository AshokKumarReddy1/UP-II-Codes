package com.example.ambulanceservice_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserMainPageActivity extends AppCompatActivity {

    private Button viewamulancesbtn, viewacceptedbtn, viewrejectedbtn, bookambulancebtn, gobackbtn,viewbookedbtn;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_page);

        viewacceptedbtn = (Button) findViewById(R.id.viewacceptedambulancebtn);
        viewrejectedbtn = (Button) findViewById(R.id.viewrejectedambulancebtn);
        viewamulancesbtn = (Button) findViewById(R.id.viewambulancebtn);
        bookambulancebtn = (Button) findViewById(R.id.searchambulancebtn);
        viewbookedbtn = (Button) findViewById(R.id.viewbookedambulancebtn);
        gobackbtn= (Button) findViewById(R.id.gobackbtn);

        Intent intent = getIntent();
        userId= intent.getStringExtra("userId");


        gobackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });

        viewacceptedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserViewAcceptedActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });

        viewbookedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserViewBookedActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });

        viewrejectedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserViewRejectedActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });

        viewamulancesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserViewAmbulancesActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });

        bookambulancebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserSearchAmbulancesActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });
    }
}