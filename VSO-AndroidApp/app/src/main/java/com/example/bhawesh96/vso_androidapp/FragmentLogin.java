package com.example.bhawesh96.vso_androidapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FragmentLogin extends Fragment {
    public FragmentLogin() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        final AppCompatEditText edit_pass = (AppCompatEditText) rootView.findViewById(R.id.edit_pass);
        final ImageView shide = (ImageView) rootView.findViewById(R.id.icon_shide);
        shide.setTag(false);
        shide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(boolean) shide.getTag()) {
                    shide.setImageResource(R.drawable.ic_hide_24dp);
                    edit_pass.setTransformationMethod(null);
                } else {
                    shide.setImageResource(R.drawable.ic_show_24dp);
                    edit_pass.setTransformationMethod(new PasswordTransformationMethod());
                }
                edit_pass.setSelection(edit_pass.length());
                shide.setTag(!(boolean) shide.getTag());
            }
        });
        return rootView;
    }
}
