package com.example.ptjm;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    TextInputLayout ti_username,ti_age,ti_phoneNumber,ti_address,ti_specialized_field, ti_password,ti_confirm_password;
    TextInputEditText et_username,et_age,et_phoneNumber,et_address,et_password,et_confirm_password;
    Button bt_register;
    Spinner spinner;

    RadioGroup rgGender;
    RadioButton rbMale, rbFemale;
    String id;
    RegisterModel registerModel;
    int existingOrNot = 0;
    int genderId = 0;
    long currentTimestamp = System.currentTimeMillis();
    String registrationDate = String.valueOf(currentTimestamp);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        onclick();


        }

    private void onclick() {
        rgGender.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == rbMale.getId()) {
                genderId = 0;
            } else {
                genderId = 1;
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item
                String selectedItem = parent.getItemAtPosition(position).toString();
                String specializedField = selectedItem;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        bt_register.setOnClickListener(v -> {
            if(checkValidations()){
                checkUsernameExists(et_username.getText().toString());
           }
        });
    }

    private boolean checkValidations() {


        if (TextUtils.isEmpty(et_username.getText().toString())) {
            ti_username.setError("Enter Username");
            return false;
        } else if (TextUtils.isEmpty(et_age.getText().toString())) {
            ti_username.setError(null);
            ti_age.setError("Enter Age");
            return false;
        } else if (!(Integer.parseInt(et_age.getText().toString()) >= 18 && Integer.parseInt(et_age.getText().toString()) <=55 )){
            ti_username.setError(null);
            ti_age.setError("only can use between 18 yeasr old and 55 years old");
            return false;
        }else if (TextUtils.isEmpty(et_phoneNumber.getText().toString())) {
            ti_age.setError(null);
            ti_username.setError(null);
            ti_phoneNumber.setError("Enter Phone Number");
            return false;
        } else if (TextUtils.isEmpty(et_address.getText().toString())) {
            ti_phoneNumber.setError(null);
            ti_username.setError(null);
            ti_age.setError(null);

            ti_address.setError("Enter address");
            return false;
        } else if (TextUtils.isEmpty(et_password.getText().toString())) {
            ti_address.setError(null);
            ti_username.setError(null);
            ti_age.setError(null);
            ti_phoneNumber.setError(null);

            ti_password.setError("please fill password");
            return false;
        } else if (!(et_password.length() >= 6 && et_password.length() <= 15)) {
            ti_address.setError(null);
            ti_username.setError(null);
            ti_age.setError(null);
            ti_phoneNumber.setError(null);
            ti_address.setError(null);
            ti_password.setError(null);
            ti_password.setError("Password must be between 6 and 15 characters");
            return false;
        } else if (TextUtils.isEmpty(et_confirm_password.getText().toString())) {
            ti_password.setError(null);
            ti_username.setError(null);
            ti_age.setError(null);
            ti_phoneNumber.setError(null);
            ti_address.setError(null);
            ti_password.setError(null);
            ti_confirm_password.setError("need confirm password");
            return false;
        } else if (!et_confirm_password.getText().toString().equals(et_password.getText().toString())) {
            ti_password.setError(null);
            ti_username.setError(null);
            ti_age.setError(null);
            ti_phoneNumber.setError(null);
            ti_address.setError(null);
            ti_confirm_password.setError(null);
            ti_confirm_password.setError("password don't match");
            return false;
        } else {
            return true;
        }
    }

    private void initView() {


        ti_username = findViewById(R.id.ti_username);
        ti_age = findViewById(R.id.ti_age);
        ti_phoneNumber = findViewById(R.id.ti_phoneNumber);
        ti_specialized_field = findViewById(R.id.ti_specialized_field);

        rgGender = findViewById(R.id.rgGender);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);

        ti_address = findViewById(R.id.ti_address);
        ti_password = findViewById(R.id.ti_password);
        ti_confirm_password = findViewById(R.id.ti_confirm_password);

        et_username = findViewById(R.id.et_username);
        et_age = findViewById(R.id.et_age);
        et_phoneNumber = findViewById(R.id.et_phoneNumber);
        et_address = findViewById(R.id.et_address);
        et_password = findViewById(R.id.et_password);
        et_confirm_password = findViewById(R.id.et_confirm_password);

        bt_register = findViewById(R.id.bt_register);

        spinner = findViewById(R.id.spinner);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.specialized_fields_drop_drown, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    private void addUser(int posterType){
        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("users_table");
            if (id == null) {
                id = myRef.push().getKey();
            }
            String username = et_username.getText().toString();
            int age = Integer.parseInt(et_age.getText().toString());
            String address = et_address.getText().toString();
            String phoneNumber = et_phoneNumber.getText().toString();
            String specializedField = spinner.getSelectedItem().toString();
            int userType = posterType;
            String password = et_password.getText().toString();

            String gender = "";
            if (genderId == 0){
                gender = "male";
            }else {
                gender = "female";
            }


            RegisterModel registerModel1 =new RegisterModel(id,username,age,address,phoneNumber,password,userType,gender,specializedField,registrationDate);
            myRef.child(id).setValue(registerModel1).addOnCompleteListener(task ->
                Toast.makeText(RegisterActivity.this, "new user "+username+" is already register!!!", Toast.LENGTH_LONG).show());
                Toast.makeText(RegisterActivity.this, "register success ", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "addUser: registrationDate is " + registrationDate);
                finish();
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);

        } catch (Exception e) {
            Toast.makeText(this, "Some error occurred when register user", Toast.LENGTH_SHORT).show();
        }

    }

    private void checkUsernameExists(String username) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("user_table");
        Query query = usersRef.orderByChild("username").equalTo(username);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean isUsernameExists = dataSnapshot.exists();

                if (isUsernameExists) {
                    ti_username.setError("Username already exists");
                    et_username.requestFocus();

                } else {
                    int registerTypeValue = getIntent().getIntExtra("registerType", 1);
                    addUser(registerTypeValue);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle potential errors during database query
            }
        });
    }

    }










