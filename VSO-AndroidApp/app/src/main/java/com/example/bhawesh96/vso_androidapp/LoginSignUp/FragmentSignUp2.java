package com.example.bhawesh96.vso_androidapp.LoginSignUp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bhawesh96.vso_androidapp.R;
import com.example.bhawesh96.vso_androidapp.Utils;

import static com.example.bhawesh96.vso_androidapp.LoginSignUp.FragmentSignUp1.person;

public class FragmentSignUp2 extends Fragment
{
    public static final String TAG = "SIGNUP_2";
    private View rootView;

    public FragmentSignUp2()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_sign_up_2, container, false);
        Spinner designationSpinner = (Spinner) rootView.findViewById(R.id.spinnerDes);
//        Spinner bgSpinner = (Spinner) rootView.findViewById(R.id.spinnerBg);
//        String[] bgOptions = getActivity().getResources().getStringArray(R.array.bloodGroup_options);
        String[] designationOptions = getActivity().getResources().getStringArray(R.array.designation_options);

        Utils.dropDownSetup(designationSpinner, getActivity(), designationOptions);
//        Utils.dropDownSetup(bgSpinner, getActivity(), bgOptions);

        rootView.findViewById(R.id.button_next2).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

//                rather than setting these 4 fields individually, a constructor can be made


                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right).replace(R.id.container_login, new FragmentSignUp3(), FragmentSignUp3.TAG).addToBackStack(TAG).commit();
            }
        });
        return rootView;
    }





}
