package com.example.ambulanceservice_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class DriverRejectActivity1 extends AppCompatActivity {
    private String areaname, bookingid;
    private Button rejectAmbulancebtn, gobackbtn;
    private FirebaseFirestore db;
    private Boolean flag;
    private String ambulanceName;
    private String vehicleNum;
    private String seatedFor;
    private String price_km;
    private String hospitalName;
    private TextView txtambulancename, txtvehiclenum, txtseatedfor, txtprice, txthospitalname,txtareaname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_reject1);
        Intent intent = getIntent();
        bookingid = intent.getStringExtra("bookingid");

        txtambulancename = (TextView)findViewById(R.id.txtambulanceName);
        txtareaname = (TextView)findViewById(R.id.txtareaname);
        txtseatedfor = (TextView)findViewById(R.id.txtseatedFor);
        txtprice = (TextView)findViewById(R.id.txtprice);
        txthospitalname = (TextView)findViewById(R.id.txthospitalName);
        txtvehiclenum = (TextView)findViewById(R.id.txtvehicleNum);
        gobackbtn= (Button) findViewById(R.id.gobackbtn);
        rejectAmbulancebtn= (Button) findViewById(R.id.rejectAmbulanceBtn);


        gobackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DriverMainPageActivity.class);
                startActivity(intent);
            }
        });

        rejectAmbulancebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("AmbulanceBooking")
                        .document(bookingid)
                        .update("status","Rejected")
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "Ambulance Request Rejected Success", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Ambulance Request Rejected Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        db = FirebaseFirestore.getInstance();
        flag=Boolean.FALSE;
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
                            for (DocumentSnapshot d : list) {
                                // after getting this list we are passing
                                // that list to our object class.
                                BookedAmbulanceClass newClass = d.toObject(BookedAmbulanceClass.class);
                                String ShowDataString = "";
                                if (bookingid.equals(d.getId())) {
                                    flag=Boolean.TRUE;
                                    ambulanceName=newClass.getAmbulanceName();
                                    seatedFor=newClass.getSaetedFor();
                                    price_km=newClass.getPrice_km();
                                    hospitalName=newClass.getHospitalName();
                                    areaname=newClass.getAreaName();
                                    vehicleNum=newClass.getVehicleNum();
                                    txtambulancename.setText("Ambulance Name : "+newClass.getAmbulanceName());
                                    txtseatedfor.setText("Seated For : "+newClass.getSaetedFor());
                                    txtprice.setText("Price_Km : "+newClass.getPrice_km());
                                    txthospitalname.setText("Hospital Name : "+newClass.getHospitalName());
                                    txtareaname.setText("Area Name : "+newClass.getAreaName());
                                    txtvehiclenum.setText("Vehicle Num : "+newClass.getVehicleNum());
                                    break;
                                }
                            }
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
}