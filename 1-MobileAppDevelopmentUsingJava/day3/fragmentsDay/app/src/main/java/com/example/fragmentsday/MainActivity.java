package com.example.fragmentsday;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.fragmentsday.dynamicFragment;
public class MainActivity extends AppCompatActivity {

    dynamicFragment DynamicFragment;
    FragmentManager mgr = getSupportFragmentManager();

    FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState==null)
        {
            DynamicFragment = new dynamicFragment();
            transaction = mgr.beginTransaction();
            transaction.add(R.id.fragmentContainerView2, DynamicFragment, "DynFrag");
            transaction.commit();
        }else {
            DynamicFragment =  (dynamicFragment)mgr.findFragmentByTag("DynFrag");
        }

        Button btn = findViewById(R.id.addBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}