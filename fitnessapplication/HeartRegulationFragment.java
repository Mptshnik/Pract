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

public class HeartRegulationFragment extends BaseFragment {
    private Button _continueButton;
    private EditText _textDiastolicPressure;
    private EditText _textHeartBeats;

    public HeartRegulationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_heart_regulation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        _continueButton =(Button) view.findViewById(R.id.btnContinue);
        _textDiastolicPressure = (EditText) view.findViewById(R.id.editTextDiastolicPressure);
        _textHeartBeats = (EditText) view.findViewById(R.id.editTextHeartBeats);

        _continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int diast = Integer.valueOf(_textDiastolicPressure.getText().toString());
                    int beats = Integer.valueOf(_textHeartBeats.getText().toString());
                    int res = (beats * diast) / 100;
                    String m = getMessage(res);
                    String description = getResources().getString(R.string.heart_recommendations);

                    Bundle result = new Bundle();
                    result.putString("Message", m);
                    result.putString("Description", description);
                    result.putString("Result", String.valueOf(res));
                    result.putInt("NextFragmentID", 3);

                    String timeStamp = new SimpleDateFormat("yyyy.MM.dd").format(Calendar.getInstance().getTime());
                    TestResult testResult = new TestResult("Уровень регуляции сердечно-сосудистой системы",
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

    private String getMessage(int result)
    {
        String m = "";

        if(result < 74)
        {
            m = "высокий уровень регуляции сердечно-сосудистой системы";
        }
        else if(result >= 75 && result <= 80)
        {
            m = "выше среднего";
        }
        else if(result >= 81 && result <= 90)
        {
            m = "средний";
        }
        else if(result >= 91 && result <= 100)
        {
            m = "ниже среднего";
        }
        else
        {
            m = "низкое значение регуляции";
        }
        return  m;
    }
}