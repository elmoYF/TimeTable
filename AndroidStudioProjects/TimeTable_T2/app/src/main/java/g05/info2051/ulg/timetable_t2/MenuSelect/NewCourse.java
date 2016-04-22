package g05.info2051.ulg.timetable_t2.MenuSelect;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import g05.info2051.ulg.timetable_t2.CourseTable.TimeTableDB;
import g05.info2051.ulg.timetable_t2.MainActivity;
import g05.info2051.ulg.timetable_t2.R;


/*
*  This class is for add new course, input String.
*
* */

public class NewCourse extends Activity implements View.OnTouchListener {


    private static final String TAG = "AddCourse";

    protected EditText coursename;
    protected EditText location;
    protected EditText professor;


    protected EditText StartTime;
    protected EditText EndTime;

    protected EditText week;

    protected Button Okay;
    protected Button Cancle;

    private int Start = 0;
    private int End = 0;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new);


        coursename = (EditText) findViewById(R.id.editText_CourseName);
        location = (EditText) findViewById(R.id.editText_classroom);
        professor = (EditText) findViewById(R.id.editText_prof);
        week = (EditText) findViewById(R.id.editText_week);

        StartTime = (EditText) findViewById(R.id.TimeFromedit);
        EndTime = (EditText)findViewById(R.id.TimeToedit);

        Okay = (Button) findViewById(R.id.btnok);
        Cancle = (Button) findViewById(R.id.btncancle);


        StartTime.setOnTouchListener(this);
        EndTime.setOnTouchListener(this);

        Okay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String COURSE = coursename.getText().toString();
                Intent intent = new Intent();
                ContentValues values = new ContentValues();
                values.put("coursename", COURSE);
                values.put("professor", professor.getText().toString());
                values.put("location", location.getText().toString());
                values.put("StartTime", StartTime.getText().toString());
                values.put("EndTime", EndTime.getText().toString());
                values.put("week", week.getText().toString());
                TimeTableDB DBTime = new TimeTableDB(getApplicationContext());
                DBTime.insert(values);


                Intent ii=new Intent(NewCourse.this, MainActivity.class);
                ii.putExtra("name", COURSE);
                startActivity(ii);
            }
        });



        Cancle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg1) {

            }
        });


    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = View.inflate(this, R.layout.date_time_dialog, null);
            final TimePicker timePicker = (android.widget.TimePicker) view.findViewById(R.id.time_picker);
            builder.setView(view);

            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(System.currentTimeMillis());

            timePicker.setIs24HourView(true);
            timePicker.setCurrentHour(cal.get(Calendar.HOUR_OF_DAY));
            timePicker.setCurrentMinute(Calendar.MINUTE);

        if (v.getId() == R.id.TimeFromedit) {
                final int inType = StartTime.getInputType();
                StartTime.setInputType(InputType.TYPE_NULL);
                StartTime.onTouchEvent(event);
                StartTime.setInputType(inType);
        StartTime.setSelection(StartTime.getText().length());

                builder.setTitle("Time From");
        builder.setPositiveButton("Ok",new DialogInterface.OnClickListener(){

        @Override
          public void onClick(DialogInterface dialog,int starttime){

              StringBuffer sb=new StringBuffer();

              sb.append("  ");
              sb.append(timePicker.getCurrentHour())
               .append(":").append(timePicker.getCurrentMinute());


              Start = starttime;


              StartTime.setText(sb);
              EndTime.requestFocus();

              dialog.cancel();
                   }
                });

            } else if (v.getId() == R.id.TimeToedit) {
                int inType = EndTime.getInputType();
                EndTime.setInputType(InputType.TYPE_NULL);
                EndTime.onTouchEvent(event);
                EndTime.setInputType(inType);
                EndTime.setSelection(EndTime.getText().length());

                builder.setTitle("Time To");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        StringBuffer sb = new StringBuffer();

                        sb.append("  ");
                        sb.append(timePicker.getCurrentHour())
                                .append(":").append(timePicker.getCurrentMinute());
                        EndTime.setText(sb);

                        dialog.cancel();
                    }
                });
            }

            Dialog dialog = builder.create();
            dialog.show();
        }

        return true;
    }

    public static long GetLongFromDate(Date date)
    {
        return date.getTime();
    }


}
