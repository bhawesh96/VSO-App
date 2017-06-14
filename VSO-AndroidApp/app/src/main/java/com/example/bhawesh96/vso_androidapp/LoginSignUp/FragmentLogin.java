package com.example.bhawesh96.vso_androidapp.LoginSignUp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bhawesh96.vso_androidapp.Post;
import com.example.bhawesh96.vso_androidapp.R;

public class FragmentLogin extends Fragment
{
    public static final String TAG = "LOGIN";
    public FragmentLogin()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        Button btn_login = (Button)rootView.findViewById(R.id.button_login);

        btn_login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), Post.class);
                startActivity(intent);
            }
        });

        final AppCompatEditText edit_pass = (AppCompatEditText) rootView.findViewById(R.id.edit_pass);
        final ImageView shide = (ImageView) rootView.findViewById(R.id.icon_shide);
        shide.setTag(false);
        shide.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!(boolean) shide.getTag())
                {
                    shide.setImageResource(R.drawable.ic_hide_24dp);
                    edit_pass.setTransformationMethod(null);
                }
                else
                {
                    shide.setImageResource(R.drawable.ic_show_24dp);
                    edit_pass.setTransformationMethod(new PasswordTransformationMethod());
                }
                edit_pass.setSelection(edit_pass.length());
                shide.setTag(!(boolean) shide.getTag());
            }
        });

        TextView to_signup = (TextView)rootView.findViewById(R.id.to_signup);
        to_signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right).replace(R.id.container_login, new FragmentSignUp1(), FragmentSignUp1.TAG).addToBackStack(TAG).commit();
            }
        });
        return rootView;
    }
}
