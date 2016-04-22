package g05.info2051.ulg.timetable_t2.MenuSelect;


/*
*   This class can create a database for event,project. Finished
* */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class projectdb extends SQLiteOpenHelper {

    protected  String TAG = "EventDB";
    private final static String DATABASE_NAME = "Project.db";
    private final static int DATABASE_VERSION = 1;
    private final static String TABLE_NAME = "books_table";
    public final static String IT_ID = "it_id";
    public final static String NAME = "book_name";
    public final static String TIME = "time";

    public projectdb(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //创建table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + IT_ID
                + " INTEGER primary key autoincrement, " + NAME + " text, "+  TIME +" text);";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public Cursor select() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db
                .query(TABLE_NAME, null, null, null, null, null, null);
        return cursor;
    }
    //add
    public long insert(String name,String time)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        /* ContentValues */
        ContentValues cv = new ContentValues();
        cv.put(NAME, name);
        cv.put(TIME, time);
        long row = db.insert(TABLE_NAME, null, cv);
        Log.i(TAG, "Add Successed");
        return row;
    }
    //delete
    public void delete(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = IT_ID + " = ?";
        String[] whereValue ={ Integer.toString(id) };
        db.delete(TABLE_NAME, where, whereValue);
        Log.i(TAG, "Delete Successed");
    }
    //update
    public void update(int id, String name,String time)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = IT_ID + " = ?";
        String[] whereValue = { Integer.toString(id) };

        ContentValues cv = new ContentValues();
        cv.put(NAME, name);
        cv.put(TIME, time);
        db.update(TABLE_NAME, cv, where, whereValue);
        Log.i(TAG, "Update Successed");
    }
}
