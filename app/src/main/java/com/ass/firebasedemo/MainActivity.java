package com.ass.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText etSid,etSname,etSage,etScourse;

    Button btnadd,btnShow;
    ListView listViewStudent;
    TextView tv;
    ArrayList<Student> Students;
    DatabaseReference databaseStudent;

    private static final String StudentName
            ="com.ass.firebasedemo.studentname ";
    private static final String authorId
            ="com.ass.firebasedemo.studentid";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseStudent= FirebaseDatabase.getInstance().getReference("Students");


        etSname=findViewById(R.id.etSname);
        etSage=findViewById(R.id.etSage);
        etScourse=findViewById(R.id.etSCourse);
        Students=new ArrayList<Student>();

        btnShow=findViewById(R.id.btnShow);
        btnadd=findViewById(R.id.btnAddStudent);
        listViewStudent=findViewById(R.id.listview);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String sid=etSid.getText().toString();
                String sname=etSname.getText().toString();
                String sage=etSage.getText().toString();
                String scourse=etScourse.getText().toString();

                if( (!TextUtils.isEmpty(sname)) || (!TextUtils.isEmpty(sage)) || (!TextUtils.isEmpty(scourse)))
                {
                    String studid=databaseStudent.push().getKey();
                    Student s=new Student(studid,sname,sage,scourse);
                    databaseStudent.child(studid).setValue(s);


                    etSname.setText("");
                    etSage.setText("");
                    etScourse.setText("");

                    Toast.makeText(MainActivity.this,"Student Details added successfully",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"An Error Occured!!",Toast.LENGTH_LONG).show();

                }
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,DisplayStudent.class);
                startActivity(i);
            }
        });



    }


}