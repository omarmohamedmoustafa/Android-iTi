package com.example.forumapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button btnSave;

    private Button btnExit;

    private EditText edtPhone;
    private EditText edtMsg;
    private final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnExit=findViewById(R.id.exitBtn);
        btnSave=findViewById(R.id.saveBtn);
        edtMsg = findViewById(R.id.editMessage);
        edtPhone = findViewById(R.id.editTextPhone);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                intent.putExtra("Mobile_NUMBER",edtPhone.getText().toString());
                intent.putExtra("Message",edtMsg.getText().toString());
                startActivity(intent);
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Log.i(TAG, "onCreate: ");
    };



}