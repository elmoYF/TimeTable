package g05.info2051.ulg.timetable_t2.MenuSelect;

/*
*   Add a new event to calendar (Finished)
* */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;

import java.util.Calendar;

import g05.info2051.ulg.timetable_t2.R;

public class CalendarTest extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calander_add);
    }

    public void onAddEventClicked(View view){
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");

        Calendar cal = Calendar.getInstance();
        long startTime = cal.getTimeInMillis();
        long endTime = cal.getTimeInMillis()  + 60 * 60 * 1000;

        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime);
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,endTime);
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

        intent.putExtra(CalendarContract.Events.TITLE, "Deadline OOP on mobile phone");
        intent.putExtra(CalendarContract.Events.DESCRIPTION,  "This is a sample description");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "2/92");
        intent.putExtra(CalendarContract.Events.RRULE, "FREQ=YEARLY");

        startActivity(intent);
    }


}