package com.example.forumapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    TextView mob_num_tv;
    TextView msg_txt_tv;

    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mob_num_tv=findViewById(R.id.mob_num_tv);
        msg_txt_tv=findViewById(R.id.msg_txt_tv);
        btnBack=findViewById(R.id.btn_back);
        Intent incoming_intent= getIntent();
        String mobile=incoming_intent.getStringExtra("Mobile_NUMBER");
        String message = incoming_intent.getStringExtra("Message");
        mob_num_tv.setText(mobile);
        msg_txt_tv.setText(message);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        };
}
