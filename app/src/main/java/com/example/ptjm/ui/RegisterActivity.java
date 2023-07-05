package com.example.ptjm.ui;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ptjm.R;
import com.example.ptjm.databinding.ActivityRegisterBinding;
import com.example.ptjm.model.RegisterModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding registerBinding;


    String id;
    int genderId = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(registerBinding.getRoot());
        onclick();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.specialized_fields_drop_drown, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        registerBinding.spinner.setAdapter(adapter);




    }

    private void onclick() {
        registerBinding.rgGender.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == registerBinding.rbMale.getId()) {
                genderId = 0;
            } else {
                genderId = 1;
            }
        });
        registerBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        registerBinding.btRegister.setOnClickListener(v -> {
            if(checkValidations()){
                checkUsernameExists(registerBinding.etUsername.getText().toString());
           }
        });
    }

    private boolean checkValidations() {


        if (TextUtils.isEmpty(registerBinding.etUsername.getText().toString())) {
            registerBinding.tiUsername.setError("Enter Username");
            return false;
        } else if (TextUtils.isEmpty(registerBinding.etAge.getText().toString())) {
            registerBinding.tiUsername.setError(null);
            registerBinding.tiAge.setError("Enter Age");
            return false;
        } else if (!(Integer.parseInt(registerBinding.etAge.getText().toString()) >= 18 && Integer.parseInt(registerBinding.etAge.getText().toString()) <=55 )){
            registerBinding.tiUsername.setError(null);
            registerBinding.tiAge.setError("only can use between 18 yeasr old and 55 years old");
            return false;
        }else if (TextUtils.isEmpty(registerBinding.etPhoneNumber.getText().toString())) {
            registerBinding.tiAge.setError(null);
            registerBinding.tiUsername.setError(null);
            registerBinding.tiPhoneNumber.setError("Enter Phone Number");
            return false;
        } else if (TextUtils.isEmpty(registerBinding.etAddress.getText().toString())) {
            registerBinding.tiPhoneNumber.setError(null);
            registerBinding.tiUsername.setError(null);
            registerBinding.tiAge.setError(null);
            registerBinding.tiAddress.setError("Enter address");
            return false;
        } else if (TextUtils.isEmpty(registerBinding.etPassword.getText().toString())) {
            registerBinding.tiAddress.setError(null);
            registerBinding.tiUsername.setError(null);
            registerBinding.tiAge.setError(null);
            registerBinding.tiPhoneNumber.setError(null);

            registerBinding.tiPassword.setError("please fill password");
            return false;
        } else if (!(registerBinding.etPassword.length() >= 6 && registerBinding.etPassword.length() <= 15)) {
            registerBinding.tiAddress.setError(null);
            registerBinding.tiUsername.setError(null);
            registerBinding.tiAge.setError(null);
            registerBinding.tiPhoneNumber.setError(null);
            registerBinding.tiAddress.setError(null);
            registerBinding.tiPassword.setError(null);
            registerBinding.tiPassword.setError("Password must be between 6 and 15 characters");
            return false;
        } else if (TextUtils.isEmpty(registerBinding.etConfirmPassword.getText().toString())) {
            registerBinding.tiPassword.setError(null);
            registerBinding.tiUsername.setError(null);
            registerBinding.tiAge.setError(null);
            registerBinding.tiPhoneNumber.setError(null);
            registerBinding.tiAddress.setError(null);
            registerBinding.tiPassword.setError(null);
            registerBinding.tiConfirmPassword.setError("need confirm password");
            return false;
        } else if (!registerBinding.etConfirmPassword.getText().toString().equals(registerBinding.etPassword.getText().toString())) {
            registerBinding.tiPassword.setError(null);
            registerBinding.tiUsername.setError(null);
            registerBinding.tiAge.setError(null);
            registerBinding.tiPhoneNumber.setError(null);
            registerBinding.tiAddress.setError(null);
            registerBinding.tiConfirmPassword.setError(null);
            registerBinding.tiConfirmPassword.setError("password don't match");
            return false;
        } else {
            return true;
        }
    }

    private void addUser(int posterType){
        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("users_table");
            if (id == null) {
                id = myRef.push().getKey();
            }
            String username = registerBinding.etUsername.getText().toString();
            int age = Integer.parseInt(registerBinding.etAge.getText().toString());
            String address = registerBinding.etAddress.getText().toString();
            String phoneNumber = registerBinding.etPhoneNumber.getText().toString();
            String specializedField = registerBinding.spinner.getSelectedItem().toString();
            int userType = posterType;
            String password = registerBinding.etPassword.getText().toString();
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String joinedDateTime = dateFormat.format(currentDate);


            String gender = "";
            if (genderId == 0){
                gender = "male";
            }else {
                gender = "female";
            }


            RegisterModel registerModel1 =new RegisterModel(id,username,age,address,phoneNumber,password,userType,gender,specializedField,joinedDateTime);
            myRef.child(id).setValue(registerModel1).addOnCompleteListener(task ->
                Toast.makeText(RegisterActivity.this, "new user "+username+" is already register!!!", Toast.LENGTH_LONG).show());
                Toast.makeText(RegisterActivity.this, "register success ", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

        } catch (Exception e) {
            Toast.makeText(this, "Some error occurred when register user", Toast.LENGTH_SHORT).show();
        }

    }

    private void checkUsernameExists(String username) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users_table");
        Query query = usersRef.orderByChild("username").equalTo(username);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean isUsernameExists = dataSnapshot.exists();

                if (isUsernameExists) {
                    registerBinding.tiUsername.setError("Username already exists");
                    registerBinding.etUsername.requestFocus();

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










