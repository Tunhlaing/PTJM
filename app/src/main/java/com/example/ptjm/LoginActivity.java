package com.example.ptjm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout  ti_login_username,ti_login_password;
    TextInputEditText et_login_password,et_login_username;
    Button bt_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        onClicked();


    }




    private void initView() {
        ti_login_username = findViewById(R.id.ti_login_username);
        ti_login_password = findViewById(R.id.ti_login_password);

        et_login_username = findViewById(R.id.et_login_username);
        et_login_password = findViewById(R.id.et_login_password);

        bt_register = findViewById(R.id.bt_register);

    }
    private void loginUser(String username, String password) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("user_table");
        Query query = usersRef.orderByChild("username").equalTo(username);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean isLoginSuccessful = false;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Get the password from the database entry
                    String dbPassword = snapshot.child("password").getValue(String.class);

                    // Compare the database password with the user input password
                    if (dbPassword.equals(password)) {
                        isLoginSuccessful = true;
                        break;
                    }
                }

                if (isLoginSuccessful) {
                    // Login successful, perform necessary actions
                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this,TestingActivity.class));
                    // Proceed with the desired actions after successful login
                    // For example, start a new activity or navigate to the user's dashboard
                } else {
                    // Login failed, display an error message
                    Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void onClicked() {
        bt_register.setOnClickListener(v -> {
            loginUser(et_login_username.getText().toString(),et_login_password.getText().toString());

        });
    }

}