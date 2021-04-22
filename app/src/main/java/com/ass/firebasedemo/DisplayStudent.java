package com.ass.firebasedemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DisplayStudent extends AppCompatActivity {

    ArrayList<Student> Students;
    DatabaseReference databaseStudent;
    ListView listViewStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_student);

        listViewStudent=findViewById(R.id.listview);
        Students=new ArrayList<Student>();
        databaseStudent= FirebaseDatabase.getInstance().getReference("Students");


    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseStudent.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Students.clear();
                for(DataSnapshot postsnapshot : snapshot.getChildren())
                {
                    Student s=postsnapshot.getValue(Student.class);
                    Students.add(s);

                    StudentList studentAdapter=new StudentList(DisplayStudent.this,Students);
                    listViewStudent.setAdapter(studentAdapter);

                    listViewStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
                    {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
                        {
                            Student s=Students.get(position);
                            showDialog(s.getSid(),s.getSname(),s.getSage(),s.getCourse());
                            return true;
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public boolean updateStudent(final String sid, String uname,String uage,String ucourse){
        //getting the specified student reference
        DatabaseReference dr=FirebaseDatabase.getInstance().getReference("Students").child(sid);
        //updating Student
        Student s= new Student(sid,uname, uage,ucourse);
        dr.setValue(s);
        Toast.makeText(this, "Student Updated Successfully ",Toast.LENGTH_SHORT).show();
        return true;
    }
    public void showDialog(final String sid,String sname,String sage,String scourse){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        LayoutInflater inflater= getLayoutInflater();
        final View v= inflater.inflate(R.layout.update_delete_dailog,null);
        builder.setView(v);


        final EditText etUpdateName=v.findViewById(R.id.etUpdateName);
        final EditText etUpdateAge=v.findViewById(R.id.etUpdateAge);
        final EditText etUpdateCourse=v.findViewById(R.id.etUpdateCourse);
        final Button btnUpdate=v.findViewById(R.id.btnUpdateStudent);
        final Button btnDelete=v.findViewById(R.id.btnDeleteStudent);

        builder.setTitle("Student Data");
        final AlertDialog alert=builder.create();
        alert.show();
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String sid=etUpdateId.getText().toString();
                String sname=etUpdateName.getText().toString();
                String sage=etUpdateAge.getText().toString();
                String scourse=etUpdateCourse.getText().toString();

                if((!TextUtils.isEmpty(sid)) || (!TextUtils.isEmpty(sname)) || (!TextUtils.isEmpty(sage)) || (!TextUtils.isEmpty(scourse)))
                {
                    updateStudent(sid,sname,sage,scourse);
                    alert.dismiss();
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String sid=etUpdateId.getText().toString();
                DatabaseReference dr=FirebaseDatabase.getInstance().getReference("Students").child(sid);
                dr.removeValue();
                Toast.makeText(DisplayStudent.this, "Record Deleted", Toast.LENGTH_LONG).show();
                alert.dismiss();
            }
        });
    }
}
