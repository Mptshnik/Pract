package com.example.fitnessapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FCIFragment extends BaseFragment {
    private Button _continueButton;
    private EditText _textSystolicPressure;
    private EditText _textDiastolicPressure;
    private EditText _textHeartBeats;
    private EditText _textWeight;
    private EditText _textHeight;
    private EditText _textAge;

    private String _age;

    public FCIFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fci, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        _continueButton =(Button) view.findViewById(R.id.btnContinue);
        _textSystolicPressure = (EditText) view.findViewById(R.id.editTextSystolicPressure);
        _textDiastolicPressure = (EditText) view.findViewById(R.id.editTextDiastolicPressure);
        _textHeartBeats = (EditText) view.findViewById(R.id.editTextHeartBeats);
        _textWeight = (EditText) view.findViewById(R.id.editTextWeight);
        _textHeight = (EditText) view.findViewById(R.id.editTextHeight);
        _textAge = (EditText) view.findViewById(R.id.editTextAge);


        _continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double systolic = Double.valueOf(_textSystolicPressure.getText().toString());
                    double diastolic = Double.valueOf(_textDiastolicPressure.getText().toString());
                    double beats = Double.valueOf(_textHeartBeats.getText().toString());
                    double weight = Double.valueOf(_textWeight.getText().toString());
                    double height = Double.valueOf(_textHeight.getText().toString());
                    double age = Double.valueOf(_textAge.getText().toString());

                    double index = 0.011 * beats + 0.014 * systolic + 0.008 * diastolic +
                            0.014 * age + 0.009 * weight - 0.009 * (height / 100) - 0.27;
                    String message = getMessage(index);
                    String description = getResources().getString(R.string.stamina_recommendations);

                    double scale = Math.pow(10, 2);
                    index = Math.round(index * scale) / scale;

                    Bundle result = new Bundle();
                    result.putString("Message", message);
                    result.putString("Description", description);
                    result.putString("Result", String.valueOf(index));
                    result.putInt("NextFragmentID", 7);

                    String timeStamp = new SimpleDateFormat("yyyy.MM.dd").format(Calendar.getInstance().getTime());
                    TestResult testResult = new TestResult("Индекс функциональных изменений",
                            String.valueOf(index), message, timeStamp);
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
        String m ="";

        if(res <= 2.6)
        {
            m = "функциональные возможности системы кровообращения хорошие";
        }
        else if(res >= 2.6 && res <= 3.09)
        {
            m = "удовлетворительные функциональные возможности системы кровообращения с умеренным напряжением механизмов регуляции";
        }
        else
        {
            m = "сниженные, недостаточные возможности системы кровообращения";
        }
        return m;
    }
}