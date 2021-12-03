package com.example.courseregistration1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView lblHours,lblFees,lblTotalFees,lblTotalHours;
    EditText edtName;
    RadioButton rbGraduate,rbUnderGraduate;
    CheckBox ckbMedicalInsurance,ckbAccomodation;
    Button btnCalculate,btnAdd;
    Spinner spinnerCourse;
    ArrayList<Course> courses=new ArrayList<>();
    ArrayList<Course> myCourses=new ArrayList<>();
    ArrayList<String> courseNames=new ArrayList<>();
    Course myCourse;
    int MaxHours=0;
    int myTotalHours=0;
    int myHour=0;
    int myFees=0;
    int totalFees=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lblHours=findViewById(R.id.lblHours);
        lblFees=findViewById(R.id.lblFees);
        lblTotalFees=findViewById(R.id.lblTotalFees);
        lblTotalHours=findViewById(R.id.lblTotalHours);
        edtName=findViewById(R.id.edtName);
        rbGraduate=findViewById(R.id.rbGraduate);
        rbUnderGraduate=findViewById(R.id.rbUnderGraduate);
        ckbMedicalInsurance=findViewById(R.id.ckbMedicalInsurance);
        ckbAccomodation=findViewById(R.id.ckbAccomodation);
        btnCalculate=findViewById(R.id.btnCalculate);
        btnAdd=findViewById(R.id.btnAdd);
        spinnerCourse=findViewById(R.id.spinnerCourse);
        fillData();
        rbGraduate.setOnClickListener(new RadioButtonEvent());
        rbUnderGraduate.setOnClickListener(new RadioButtonEvent());
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MaxHours==0||edtName.getText().toString().equals("")) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setMessage("Please Fill The above Form");
                    alertDialogBuilder.setNeutralButton("Ok",null);
                    alertDialogBuilder.show();
                }
                else if(MaxHours!=0){
                    if(myHour+myTotalHours<MaxHours&&!myCourses.contains(myCourse)){
                        myTotalHours=myTotalHours+myHour;
                        totalFees=totalFees+myFees;
                        myCourses.add(myCourse);
                        Toast.makeText(MainActivity.this, "Course Added Succeessfully", Toast.LENGTH_SHORT).show();
                        lblTotalHours.setText(Integer.toString(myTotalHours));
                        lblTotalFees.setText(Integer.toString(totalFees));
                    }

                    else if(myHour+myTotalHours>MaxHours&&myCourses.contains(myCourse)){
                        Toast.makeText(MainActivity.this, "Course Can not be added", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Course already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ckbAccomodation.isChecked()){
                    totalFees=totalFees+1000;
                }
                if(ckbMedicalInsurance.isChecked()){
                    totalFees=totalFees+700;
                }
                AlertDialog.Builder alertBuilder=new AlertDialog.Builder(MainActivity.this);
                alertBuilder.setMessage("Total fees: $"+totalFees);
                alertBuilder.setNeutralButton("Ok",null);
                alertBuilder.show();
            }
        });
        ArrayAdapter pickerAdapter=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,courseNames);
        spinnerCourse.setAdapter(pickerAdapter);
        spinnerCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                lblFees.setText(Integer.toString(courses.get(position).getFees()));
                myHour=courses.get(position).getHoursPerWeek();
                myFees=courses.get(position).getFees();
                myCourse=courses.get(position);
                lblHours.setText(Integer.toString(courses.get(position).getHoursPerWeek()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    private void fillData() {
        courses.add(new Course(1,"Java",6,1300));
        courses.add(new Course(2,"Swift",5,1500));
        courses.add(new Course(3,"IOS",5,1350));
        courses.add(new Course(4,"Android",7,1400));
        courses.add(new Course(5,"DataBase",4,1000));
        for(Course c:courses){
            courseNames.add(c.getCourse());
        }
    }
    private class RadioButtonEvent implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.rbGraduate:
                    rbUnderGraduate.setChecked(false);
                    MaxHours=21;
                    break;
                case R.id.rbUnderGraduate:
                    rbGraduate.setChecked(false);
                    MaxHours=19;
                    break;
            }
        }
    }
}