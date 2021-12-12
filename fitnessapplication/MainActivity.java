package com.example.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;
import android.widget.Button;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button _btnHome;
    private Button _btnIndex;
    private Button _btnInfo;
    public static ArrayList<TestResult> Results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _btnHome = (Button)findViewById(R.id.btnHome);
        _btnIndex = (Button)findViewById(R.id.btnIndexes);
        _btnInfo = (Button)findViewById(R.id.btnInfo);
        ReplaceFragment(new fragment_home());

        _btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReplaceFragment(new fragment_home());
            }
        });

        _btnIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReplaceFragment(new fragment_index());
            }
        });

        _btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReplaceFragment(new fragment_info());
            }
        });
    }


    public void ReplaceFragment(Fragment fragment)
    {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }
}