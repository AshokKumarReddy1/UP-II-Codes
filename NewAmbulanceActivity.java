package com.example.ambulanceservice_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.io.InputStream;
import android.os.Bundle;

public class NewAmbulanceActivity extends AppCompatActivity {
    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "StoreImageActivity";
    private Uri selectedImageUri;
    private DBHelper dbHelper;
    private String imageType;
    private ImageView ambulanceImage;
    private String imageId;


    // this function is triggered when
    // the Select Image Button is clicked
    void imageChooser() {
        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                selectedImageUri = data.getData();
                Log.d("Path : ", selectedImageUri.getPath());
                if (null != selectedImageUri) {
                    ambulanceImage.setImageURI(selectedImageUri);
                }
            }
        }
    }

    boolean saveImageInDB() {
        try {
            dbHelper.open();
            InputStream iStream = getContentResolver().openInputStream(selectedImageUri);
            byte[] inputData = Utils.getBytes(iStream);
            Log.d("ImageId ", imageId);
            Log.d("ImageType ", imageType);
            dbHelper.insertImage(imageId, imageType, inputData);
            dbHelper.close();
            return true;
        } catch (IOException ioe) {
            Log.e(TAG, "<saveImageInDB> Error : " + ioe.getLocalizedMessage());
            dbHelper.close();
            return false;
        }
    }

    private EditText txtambulanceName,txtvehicleNum, txtprice_km, txthospitalname,
            txtseatedfor;
    private String ambulanceName, vehicleNum, price_km, hospitalName, seatedfor, areaname;
    private Button newambulancebtn, gobackbtn, ambulancepicbtn;
    private String[] areanames;
    private Spinner areanamespinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_ambulance);

        txtambulanceName = (EditText) findViewById(R.id.editTextAname);
        txtvehicleNum = (EditText) findViewById(R.id.editTextVnum);
        txtprice_km = (EditText) findViewById(R.id.editTextPriceKm);
        txthospitalname = (EditText) findViewById(R.id.editTextHname);
        txtseatedfor = (EditText) findViewById(R.id.editTextSeatFor);
        ambulanceImage = (ImageView) findViewById(R.id.ambulancePicImage);
        ambulancepicbtn = (Button)findViewById(R.id.uploadambulancepicbutton);
        gobackbtn= (Button)findViewById(R.id.gobackbtn);
        newambulancebtn= (Button)findViewById(R.id.newambulancebtn);
        areanames=getResources().getStringArray(R.array.areanames);
        areanamespinner = (Spinner)findViewById(R.id.areanamespinner);
        UUID uuid=UUID.randomUUID(); //Generates random UUID
        imageId = uuid.toString();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, areanames);
        areanamespinner.setAdapter(adapter);

        // Create the Database helper object
        dbHelper = new DBHelper(this);
        ambulanceImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageType="Ambulance";
                imageChooser();
            }
        });

        ambulancepicbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(saveImageInDB())
                    Toast.makeText(getApplicationContext(), "Ambulance Pic Image Saved", Toast.LENGTH_SHORT).show();
            }
        });

        gobackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminMainActivity.class);
                startActivity(intent);
            }
        });

        newambulancebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ambulanceName = txtambulanceName.getText().toString();
                vehicleNum = txtvehicleNum.getText().toString();
                price_km = txtprice_km.getText().toString();
                hospitalName = txthospitalname.getText().toString();
                seatedfor = txtseatedfor.getText().toString();
                areaname = areanamespinner.getSelectedItem().toString();

                if (TextUtils.isEmpty(ambulanceName)) {
                    Toast.makeText(getApplicationContext(), "Ambulance Name is Empty", Toast.LENGTH_SHORT).show();
                    txtambulanceName.setFocusable(true);
                } else if (TextUtils.isEmpty(vehicleNum)) {
                    Toast.makeText(getApplicationContext(), "VehicleNum is Empty", Toast.LENGTH_SHORT).show();
                    txtvehicleNum.setFocusable(true);
                } else if (TextUtils.isEmpty(price_km)) {
                    Toast.makeText(getApplicationContext(), "Price/Km is Empty", Toast.LENGTH_SHORT).show();
                    txtprice_km.setFocusable(true);
                } else if (TextUtils.isEmpty(hospitalName)) {
                    Toast.makeText(getApplicationContext(), "Hospital Name is Empty", Toast.LENGTH_SHORT).show();
                    txthospitalname.setFocusable(true);
                } else if (TextUtils.isEmpty(seatedfor)) {
                    Toast.makeText(getApplicationContext(), "Seated For is Empty", Toast.LENGTH_SHORT).show();
                    txtseatedfor.setFocusable(true);
                }else if (TextUtils.isEmpty(areaname)) {
                    Toast.makeText(getApplicationContext(), "Area Name is Empty", Toast.LENGTH_SHORT).show();
                    areanamespinner.setFocusable(true);
                }else {



                    /*FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference dbRef = database.getReference();
                    dbRef = database.getReference("NewDriver");
                    Id = dbRef.push().getKey();
                    NewDriverClass newClass = new NewDriverClass(Id, firstName, lastName, gender,
                            emailId, phoneNum, collegeName, collegeId, userName, password, branch, aggregate, imageId);
                    dbRef.child(Id).setValue(newClass);
                    Toast.makeText(getApplicationContext(), "New Driver Inserted Successfully",
                            Toast.LENGTH_LONG).show();
                     */

                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    NewAmbulanceClass newClass =new NewAmbulanceClass(imageId,  ambulanceName,  vehicleNum,  seatedfor,  price_km,  hospitalName,  imageId, areaname);
                    // Add a new document with a generated ID
                    db.collection("NewAmbulance")
                            .add(newClass)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d("Success : ", "DocumentSnapshot added with ID: " + documentReference.getId());
                                    Toast.makeText(getApplicationContext(), "Ambulance has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w("Error : ", "Error adding document", e);
                                    Toast.makeText(getApplicationContext(), "Fail to add Ambulance \n" + e, Toast.LENGTH_SHORT).show();
                                }
                    });
                }
            }
        });
    }
}