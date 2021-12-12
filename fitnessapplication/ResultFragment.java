package com.example.fitnessapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.widget.Button;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultFragment extends Fragment {


    private TextView _textViewResult;
    private TextView _textViewMessage;
    private TextView _textViewDescription;
    private Button _btnContiniue;
    private ArrayList<Fragment> _fragments;
    int _nextFragmentId = 0;
    public ResultFragment() {
        _fragments = new ArrayList<Fragment>();

        _fragments.add(new MALFragment());
        _fragments.add(new StaminaFragment());
        _fragments.add(new HeartRegulationFragment());
        _fragments.add(new LifeIndexFragment());
        _fragments.add(new SkibinskyFragment());
        _fragments.add(new KerdoFragment());
        _fragments.add(new FCIFragment());
        _fragments.add(new fragment_info());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        _textViewResult = (TextView)view.findViewById(R.id.textViewResult);
        _textViewMessage = (TextView)view.findViewById(R.id.textViewMessage);
        _textViewDescription = (TextView)view.findViewById(R.id.textViewDescription);
       _btnContiniue = (Button) view.findViewById(R.id.btnContinue);

        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                String message = bundle.getString("Message");
                String result = bundle.getString("Result");
                String description= bundle.getString("Description");
                _nextFragmentId = bundle.getInt("NextFragmentID");

                _textViewResult.setText("Ваш результат: " + result);
                _textViewMessage.setText("Описание: "+ message);
                _textViewDescription.setText(description);
            }
        });

        _btnContiniue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReplaceFragment(getFragmentByID(_nextFragmentId));
            }
        });
    }

    public Fragment getFragmentByID(int id)
    {
        return _fragments.get(id);
    }

    public void ReplaceFragment(Fragment fragment)
    {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }
}