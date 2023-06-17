package com.example.ptjm;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

public class RegisterActivity extends AppCompatActivity {

    TextInputLayout ti_username,ti_age,ti_phoneNumber,ti_address,ti_specialized_field, ti_password,ti_confirm_password;
    TextInputEditText et_username,et_age,et_phoneNumber,et_address,et_password,et_confirm_password;
    Button bt_register;

    RadioGroup rgGender;
    RadioButton rbMale, rbFemale;
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
   // String [] fields = {"baker","baby sitter","cooker","home maid","painter","general worker"};

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    String id;
    RegisterModel registerModel;
    int gender = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        onclick();
        }

    private void onclick() {
        bt_register.setOnClickListener(v -> {
            if (checkValidations()) {
                Toast.makeText(this, "checkvalidatio is pass", Toast.LENGTH_SHORT).show();
                int registerTypeValue = getIntent().getIntExtra("posterType",1);
                    addUser(registerTypeValue);
                    startActivity(new Intent(this,LoginActivity.class));



            }

        });
    }
    private boolean checkValidations() {
        if (TextUtils.isEmpty(et_username.getText().toString())){
            ti_username.setError("Enter Username");
            return false;
        } // #need to check with db
        else if (TextUtils.isEmpty(et_age.getText().toString())) {
            ti_username.setError(null);
            ti_age.setError("Enter Age");
            return false;
        }
        else if (TextUtils.isEmpty(et_phoneNumber.getText().toString())) {
            ti_age.setError(null);
            ti_phoneNumber.setError("Enter Phone Number");
            return false;
        }
        else if (TextUtils.isEmpty(et_address.getText().toString())) {
            ti_phoneNumber.setError(null);
            ti_address.setError("Enter address");
            return false;
        }
        else if (TextUtils.isEmpty(et_password.getText().toString())){
            ti_address.setError(null);
            ti_password.setError("please fill password");
            return false;
        }else if (!(et_password.length() >= 6 && et_password.length() <= 15)){
            ti_address.setError(null);
            ti_password.setError("Password must be between 6 and 15 characters");
            return false;
        }else if (TextUtils.isEmpty(et_confirm_password.getText().toString())){
            ti_password.setError(null);
            ti_confirm_password.setError("need confirm password");
        return false;
        }
        else if (!et_confirm_password.getText().toString().equals(et_password.getText().toString())){
            ti_password.setError(null);
            ti_confirm_password.setError("password don't match");
            return false;
        }
        else {
            return true;
        }
//        else if (TextUtils.isEmpty(autoCompleteTextView.getText().toString())) {
//            ti_address.setError(null);
//            autoCompleteTextView.setError("Enter Gender");
//        }
//        else if (TextUtils.isEmpty(autoCompleteTextView.getText().toString())) {
//            ti_address.setError(null);
//            autoCompleteTextView.setError("Enter Gender");
//        }


    }

    private void initView() {
//        autoCompleteTextView = findViewById(R.id.ti_specialized_field);
//        adapterItems = new ArrayAdapter<String>(this,R.layout.list_field,fields);
//        autoCompleteTextView.setAdapter(adapterItems);
//        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String field = parent.getItemAtPosition(position).toString();
//
//            }
//        });

        ti_username = findViewById(R.id.ti_username);
        ti_age = findViewById(R.id.ti_age);
        ti_phoneNumber = findViewById(R.id.ti_phoneNumber);

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

    }

    private void addUser(int posterType){
        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("user_table");
            if (id == null) {
                id = myRef.push().getKey();
            }
            String username = et_username.getText().toString();
            String age = et_age.getText().toString();
            String address = et_address.getText().toString();
            String phoneNumber = et_phoneNumber.getText().toString();
            int userType = posterType;
            //String specializedField = autoCompleteTextView.getText().toString();
            String password = et_password.getText().toString();

            RegisterModel registerModel1 =new RegisterModel(id,username,age,address,phoneNumber,password,userType);
            myRef.child(id).setValue(registerModel1).addOnCompleteListener(task ->
                Toast.makeText(RegisterActivity.this, "new user "+username+" is already register!!!", Toast.LENGTH_LONG).show());
            finish();
        } catch (Exception e) {
            Toast.makeText(this, "Some error occurred when register user", Toast.LENGTH_SHORT).show();
        }

    }

}