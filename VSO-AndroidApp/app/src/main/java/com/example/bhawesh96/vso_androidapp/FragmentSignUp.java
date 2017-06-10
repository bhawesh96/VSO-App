package com.example.bhawesh96.vso_androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class FragmentSignUp extends Fragment implements View.OnClickListener {
    public FragmentSignUp() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ImageView shide1 = (ImageView) rootView.findViewById(R.id.icon_shide1);
        ImageView shide2 = (ImageView) rootView.findViewById(R.id.icon_shide2);
        Button button_next = (Button) rootView.findViewById(R.id.button_next);
        shide1.setTag(false);
        shide2.setTag(false);
        shide1.setOnClickListener(setListener((AppCompatEditText) rootView.findViewById(R.id.edit_pass), shide1));
        shide2.setOnClickListener(setListener((AppCompatEditText) rootView.findViewById(R.id.edit_cpass), shide2));
        button_next.setOnClickListener(this);
        return rootView;
    }

    private void setListener(Button button_next) {
//        return new FragmentSignUpNext();
    }

    private View.OnClickListener setListener(final AppCompatEditText editText, final ImageView shide) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(boolean) shide.getTag()) {
                    shide.setImageResource(R.drawable.ic_hide_24dp);
                    editText.setTransformationMethod(null);
                } else {
                    shide.setImageResource(R.drawable.ic_show_24dp);
                    editText.setTransformationMethod(new PasswordTransformationMethod());
                }
                editText.setSelection(editText.length());
                shide.setTag(!(boolean) shide.getTag());
            }
        };
    }

    @Override
    public void onClick(View v) {

    }
}
