package g05.info2051.ulg.timetable_t2.Other;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import g05.info2051.ulg.timetable_t2.MainActivity;
import g05.info2051.ulg.timetable_t2.R;

/*
*   This class manage app starts 3s
*   Finished
* */

public class Menu extends Activity {

    protected String TAG = "Menu";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        mHandler.sendEmptyMessageDelayed(GOTO_MAIN_ACTIVITY, 3000); //3s

    }

    protected final int GOTO_MAIN_ACTIVITY = 0;
    Handler mHandler = new Handler(new Handler.Callback() {

        public boolean handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case GOTO_MAIN_ACTIVITY:
                    Intent intent = new Intent();
                    intent.setClass(Menu.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                default:
                    break;
            }
            return true;

        }
    });

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "++ On Start ++");
    }

    @Override
    public synchronized void onResume() {
        super.onResume();
        Log.e(TAG , "++ On Resume ++");
    }


}
