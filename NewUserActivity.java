package com.example.ambulanceservice_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.UUID;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.FileInputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

public class NewUserActivity extends AppCompatActivity {
    private EditText txtfirstName,
            txtlastName, txtemailId, txtphoneNum,
            txtuserName, txtpassword;
    private String id, firstName, lastName, emailId, phoneNum, userName, password,
            gender, imageId;
    private Button signupbtn, gobackbtn, uploadprofilepicbutton, uploadaadharpicbutton;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private FirebaseFirestore db;
    private ImageView profilePicImage, aadharImage;
    private static final int SELECT_PICTURE = 100;
    private static final String TAG = "StoreImageActivity";
    private Uri selectedImageUri;
    private DBHelper dbHelper;
    private String imageType;

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
                    // update the preview image in the layout
                    if(imageType.equals("ProfilePic"))
                        profilePicImage.setImageURI(selectedImageUri);
                    else if(imageType.equals("AadharPic"))
                        aadharImage.setImageURI(selectedImageUri);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        txtfirstName=(EditText)findViewById(R.id.editTextFname);
        txtlastName=(EditText)findViewById(R.id.editTextLname);
        txtemailId=(EditText)findViewById(R.id.editTextEmail);
        txtphoneNum=(EditText)findViewById(R.id.editTextPhoneNum);
        txtuserName=(EditText)findViewById(R.id.editTextUname);
        txtpassword=(EditText)findViewById(R.id.editTextPwd);
        radioGroup = (RadioGroup)findViewById(R.id.genderradioGroup);
        signupbtn = (Button)findViewById(R.id.SignUpBtn);
        gobackbtn= (Button)findViewById(R.id.gobackbtn);
        profilePicImage = findViewById(R.id.profilePicImage);
        aadharImage = findViewById(R.id.aadharPicImage);
        uploadprofilepicbutton = (Button)findViewById(R.id.uploadprofilepicbutton);
        uploadaadharpicbutton= (Button)findViewById(R.id.uploadedaadharbutton);

        db = FirebaseFirestore.getInstance();

        UUID uuid=UUID.randomUUID(); //Generates random UUID
        imageId = uuid.toString();

        // Create the Database helper object
        dbHelper = new DBHelper(this);
        profilePicImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageType="ProfilePic";
                imageChooser();
            }
        });

        aadharImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageType="AadharPic";
                imageChooser();
            }
        });

        profilePicImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageType="ProfilePic";
                imageChooser();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
                gender = radioButton.getText().toString();
            }
        });

        gobackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        txtfirstName.setFilters(new InputFilter[]{acceptonlyAlphabetValuesnotNumbersMethod()});
        txtlastName.setFilters(new InputFilter[]{acceptonlyAlphabetValuesnotNumbersMethod()});

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstName=txtfirstName.getText().toString();
                lastName=txtlastName.getText().toString();
                emailId=txtemailId.getText().toString();
                phoneNum=txtphoneNum.getText().toString();
                userName=txtuserName.getText().toString();
                password=txtpassword.getText().toString();

                Pattern pattern = Pattern.compile("^\\d{10}$");
                Matcher matcher = pattern.matcher(phoneNum);

                if(TextUtils.isEmpty(firstName))
                {
                    Toast.makeText(getApplicationContext(), "First Name is Empty", Toast.LENGTH_SHORT).show();
                    txtfirstName.setFocusable(true);
                }
                else if(TextUtils.isEmpty(lastName))
                {
                    Toast.makeText(getApplicationContext(), "Last Name is Empty", Toast.LENGTH_SHORT).show();
                    txtlastName.setFocusable(true);
                }
                else if(TextUtils.isEmpty(emailId))
                {
                    Toast.makeText(getApplicationContext(), "Email Id is Empty", Toast.LENGTH_SHORT).show();
                    txtemailId.setFocusable(true);
                }else if(!Patterns.EMAIL_ADDRESS.matcher(emailId).matches())
                {
                    txtemailId.setError("EmailId is Not Valid");
                    txtemailId.setFocusable(true);
                }
                else if(TextUtils.isEmpty(phoneNum))
                {
                    Toast.makeText(getApplicationContext(), "Phone Num is Empty", Toast.LENGTH_SHORT).show();
                    txtphoneNum.setFocusable(true);
                }
                else if(!matcher.matches())
                {
                    txtphoneNum.setError("Phone Num is Invalid should be 10 digits");
                    txtphoneNum.setFocusable(true);
                }
                else if(TextUtils.isEmpty(gender))
                {
                    Toast.makeText(getApplicationContext(), "Gender is Empty", Toast.LENGTH_SHORT).show();
                    radioGroup.setFocusable(true);
                }
                else if(TextUtils.isEmpty(userName))
                {
                    Toast.makeText(getApplicationContext(), "User Name is Empty", Toast.LENGTH_SHORT).show();
                    txtuserName.setFocusable(true);
                }
                else if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(getApplicationContext(), "Password is Empty", Toast.LENGTH_SHORT).show();
                    txtpassword.setFocusable(true);
                }
                else{
/*
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference dbRef = database.getReference();
                    dbRef = database.getReference("NewUser");
                    id = dbRef.push().getKey();

                    NewUserClass newUserClass =new NewUserClass(id, firstName, lastName, gender,
                            emailId, phoneNum, userName, password, imageId);
                    dbRef.child(id).setValue(newUserClass);
                    Toast.makeText(getApplicationContext(), "New User Inserted Successfully",
                            Toast.LENGTH_LONG).show();

 */
                    CollectionReference dbCourses = db.collection("NewUser");
                    // adding our data to our courses object class.
                    NewUserClass newClass =new NewUserClass(imageId, firstName, lastName, gender,
                            emailId, phoneNum, userName, password, imageId);
                    // below method is use to add data to Firebase Firestore.
                    dbCourses.add(newClass).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            // after the data addition is successful
                            // we are displaying a success toast message.
                            Toast.makeText(getApplicationContext(), "User has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // this method is called when the data addition process is failed.
                            // displaying a toast message when data addition is failed.
                            Toast.makeText(getApplicationContext(), "Fail to add user \n" + e, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
    //Accept Only Alphabet in EditText
    public static InputFilter acceptonlyAlphabetValuesnotNumbersMethod() {
        return new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                boolean isCheck = true;
                StringBuilder sb = new StringBuilder(end - start);
                for (int i = start; i < end; i++) {
                    char c = source.charAt(i);
                    if (isCharAllowed(c)) {
                        sb.append(c);
                    } else {
                        isCheck = false;
                    }
                }
                if (isCheck)
                    return null;
                else {
                    if (source instanceof Spanned) {
                        SpannableString spannableString = new SpannableString(sb);
                        TextUtils.copySpansFrom((Spanned) source, start, sb.length(), null, spannableString, 0);
                        return spannableString;
                    } else {
                        return sb;
                    }
                }
            }

            private boolean isCharAllowed(char c) {
                Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
                Matcher match = pattern.matcher(String.valueOf(c));
                return match.matches();
            }
        };
    }
}