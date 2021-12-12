package com.example.fitnessapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class fragment_info extends Fragment {

    private LinearLayout _linearLayout;

    public fragment_info() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _linearLayout = (LinearLayout)view.findViewById(R.id.linearLayout);
        for(TestResult testResult : MainActivity.Results)
        {

            String text = testResult.Name+"\nРезультат: " + testResult.Result + "\n" + "Описание: " +
                    testResult.Message + "\n" + "Дата: " + testResult.Date;

            TextView textView = new TextView(new ContextThemeWrapper(getActivity(), R.style.TextViewResult), null, 0);
            textView.setText(text);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(35, 20, 35, 20);
            textView.setLayoutParams(params);

            textView.setBackgroundColor(getResources().getColor(R.color.test_btn_color));
            _linearLayout.addView(textView);
        }

    }
}