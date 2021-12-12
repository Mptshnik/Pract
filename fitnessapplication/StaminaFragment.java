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


public class StaminaFragment extends BaseFragment {
    private Button _continueButton;
    private EditText _textSystolicPressure;
    private EditText _textDiastolicPressure;
    private EditText _textHeartBeats;

    public StaminaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stamina, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        _continueButton =(Button) view.findViewById(R.id.btnContinue);
        _textSystolicPressure = (EditText) view.findViewById(R.id.editTextSystolicPressure);
        _textDiastolicPressure = (EditText) view.findViewById(R.id.editTextDiastolicPressure);
        _textHeartBeats = (EditText) view.findViewById(R.id.editTextHeartBeats);

        _continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int syst = Integer.valueOf(_textSystolicPressure.getText().toString());
                    int diast = Integer.valueOf(_textDiastolicPressure.getText().toString());
                    int beats = Integer.valueOf(_textHeartBeats.getText().toString());

                    int c = (beats * 10) / (syst - diast);
                    String message = getMessage(c);
                    String description = getResources().getString(R.string.stamina_recommendations);

                    Bundle result = new Bundle();
                    result.putString("Message", message);
                    result.putString("Description", description);
                    result.putString("Result", String.valueOf(c));
                    result.putInt("NextFragmentID", 2);

                    String timeStamp = new SimpleDateFormat("yyyy.MM.dd").format(Calendar.getInstance().getTime());
                    TestResult testResult = new TestResult("Коэффициент выносливости",
                            String.valueOf(c), message, timeStamp);
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

        if(result > 14 && result < 18)
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