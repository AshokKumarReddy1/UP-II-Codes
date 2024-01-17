package com.example.ambulanceservice_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import android.os.Bundle;

import androidx.annotation.NonNull;

import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class UserSearchAmbulancesActivity extends AppCompatActivity {
    private Button viewBtn;
    private ListView list_view;
    private ArrayList<String> staffarray;
    private String[] array, areanames, ambulanceids;
    private String areaname, ambulanceid,userId;
    private FirebaseFirestore db;
    private Spinner areanamespinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search_ambulances);
        list_view = (ListView) findViewById(R.id.list_view);
        viewBtn = (Button) findViewById(R.id.viewbtn);
        areanames=getResources().getStringArray(R.array.areanames);
        areanamespinner = (Spinner)findViewById(R.id.areanamespinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, areanames);
        areanamespinner.setAdapter(adapter);

        Intent intent = getIntent();
        userId= intent.getStringExtra("userId");

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), UserBookAmbulanceActivity.class);
                String ambulance_id = ambulanceids[i];
                intent.putExtra("ambulanceid", ambulance_id);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

        db = FirebaseFirestore.getInstance();
        areaname="";
        ambulanceid="";
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                areaname = areanamespinner.getSelectedItem().toString();
                if (TextUtils.isEmpty(areaname)) {
                    Toast.makeText(getApplicationContext(), "No area selected", Toast.LENGTH_SHORT).show();
                }
else{
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
                                    String str = "";
                                    for (DocumentSnapshot d : list) {
                                        // after getting this list we are passing
                                        // that list to our object class.
                                        NewAmbulanceClass newClass = d.toObject(NewAmbulanceClass.class);
                                        String ShowDataString = "";
                                        if (areaname.equals(newClass.getAreaname())) {
                                            ShowDataString = "Ambulance Name : " + newClass.getAmbulanceName() +
                                                    "\nHospital Name  : " + newClass.getHospitalName() +
                                                    "\nArea Name  : " + newClass.getAreaname() +
                                                    "\nPrice/Km : " + newClass.getPrice_km() +
                                                    "\nSeated For    : " + newClass.getSaetedFor();
                                            if (str.length() == 0) {
                                                str = str + ShowDataString;
                                                ambulanceid = newClass.getId();
                                            }
                                            else {
                                                str = str + "," + ShowDataString;
                                                ambulanceid +=","+  newClass.getId();
                                            }
                                        }
                                    }
                                    array = str.split(",");
                                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                                            android.R.layout.simple_list_item_1, android.R.id.text1, array);
                                    list_view.setAdapter(adapter);
                                    ambulanceids = ambulanceid.split(",");
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
        });
    }
}