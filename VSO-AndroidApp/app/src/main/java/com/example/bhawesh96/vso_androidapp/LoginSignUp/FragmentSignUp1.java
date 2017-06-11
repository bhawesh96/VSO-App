package com.example.bhawesh96.vso_androidapp.LoginSignUp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bhawesh96.vso_androidapp.R;

public class FragmentSignUp1 extends Fragment
{
    public static final String TAG = "SIGNUP_1";

    public FragmentSignUp1()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_sign_up_1, container, false);
        rootView.findViewById(R.id.button_next).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right).replace(R.id.container_login, new FragmentSignUp2(), FragmentSignUp2.TAG).addToBackStack(TAG).commit();
            }
        });

        return rootView;
    }
}
