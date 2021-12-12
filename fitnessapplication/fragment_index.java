package com.example.fitnessapplication;

import static java.lang.String.valueOf;

import android.database.DataSetObserver;
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
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class fragment_index extends BaseFragment {

    private Spinner _spinner;
    private Button _BMIButton;
    private Button _staminaButton;
    private Button _MALButton;
    private Button  _KerdoButton;
    private Button _skibinskyButton;
    private Button _lifeIndexButton;
    private Button _FCIButton;
    private Button _heartRegulationButton;
    private Button _btnStart;
    private EditText _textAge;
    private ArrayList<String> _genders;

    public fragment_index() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_index, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _genders = new ArrayList();
        _genders.add("Мужской");
        _genders.add("Женский");


        _textAge = (EditText)view.findViewById(R.id.age);
        _MALButton = (Button)view.findViewById(R.id.btn_movement_level);
        _KerdoButton = (Button)view.findViewById(R.id.btn_kerdo);
        _skibinskyButton = (Button)view.findViewById(R.id.btn_skibinsky);
        _lifeIndexButton = (Button)view.findViewById(R.id.btn_life_index);
        _FCIButton = (Button)view.findViewById(R.id.btn_functional_changes);
        _heartRegulationButton = (Button)view.findViewById(R.id.btn_heart_regulation);

        _MALButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReplaceFragment(new MALFragment());
            }
        });

        _KerdoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReplaceFragment(new KerdoFragment());
            }
        });

        _skibinskyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReplaceFragment(new SkibinskyFragment());
            }
        });

        _lifeIndexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReplaceFragment(new LifeIndexFragment());
            }
        });

        _FCIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReplaceFragment(new FCIFragment());

            }
        });

        _heartRegulationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReplaceFragment(new HeartRegulationFragment());
            }
        });

        _btnStart = (Button)view.findViewById(R.id.btn_start);
        _btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReplaceFragment(new BMIFragment());

            }
        });

        _staminaButton = (Button)view.findViewById(R.id.btn_stamina);
        _staminaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReplaceFragment(new StaminaFragment());
            }
        });

        _BMIButton = (Button)view.findViewById(R.id.btn_index_mass);
        _BMIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ReplaceFragment(new BMIFragment());
            }
        });

        _spinner = (Spinner)view.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, _genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _spinner.setAdapter(adapter);
    }

}