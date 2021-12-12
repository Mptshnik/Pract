package com.example.fitnessapplication;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BMIFragment extends BaseFragment {

    private Button _continueButton;
    private EditText _textWeight;
    private EditText _textHeight;
    private String _description;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_bmi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        _textHeight = (EditText)view.findViewById(R.id.editTextHeight);
        _textWeight = (EditText)view.findViewById(R.id.editTextWeight);

        _description = getResources().getString(R.string.bmi_fragment);


        _continueButton = (Button)view.findViewById(R.id.btnContinue);
        _continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double weight = Double.valueOf(_textWeight.getText().toString());
                    double height = Double.valueOf(_textHeight.getText().toString());

                    double I = weight / Math.pow(height / 100, 2);
                    String message = getMessage(I);

                    double scale = Math.pow(10, 2);
                    I = Math.round(I * scale) / scale;

                    Bundle result = new Bundle();
                    result.putString("Message", message);
                    result.putString("Description", _description);
                    result.putString("Result", String.valueOf(I));
                    result.putInt("NextFragmentID", 0);

                    String timeStamp = new SimpleDateFormat("yyyy.MM.dd").format(Calendar.getInstance().getTime());
                    TestResult testResult = new TestResult("ИМТ", String.valueOf(I), message, timeStamp);
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

    private String getMessage(double i)
    {
        String result = "";

        if(i >= 16 && i<=18.5)
        {
            result = "Наблюдается недостаток массы тела";
        }
        else if(i >= 18.5 && i<=25)
        {
            result = "Норма";
        }
        else if(i >= 25 && i <= 30)
        {
            result = "Избыточная масса тела";
        }
        else if (i >= 30 && i <= 35)
        {
            result = "Первая степень ожирения";
        }
        else if(i >= 35 && i <= 40)
        {
            result = "Вторая степень ожирения";
        }
        else
        {
            result = "Третья степень ожирения";
        }

        return result;

    }
}