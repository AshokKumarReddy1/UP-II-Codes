package com.example.ambulanceservice_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class AdminViewFullDetailsActivity extends AppCompatActivity {
    private ImageView profilePicImage, aadharPicImage, drivinglicencePicImage, panCardImage;
    private Button profilePicBtn, aadharPicBtn, panCardPicBtn, drivingLicenceBtn;
    private String imageId, imageType;
    private DBHelper dbHelper;
    private static final String TAG = "StoreImageActivity";

    public void loadImageFromDB() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dbHelper.open();
                    Log.d("Image Type : ",imageType);
                    Log.d("Image Id   : ",imageId);
                    final byte[] bytes = dbHelper.retreiveImageFromDB(imageId, imageType);
                    dbHelper.close();
                    // Show Image from DB in ImageView
                    if(imageType.equals("ProfilePic")) {
                        profilePicImage.post(new Runnable() {
                            @Override
                            public void run() {
                                profilePicImage.setImageBitmap(Utils.getImage(bytes));
                                //showMessage("Image Loaded from Database");
                                Toast.makeText(getApplicationContext(), "Image Uploaded", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else if(imageType.equals("AadharPic")) {
                        aadharPicImage.post(new Runnable() {
                            @Override
                            public void run() {
                                aadharPicImage.setImageBitmap(Utils.getImage(bytes));
                                //showMessage("Image Loaded from Database");
                                Toast.makeText(getApplicationContext(), "Image Uploaded", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else if(imageType.equals("PanPic")) {
                        panCardImage.post(new Runnable() {
                            @Override
                            public void run() {
                                panCardImage.setImageBitmap(Utils.getImage(bytes));
                                //showMessage("Image Loaded from Database");
                                Toast.makeText(getApplicationContext(), "Image Uploaded", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else if(imageType.equals("DrivingLicense")) {
                        drivinglicencePicImage.post(new Runnable() {
                            @Override
                            public void run() {
                                drivinglicencePicImage.setImageBitmap(Utils.getImage(bytes));
                                //showMessage("Image Loaded from Database");
                                Toast.makeText(getApplicationContext(), "Image Uploaded", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (Exception e) {
                    Log.e(TAG, "<loadImageFromDB> Error : " + e.getLocalizedMessage());
                    dbHelper.close();
                }
            }
        }).start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_full_details);

        // Create the Database helper object
        dbHelper = new DBHelper(this);
        profilePicBtn = (Button) findViewById(R.id.showprofilepicbutton);
        aadharPicBtn = (Button) findViewById(R.id.showaadharbutton);
        panCardPicBtn = (Button) findViewById(R.id.showpancardbutton);
        drivingLicenceBtn = (Button) findViewById(R.id.showdrivinglicencebutton);
        profilePicImage = (ImageView) findViewById(R.id.profilePicImage);
        aadharPicImage = (ImageView) findViewById(R.id.aadharPicImage);
        drivinglicencePicImage = (ImageView) findViewById(R.id.drivinglicencePicImage);
        panCardImage = (ImageView) findViewById(R.id.panCardPicImage);

        Intent intent = getIntent();
        imageId = intent.getStringExtra("imageId");

        profilePicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //imageId = "9d831e86-a81d-4c7f-bc53-d9da082b2289";
                imageType = "ProfilePic";
                loadImageFromDB();
            }
        });

        aadharPicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //imageId = "9d831e86-a81d-4c7f-bc53-d9da082b2289";
                imageType = "AadharPic";
                loadImageFromDB();
            }
        });

        panCardPicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //imageId = "9d831e86-a81d-4c7f-bc53-d9da082b2289";
                imageType = "PanPic";
                loadImageFromDB();
            }
        });

        drivingLicenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //imageId = "9d831e86-a81d-4c7f-bc53-d9da082b2289";
                imageType = "DrivingLicense";
                loadImageFromDB();
            }
        });

    }
}