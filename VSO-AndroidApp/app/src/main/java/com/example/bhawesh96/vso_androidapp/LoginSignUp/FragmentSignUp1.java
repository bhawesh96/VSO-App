package com.example.bhawesh96.vso_androidapp.LoginSignUp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bhawesh96.vso_androidapp.Person;
import com.example.bhawesh96.vso_androidapp.R;
import com.example.bhawesh96.vso_androidapp.Utils;

public class FragmentSignUp1 extends Fragment
{
    public static final String TAG = "SIGNUP_1";
    public static Person person = new Person();

    public FragmentSignUp1()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_sign_up_1, container, false);
        final AppCompatEditText edit_fname = (AppCompatEditText) rootView.findViewById(R.id.edit_fname);
        final AppCompatEditText edit_lname = (AppCompatEditText) rootView.findViewById(R.id.edit_lname);
        final AppCompatEditText edit_email = (AppCompatEditText) rootView.findViewById(R.id.edit_email);
        final AppCompatEditText edit_contact = (AppCompatEditText) rootView.findViewById(R.id.edit_contact);

        rootView.findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

//                rather than setting these 4 fields individually, a constructor can be made
                person.setFirstName(edit_fname.getText().toString());
                person.setLastName(edit_lname.getText().toString());
                person.setEmail(edit_email.getText().toString());
                person.setContact(edit_contact.getText().toString());

                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right).replace(R.id.container_login, new FragmentSignUp2(), FragmentSignUp2.TAG).addToBackStack(TAG).commit();
            }
        });

        return rootView;
    }
}
