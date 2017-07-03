package com.example.bhawesh96.vso_androidapp.LoginSignUp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.bhawesh96.vso_androidapp.R;
import com.example.bhawesh96.vso_androidapp.Utils;

import static com.example.bhawesh96.vso_androidapp.LoginSignUp.FragmentSignUp1.person;

public class FragmentSignUp4 extends Fragment
{
    public static final String TAG = "SIGNUP_4";
    private View rootView;

    public FragmentSignUp4()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_sign_up_3, container, false);
        final EditText pres_address = (EditText)rootView.findViewById(R.id.edit_presAddress);
        final EditText perm_address = (EditText)rootView.findViewById(R.id.edit_permAddress);
        CheckBox perm_same_as_pres = (CheckBox)rootView.findViewById(R.id.perm_same_as_pres);
        final EditText nationality = (EditText)rootView.findViewById(R.id.edit_nationality);

        rootView.findViewById(R.id.button_next2).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                person.setPresentAddress(pres_address.getText().toString());
                person.setPermAddress(perm_address.getText().toString());
                person.setNationality(nationality.getText().toString());
//                rather than setting these 4 fields individually, a constructor can be made


                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right).replace(R.id.container_login, new FragmentSignUp4(), FragmentSignUp4.TAG).addToBackStack(TAG).commit();
            }
        });
        return rootView;
    }





}
