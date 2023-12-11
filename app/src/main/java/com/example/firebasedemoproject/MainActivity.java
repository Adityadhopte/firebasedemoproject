package com.example.firebasedemoproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextView textView, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Get a reference to a specific node in the database
        DatabaseReference myRef = database.getReference("Users");

        // Write a value to the specified database location
        UserModel user1 = new UserModel("Jack", "jack@gmail.com");
        myRef.setValue(user1);

        //    myRef.setValue("Hello from our Course!");


        textView = findViewById(R.id.email);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                UserModel user = snapshot.getValue(UserModel.class);

                textView.setText("Email: " + user.getEmail());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle Errors here

                Toast.makeText(MainActivity.this, "Data is Null", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
