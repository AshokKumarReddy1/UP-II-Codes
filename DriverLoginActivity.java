package com.example.ambulanceservice_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
//import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.security.SecureRandom;
import java.util.List;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import android.os.Bundle;
import android.text.InputType;

import android.content.Context;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.nio.charset.StandardCharsets;
import java.security.*;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class DriverLoginActivity extends AppCompatActivity {

    private Button signInBtn, goBackBtn;
    private EditText txtUname, txtPwd;
    private String driverId, username, password;
    private boolean flag;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        signInBtn = (Button) findViewById(R.id.loginBtn);
        goBackBtn = (Button) findViewById(R.id.gobackBtn);
        txtUname = (EditText) findViewById(R.id.editTextUname);
        txtPwd = (EditText) findViewById(R.id.editTextPassword);

        //txtUname.setText("kiran");
        //txtPwd.setText("1234");

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = txtUname.getText().toString();
                password = txtPwd.getText().toString();
                Log.d("User Name : ", username + " Password : "+password);
                db = FirebaseFirestore.getInstance();
                if (TextUtils.isEmpty(username)) {
                    txtUname.setError("User Name is Empty");
                    txtUname.setFocusable(true);
                } else if (TextUtils.isEmpty(password)) {
                    txtPwd.setError("Password is Empty");
                    txtPwd.setFocusable(true);
                } else {
                    // Adding addValueEventListener method on firebase object.
                    username = txtUname.getText().toString();
                    password = txtPwd.getText().toString();
                    /*FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("NewUser");

                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            flag=true;
                            for (DataSnapshot SubSnapshot : snapshot.getChildren()) {
                                NewUserClass userClass = SubSnapshot.getValue(NewUserClass.class);
                                if(userClass.getUserName().equals(username)
                                        && userClass.getPassword().equals(password))
                                {
                                    userId = userClass.getUserId();
                                    flag=true;
                                    break;
                                }
                            }
                            if(flag)
                            {
                                Intent intent = new Intent(getApplicationContext(), UserMainPageActivity.class);
                                intent.putExtra("userid", userId);
                                startActivity(intent);
                            }else
                            {
                                Toast.makeText(getApplicationContext(),"Invalid UserName/Password", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            System.out.println("Data Access Failed" + error.getMessage());
                            Toast.makeText(getApplicationContext(),"Data Access Failed", Toast.LENGTH_SHORT).show();
                        }
                    });*/

                    db.collection("NewDriver").get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @RequiresApi(api = Build.VERSION_CODES.O)
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
                                        String userId = "";
                                        for (DocumentSnapshot d : list) {
                                            // after getting this list we are passing
                                            // that list to our object class.
                                            NewDriverClass userClass = d.toObject(NewDriverClass.class);
                                            if (userClass.getUserName().equals(username) && password.equals(userClass.getPassword())) {
                                                userId = userClass.getId();
                                                Log.d("UserId ", userId);
                                                flag = true;
                                                break;
                                            }
                                        }
                                        if (flag) {
                                            Toast.makeText(getApplicationContext(), "User Login Success", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getApplicationContext(), DriverMainPageActivity.class);
                                            intent.putExtra("userId", userId);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Invalid UserName/Password", Toast.LENGTH_SHORT).show();
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
        });

        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}