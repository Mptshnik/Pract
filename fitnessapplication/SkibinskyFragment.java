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

public class SkibinskyFragment extends BaseFragment {

    private Button _continueButton;
    private EditText _textShtange;
    private EditText _textHeartBeats;
    private EditText _textLungCapacity;

    public SkibinskyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_skibinsky, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        _textHeartBeats = (EditText) view.findViewById(R.id.editTextHeartBeats);
        _continueButton =(Button) view.findViewById(R.id.btnContinue);
        _textShtange = (EditText)view.findViewById(R.id.editTextShtange);
        _textLungCapacity= (EditText) view.findViewById(R.id.editTextLungCapacity);

        _continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Integer capacity = Integer.valueOf(_textLungCapacity.getText().toString());
                    Integer beats = Integer.valueOf(_textHeartBeats.getText().toString());
                    Integer seconds = Integer.valueOf(_textShtange.getText().toString());

                    int res = ((capacity / 100) * seconds)/beats;
                    String message = getMessage(res);
                    String descr = getResources().getString(R.string.index_skibinsky);

                    Bundle result = new Bundle();
                    result.putString("Message", message);
                    result.putString("Description", descr);
                    result.putString("Result", String.valueOf(res));
                    result.putInt("NextFragmentID", 5);

                    String timeStamp = new SimpleDateFormat("yyyy.MM.dd").format(Calendar.getInstance().getTime());
                    TestResult testResult = new TestResult("Циркулярно-респираторный коэффициент Скибински",
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

        if(res <= 5)
        {
            m = "очень плохо";
        }
        else if(res >= 5 && res <= 10)
        {
            m = "неудовлетворительно";
        }
        else if(res >= 10 && res <= 30)
        {
            m = "удовлетворительно";
        }
        else if(res >= 30 && res <= 60)
        {
            m = "хорошо";
        }
        else
        {
            m = "очень хорошо";
        }

        return m;
    }

}