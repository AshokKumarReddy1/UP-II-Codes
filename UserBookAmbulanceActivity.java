package com.example.ambulanceservice_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserBookAmbulanceActivity extends AppCompatActivity {
    private String areaname, ambulanceid,userId,bookingId;
    private Button bookambulancebtn, gobackbtn;
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
        setContentView(R.layout.activity_user_book_ambulance);
        Intent intent = getIntent();
        ambulanceid = intent.getStringExtra("ambulanceid");
        userId = intent.getStringExtra("userId");

        txtambulancename = (TextView)findViewById(R.id.txtambulanceName);
        txtareaname = (TextView)findViewById(R.id.txtareaname);
        txtseatedfor = (TextView)findViewById(R.id.txtseatedFor);
        txtprice = (TextView)findViewById(R.id.txtprice);
        txthospitalname = (TextView)findViewById(R.id.txthospitalName);
        txtvehiclenum = (TextView)findViewById(R.id.txtvehicleNum);
        gobackbtn= (Button) findViewById(R.id.gobackbtn);
        bookambulancebtn= (Button) findViewById(R.id.bookAmbulanceBtn);


        gobackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserMainPageActivity.class);
                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });


        bookambulancebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*ambulanceName = txtambulancename.getText().toString();
                vehicleNum = txtvehiclenum.getText().toString();
                seatedFor = txtseatedfor.getText().toString();
                price_km=txtprice.getText().toString();
                hospitalName=txthospitalname.getText().toString();
                areaname = txtareaname.getText().toString();*/
                UUID uuid=UUID.randomUUID(); //Generates random UUID
                bookingId = uuid.toString();
                CollectionReference dbCourses = db.collection("AmbulanceBooking");
                // adding our data to our courses object class.
                BookedAmbulanceClass newClass =new BookedAmbulanceClass(bookingId,  ambulanceName,  vehicleNum,  seatedFor,  price_km,  hospitalName,  "Booked", userId, ambulanceid, areaname);
                // below method is use to add data to Firebase Firestore.
                dbCourses.add(newClass).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // after the data addition is successful
                        // we are displaying a success toast message.
                        Toast.makeText(getApplicationContext(), "Ambulance booked success", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // this method is called when the data addition process is failed.
                        // displaying a toast message when data addition is failed.
                        Toast.makeText(getApplicationContext(), "Ambulance booking Failed\n" + e, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        db = FirebaseFirestore.getInstance();
        flag=Boolean.FALSE;
        db.collection("NewAmbulance").get()
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
                                NewAmbulanceClass newClass = d.toObject(NewAmbulanceClass.class);
                                String ShowDataString = "";
                                if (ambulanceid.equals(newClass.getId())) {
                                 flag=Boolean.TRUE;
                                 ambulanceName=newClass.getAmbulanceName();
                                    seatedFor=newClass.getSaetedFor();
                                    price_km=newClass.getPrice_km();
                                    hospitalName=newClass.getHospitalName();
                                    areaname=newClass.getAreaname();
                                    vehicleNum=newClass.getVehicleNum();
                                 txtambulancename.setText("Ambulance Name : "+newClass.getAmbulanceName());
                                    txtseatedfor.setText("Seated For : "+newClass.getSaetedFor());
                                    txtprice.setText("Price_Km : "+newClass.getPrice_km());
                                    txthospitalname.setText("Hospital Name : "+newClass.getHospitalName());
                                    txtareaname.setText("Area Name : "+newClass.getAreaname());
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