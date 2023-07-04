package com.example.ptjm.ui;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.ptjm.databinding.ActivityLoginBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding loginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());
        onClicked();


    }

    private void loginUser(String username, String password) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users_table");
        Query query = usersRef.orderByChild("username").equalTo(username);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            String sentUserId = "";
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean isLoginSuccessful = false;


                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Get the password from the database entry
                    String dbPassword = snapshot.child("password").getValue(String.class);
                    sentUserId = snapshot.child(("userId")).getValue(String.class);

                    // Compare the database password with the user input password
                    if (dbPassword.equals(password)) {
                        isLoginSuccessful = true;

                        break;
                    }
                }

                if (isLoginSuccessful) {
                    // Login successful, perform necessary actions
                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, JobListActivity.class).putExtra("sentUserID",sentUserId));
                    Log.e(TAG, "onUser: "+ sentUserId);
                    //startActivity(new Intent(LoginActivity.this, CreateJob.class).putExtra("sentuserID",sentUserId));
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
        loginBinding.btLogin.setOnClickListener(v -> {
            loginUser(loginBinding.etLoginUsername.getText().toString(),loginBinding.etLoginPassword.getText().toString());

        });
    }

}