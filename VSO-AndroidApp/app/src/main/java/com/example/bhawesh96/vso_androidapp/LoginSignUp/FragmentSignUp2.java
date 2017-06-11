package com.example.bhawesh96.vso_androidapp.LoginSignUp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bhawesh96.vso_androidapp.R;

public class FragmentSignUp2 extends Fragment
{
    public static final String TAG = "SIGNUP_2";
    private View rootView;

    public FragmentSignUp2()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.fragment_sign_up_2, container, false);
        setupDesignationAdapter();

        return rootView;
    }

    private void setupDesignationAdapter()
    {
        Spinner designationSpinner = (Spinner)rootView.findViewById(R.id.spinnerDes);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item)
        {

            @Override
            @NonNull
            public View getView(int position, View convertView, @NonNull ViewGroup parent)
            {

                View v = super.getView(position, convertView, parent);
                if (position == getCount())
                {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                    ((TextView)v.findViewById(android.R.id.text1)).setHintTextColor(Color.parseColor("#FFFFFF"));
                }

                return v;
            }

            @Override
            public int getCount()
            {
                return super.getCount()-1; // you don't display last item. It is used as hint.
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.addAll(getActivity().getResources().getStringArray(R.array.designation_options));

        designationSpinner.setAdapter(adapter);
        designationSpinner.setSelection(adapter.getCount()); //set the hint the default selection so it appears on launch.
        designationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                ((TextView)view).setTextColor(Color.parseColor("#FFFFFF"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
