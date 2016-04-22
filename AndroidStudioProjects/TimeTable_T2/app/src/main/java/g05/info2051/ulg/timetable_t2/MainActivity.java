package g05.info2051.ulg.timetable_t2;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

import g05.info2051.ulg.timetable_t2.CourseTable.TimeTableDB;
import g05.info2051.ulg.timetable_t2.CourseTable.TimeTableModel;
import g05.info2051.ulg.timetable_t2.CourseTable.TimeTableView;
import g05.info2051.ulg.timetable_t2.MenuSelect.CalendarTest;
import g05.info2051.ulg.timetable_t2.MenuSelect.NewCourse;
import g05.info2051.ulg.timetable_t2.MenuSelect.SQLprojtest;
import g05.info2051.ulg.timetable_t2.MenuSelect.ScreenshotUtil;

public class MainActivity extends Activity {
    private String TAG = "submit";
    private TimeTableView mTimaTableView;
    private List<TimeTableModel> mList;
    private TimeTableDB TimeTableDB;
    private String NAME = null;

    Intent intent = getIntent();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = new ArrayList<TimeTableModel>();

        mTimaTableView = (TimeTableView) findViewById(R.id.main_timetable_ly);

        addList();
        mTimaTableView.setTimeTable(mList);


    }



    private void addList() {

        mList.add(new TimeTableModel(0, 3, 4, 1, "8:20", "10:10", "审计实务",
                "李老师", "2"));
        mList.add(new TimeTableModel(0, 6, 7, 1, "8:20", "10:10", "市场营销实务",
                "王", "3"));
        mList.add(new TimeTableModel(0, 6, 7, 2, "8:20", "10:10", "财务管理实务",
                "老师1", "4"));
        mList.add(new TimeTableModel(0, 8, 9, 2, "8:20", "10:10", "财务报表分析",
                "老师2", "5"));
        mList.add(new TimeTableModel(0, 1, 2, 3, "8:20", "10:10", "审计实务",
                "老师3", "6"));
        mList.add(new TimeTableModel(0, 6, 7, 3, "8:20", "10:10", "管理会计实务",
                "老师4", "7"));
        mList.add(new TimeTableModel(0, 3, 5, 4, "8:20", "10:10", "财务管理实务",
                "老师4", "8"));
        mList.add(new TimeTableModel(0, 8, 9, 4, "8:20", "10:10", "管理会计实务",
                "老师5", "9"));
        mList.add(new TimeTableModel(0, 3, 5, 5, "8:20", "10:10", "税务筹划",
                "老师6", "10"));
        mList.add(new TimeTableModel(0, 6, 8, 5, "8:20", "10:10", "证券投资分析",
                "老师7", "11"));

    }

    public boolean onCreateOptionsMenu(Menu menu) {
/*
		Inflate the menu; this adds items to the action bar if it is present. Showing, after press menu button.
*/
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }

    // Menu button select

    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {

            case R.id.addCourse:
                Intent i = new Intent(MainActivity.this, NewCourse.class);
                this.startActivity(i);
                return true;
            case R.id.databs:
                Intent i_p = new Intent(MainActivity.this, SQLprojtest.class);
                this.startActivity(i_p);
                return true;
            case R.id.calendarView:
                Intent i_c = new Intent(MainActivity.this, CalendarTest.class);
                this.startActivity(i_c);
                return true;
            case R.id.main_screenshot:
                Intent i_s = new Intent(MainActivity.this, ScreenshotUtil.class);
                this.startActivity(i_s);
                return true;
            case R.id.test_quit:
                finish();
                return true;


            default:
                break;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    // Android life circle

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "++ On Start ++");
    }

    @Override
    public synchronized void onResume() {
        super.onResume();
        Log.e(TAG, "++ On Resume ++");
    }

}