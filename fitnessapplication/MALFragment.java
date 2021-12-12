package com.example.fitnessapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.widget.Button;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MALFragment extends BaseFragment {

    private Button _btnContinue;
    private EditText _numberOfSteps;

    public MALFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_mal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        _btnContinue = (Button)view.findViewById(R.id.btnContinue);
        _numberOfSteps = (EditText) view.findViewById(R.id.editTextNumberOfSteps);

        _btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int steps = Integer.valueOf(_numberOfSteps.getText().toString());

                    int averageSteps = steps / 1;
                    String message = getMessage(steps);

                    Bundle result = new Bundle();
                    result.putString("Message", message);
                    result.putString("Description", "");
                    result.putString("Result", String.valueOf("В среднем шагов за год: "+ averageSteps));
                    result.putInt("NextFragmentID", 1);

                    String timeStamp = new SimpleDateFormat("yyyy.MM.dd").format(Calendar.getInstance().getTime());

                    TestResult testResult = new TestResult("Уровень двигательной активности", "В среднем шагов за год: " +
                            String.valueOf(averageSteps), message, timeStamp);
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

    private String getMessage(int steps)
    {
        String message = "";
        if(steps < 5000)
        {
            message = "сидячая работа";
        }
        else if (steps >= 5000 && steps < 10000)
        {
            message = "несколько активная работа";
        }
        else if(steps >= 10000 && steps <= 12000)
        {
            message = "активный образ жизни";
        }
        else
        {
            message = "очень активный образ жизни";
        }

        return message;
    }
}