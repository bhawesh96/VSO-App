package com.example.bhawesh96.vso_androidapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bhawesh96 on 8/6/17.
 */

public class ProjectList extends ArrayAdapter<Project> {

    private Activity context;
    private List<Project> projectList;

    public ProjectList(Activity context, List<Project> projectList) {
        super(context, R.layout.inflater_view_project, projectList);
        this.context = context;
        this.projectList = projectList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View view = inflater.inflate(R.layout.inflater_view_project, null, true);

        TextView textView_title = (TextView) view.findViewById(R.id.textView_title);
        TextView textView_desc = (TextView) view.findViewById(R.id.textView_desc);

        Project project = projectList.get(position);

        textView_title.setText(project.getTitle());
        textView_desc.setText(project.getDesc());

        return view;
    }
}
