package com.example.sennhvi.adatechooserdemo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private Button btnChooseDate;

    private Button btnChooseTime;

    private RadioButton rdBtnA;

    private Button btnSubmit;

    private CheckBox cbA, cbB, cbC, cbD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChooseDate = (Button) findViewById(R.id.btn_choose_date);
        btnChooseTime = (Button) findViewById(R.id.btn_choose_time);
        rdBtnA = (RadioButton) findViewById(R.id.bd_a);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        cbA = (CheckBox) findViewById(R.id.cb_a);
        cbB = (CheckBox) findViewById(R.id.cb_b);
        cbC = (CheckBox) findViewById(R.id.cb_c);
        cbD = (CheckBox) findViewById(R.id.cb_d);
        cbA.setOnCheckedChangeListener(this);
        cbB.setOnCheckedChangeListener(this);
        cbC.setOnCheckedChangeListener(this);
        cbD.setOnCheckedChangeListener(this);

        btnChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, final int year, final int month, final int dayOfMonth) {
                        String dateStr = String.format("%d-%d-%d", year, month + 1, dayOfMonth);
                        btnChooseDate.setText(dateStr);
                    }
                },2017, 3, 30).show();
            }
        });
        btnChooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String timeStr = String.format("%d:%d", hourOfDay, minute);
                        btnChooseTime.setText(timeStr);
                    }
                }, 10, 10, false).show(); //false is not 24-hour clock
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rdBtnA.isChecked()) {
                    Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String str = "checked:";
        if (cbA.isChecked()) {
            str += cbA.getText().toString() + ",";
        }
        if (cbB.isChecked()) {
            str += cbB.getText().toString() + ",";
        }
        if (cbC.isChecked()) {
            str += cbC.getText().toString() + ",";
        }
        if (cbD.isChecked()) {
            str += cbD.getText().toString();
        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();


    }
}
