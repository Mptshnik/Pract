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

public class KerdoFragment extends BaseFragment {

    private Button _continueButton;
    private EditText _textDiastolicPressure;
    private EditText _textHeartBeats;

    public KerdoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_kerdo, container, false);
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

                    int res = 100 * (1 - (diast/beats));
                    String message = getMessage(res);
                    String description = getResources().getString(R.string.index_kerdo);

                    Bundle result = new Bundle();
                    result.putString("Message", message);
                    result.putString("Description", description);
                    result.putString("Result", String.valueOf(res));
                    result.putInt("NextFragmentID", 6);

                    String timeStamp = new SimpleDateFormat("yyyy.MM.dd").format(Calendar.getInstance().getTime());
                    TestResult testResult = new TestResult("Вегетативный индекс Кердо",
                            String.valueOf(res), message, timeStamp);
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

    private String getMessage(int res)
    {
        String m = "";
        if(res >= 31)
        {
            m = "выраженная симпатикотония";
        }
        else if(res >= 16 && res <= 30)
        {
            m = " симпатикотония";
        }
        else if(res >= -15 && res <= 12)
        {
            m = "уравновешенность симпатических и парасимпатических влияний";
        }
        else if(res >= -30 && res <= -16)
        {
            m = "парасимпатикотония";
        }
        else
        {
            m = "выраженная парасимпатикотония";
        }
        return m;
    }
}