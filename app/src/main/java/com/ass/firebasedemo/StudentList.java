package com.ass.firebasedemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class StudentList extends ArrayAdapter<Student> {
    Activity context;
    ArrayList<Student> students;


    public StudentList(@NonNull Activity context, ArrayList<Student> students) {
        super(context, R.layout.layout_student_list,students);
        this.context=context;
        this.students=students;

    }

    public View getView(int position, @Nullable View
            convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View V=
                inflater.inflate(R.layout.layout_student_list,null, true);
        TextView tvSid=V.findViewById(R.id.tvSid);
        TextView tvSname=V.findViewById(R.id.tvSname);
        TextView tvSage=V.findViewById(R.id.tvSage);
        TextView tvScourse=V.findViewById(R.id.tvScourse);

        Student author=students.get(position);
        tvSid.setText(author.getSid());
        tvSname.setText(author.getSname());
        tvSage.setText(author.getSage());
        tvScourse.setText(author.getCourse());
        return V;
    }
}

