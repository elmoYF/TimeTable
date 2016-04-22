package g05.info2051.ulg.timetable_t2.MenuSelect;


/*
*   handle event add, delete and update at list.  (finished) From projectdb
*
* */

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import g05.info2051.ulg.timetable_t2.R;

public class SQLprojtest extends Activity implements AdapterView.OnItemClickListener {

    protected String TAG = "Event";

    private projectdb mPROJDB;
    private Cursor mCursor;
    private EditText Name;
    private EditText Time;
    private ListView List;

    private int IT_ID = 0;
    protected final static int MENU_ADD = Menu.FIRST;
    protected final static int MENU_DELETE = Menu.FIRST + 1;
    protected final static int MENU_UPDATE = Menu.FIRST + 2;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.databs);
        setUpViews();
    }

    public void setUpViews(){
        mPROJDB = new projectdb(this);
        mCursor  = mPROJDB.select();

        Name = (EditText)findViewById(R.id.name);
        Time = (EditText)findViewById(R.id.time);
        List = (ListView)findViewById(R.id.list);

        List.setAdapter(new ListAdapter(this, mCursor));
        List.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(Menu.NONE, MENU_ADD, 0, "ADD");
        menu.add(Menu.NONE, MENU_DELETE, 0, "DELETE");
        menu.add(Menu.NONE, MENU_UPDATE, 0, "UPDATE");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
            case MENU_ADD:
                add();
                break;
            case MENU_DELETE:
                delete();
                break;
            case MENU_UPDATE:
                update();
                break;
        }
        return true;
    }

    public void add(){
        String name = Name.getText().toString();
        String time  = Time.getText().toString();
        //both textview cannot empty, else exit.
        if (name.equals("") || time.equals("")){
            return;
        }
        mPROJDB.insert(name, time);
        mCursor.requery();
        List.invalidateViews();
        Name.setText("");
        Time.setText("");
        Toast.makeText(this, "Add Successed!", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "Add new event.");
    }

    public void delete(){
        if (IT_ID == 0) {
            return;
        }
        mPROJDB.delete(IT_ID);
        mCursor.requery();
        List.invalidateViews();
        Name.setText("");
        Time.setText("");
        Toast.makeText(this, "Delete Successed!", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "Delete event.");
    }

    public void update(){
        String name = Name.getText().toString();
        String time  = Time.getText().toString();
        //both textview cannot empty, else exit.
        if (name.equals("") || time.equals("")){
            return;
        }
        mPROJDB.update(IT_ID, name, time);
        mCursor.requery();
        List.invalidateViews();
        Name.setText("");
        Time.setText("");
        Toast.makeText(this, "Update Successed!", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "update event.");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        mCursor.moveToPosition(position);
        IT_ID = mCursor.getInt(0);
        Name.setText(mCursor.getString(1));
        Time.setText(mCursor.getString(2));

    }

    public class ListAdapter extends BaseAdapter {
        private Context mContext;
        private Cursor mCursor;
        public ListAdapter(Context context,Cursor cursor) {

            mContext = context;
            mCursor = cursor;
        }
        @Override
        public int getCount() {
            return mCursor.getCount();
        }
        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView mTextView = new TextView(mContext);
            mCursor.moveToPosition(position);
            mTextView.setText(mCursor.getString(1) + "     " + mCursor.getString(2));
            return mTextView;
        }

    }
}
