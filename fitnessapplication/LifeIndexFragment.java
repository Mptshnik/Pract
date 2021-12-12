package com.example.fitnessapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LifeIndexFragment extends BaseFragment {
    private Button _continueButton;
    private EditText _textLungCapacity;
    private EditText _textWeight;

    public LifeIndexFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_life_index, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _continueButton =(Button) view.findViewById(R.id.btnContinue);
        _textWeight = (EditText) view.findViewById(R.id.editTextWeight);
        _textLungCapacity= (EditText) view.findViewById(R.id.editTextLungCapacity);

        _continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double weight = Double.valueOf(_textWeight.getText().toString());
                    Double capacity = Double.valueOf(_textLungCapacity.getText().toString());
                    double res = capacity / weight;
                    double scale = Math.pow(10, 2);
                    res = Math.round(res * scale) / scale;

                    String m = getMessage(res);
                    String description = getResources().getString(R.string.lifeindex_recommendations);

                    Bundle result = new Bundle();
                    result.putString("Message", m);
                    result.putString("Description", description);
                    result.putString("Result", String.valueOf(res));
                    result.putInt("NextFragmentID", 4);

                    String timeStamp = new SimpleDateFormat("yyyy.MM.dd").format(Calendar.getInstance().getTime());
                    TestResult testResult = new TestResult("Жизненный индекс",
                            String.valueOf(res), m, timeStamp);
                    MainActivity.Results.add(testResult);

                    getParentFragmentManager().setFragmentResult("key", result);
                    ReplaceFragment(new ResultFragment());
                }
                catch (Exception e)
                {
                    Toast toast = Toast.makeText(getActivity(), "Ошибка",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }

    private String getMessage(double res)
    {
        String m = "";
        if(res >= 50 && res <= 61)
        {
            m = "Норма";
        }
        else
        {
            m = "Не норма";
        }
        return  m;
    }
}