package com.example.bhawesh96.vso_androidapp;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewProject extends AppCompatActivity {

    ListView listView_viewProject;
    List<Project> projectList;
    DatabaseReference db;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_project);
        listView_viewProject = (ListView) findViewById(R.id.listView_viewProject);
        projectList = new ArrayList<>();
        db = FirebaseDatabase.getInstance().getReference("project");

        listView_viewProject.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Project project = projectList.get(position);
                showUpdateDialog(project.getId(), project.getTitle());
                return false;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        getData();
    }



    private void getData() {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

//                to be cleared as it may contain any projetc previously because the dataSnapshot
//                object will contain every projct each time it is executed
                projectList.clear();

//                iterate through all projects
                for(DataSnapshot projectSnapshot : dataSnapshot.getChildren()) {
                    Project project = projectSnapshot.getValue(Project.class);

                    projectList.add(project);
                }

                ProjectList adapter = new ProjectList(ViewProject.this, projectList);
                listView_viewProject.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showUpdateDialog(final String id, String title) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.dialog_update_project, null, true);

        dialogBuilder.setView(dialogView);

        final EditText editText_newTitle = (EditText) dialogView.findViewById(R.id.editText_newTitle);
        TextView textView_currentTitle = (TextView) dialogView.findViewById(R.id.textView_currentTitle);
        Button button_update = (Button) dialogView.findViewById(R.id.button_updateProject);

        dialogBuilder.setTitle("Update Project");
        textView_currentTitle.setText(title);

        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTitle = editText_newTitle.getText().toString().trim();
                updateProject(id, newTitle);
                alertDialog.dismiss();
            }
        });

        alertDialog = dialogBuilder.create();
        alertDialog.show();
    }

    private void updateProject(String id, String title) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("project/"+id).child("title");
//        Project project = new Project(id, title);
//        db = db.child("");
        db.setValue(title, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    Toast.makeText(getApplicationContext(),"Error" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
