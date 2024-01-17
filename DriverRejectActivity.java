package com.example.ambulanceservice_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DriverRejectActivity extends AppCompatActivity {
    private Button viewBtn;
    private ListView list_view;
    private ArrayList<String> staffarray;
    private String[] array, booked_ids;
    private String bookedid;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_reject);
        list_view = (ListView) findViewById(R.id.list_view);
        viewBtn = (Button) findViewById(R.id.viewbtn);
        db = FirebaseFirestore.getInstance();

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), DriverRejectActivity1.class);
                String booked_id = booked_ids[i];
                intent.putExtra("bookingid", booked_id);
                startActivity(intent);
            }
        });

        bookedid = "";
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("AmbulanceBooking").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                // after getting the data we are calling on success method
                                // and inside this method we are checking if the received
                                // query snapshot is empty or not.
                                if (!queryDocumentSnapshots.isEmpty()) {
                                    // if the snapshot is not empty we are
                                    // hiding our progress bar and adding
                                    // our data in a list.
                                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                    String str = "";
                                    for (DocumentSnapshot d : list) {
                                        // after getting this list we are passing
                                        // that list to our object class.
                                        String id = d.getId();
                                        BookedAmbulanceClass newClass = d.toObject(BookedAmbulanceClass.class);
                                        String ShowDataString = "";
                                        if(newClass.getStatus().equals("Booked")) {
                                            ShowDataString = "Ambulance Name : " + newClass.getAmbulanceName() +
                                                    "\nHospital Name  : " + newClass.getHospitalName() +
                                                    "\nPrice/Km : " + newClass.getPrice_km() +
                                                    "\nSeated For    : " + newClass.getSaetedFor() +
                                                    "\nBooked Date/Km : " + newClass.getPrice_km() +
                                                    "\nStatus/Km : " + newClass.getPrice_km();
                                            if (str.length() == 0) {
                                                str = str + ShowDataString;
                                                bookedid = id;
                                            }
                                            else {
                                                str = str + "," + ShowDataString;
                                                bookedid +=","+ id;
                                            }
                                        }
                                    }
                                    array = str.split(",");
                                    booked_ids = bookedid.split(",");
                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                                            android.R.layout.simple_list_item_1, android.R.id.text1, array);
                                    list_view.setAdapter(adapter);
                                } else {
                                    // if the snapshot is empty we are displaying a toast message.
                                    Toast.makeText(getApplicationContext(), "No data found in Database", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // if we do not get any data or any error we are displaying
                                // a toast message that we do not get any data
                                Toast.makeText(getApplicationContext(), "Fail to get the data.", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}