package g05.info2051.ulg.timetable_t2.CourseTable;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;



public class TimeTableDB extends SQLiteOpenHelper{

    private static final String DATABASE_NAME="events.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="events";
    public final static String IT_ID = "it_id";
    public final static String NAME = "coursename";
    public final static String LOCATION = "location";
    public final static String PROFESSOR = "professor";
    public final static String WEEK = "week";
    public final static String STARTTIME = "starttime";
    public final static String ENDTIME = "endtime";

    public TimeTableDB(Context ctx)
    {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db)
    {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + IT_ID
                + " INTEGER primary key autoincrement, " + NAME + " text, "+ LOCATION + " text, "+ PROFESSOR + " text, "+ WEEK + " text, "+ STARTTIME + " text, "+  ENDTIME +" text);";
        db.execSQL(sql);
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void insert(ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<TimeTableModel> getCourseList(String coursename){
        List<TimeTableModel> TimeTableMode = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM coursename = ?", new String[]{coursename});

        while (c.moveToNext()){
            TimeTableModel Model =new TimeTableModel();
            Model.setName(c.getString(c.getColumnIndex("cousename")));
            Model.setTeacher(c.getString(c.getColumnIndex("professor")));
            Model.setClassroom(c.getString(c.getColumnIndex("location")));
            Model.setStarttime(c.getString(c.getColumnIndex("Starttime")));
            Model.setEndtime(c.getString(c.getColumnIndex("Endtime")));
            Model.setWeek(c.getInt(c.getColumnIndex("week")));
        }

        c.close();

        return TimeTableMode;
    }
    public Cursor query() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        return c;
    }
    public void close() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null)
            db.close();
    }
}
