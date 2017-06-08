package com.example.bhawesh96.vso_androidapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddProject extends AppCompatActivity {

    EditText editText_title, editText_desc;
    ToggleButton toggleButton_type;
    Button button_add, button_view;
    String type;

    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        editText_title = (EditText) findViewById(R.id.editText_title);
        editText_desc = (EditText) findViewById(R.id.editText_desc);
        toggleButton_type = (ToggleButton) findViewById(R.id.toggleButton_type);
        button_add = (Button) findViewById(R.id.button_add);
        button_view = (Button) findViewById(R.id.button_view);

        toggleButton_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = toggleButton_type.isChecked() ? "Weekly" : "Special";
                toggleButton_type.setText(type);
            }
        });

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProject();
            }
        });

        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddProject.this, ViewProject.class));
            }
        });
    }

    private void addProject() {
        String title = editText_title.getText().toString().trim();
        String desc = editText_desc.getText().toString().trim();

        myRef = FirebaseDatabase.getInstance().getReference("project");

        String id = myRef.push().getKey();

        myRef.child(id).setValue(new Project(id, type, title, desc), new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    Toast.makeText(getApplicationContext(),"Error" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Saved", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
