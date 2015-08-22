package com.example.administrator.test;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends Activity {
    private Handler mHandler;
    private TextView myview;
    private RelativeLayout myGroup;
    private WindowManager mWindowManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myGroup = (RelativeLayout)findViewById(R.id.group);

        mWindowManager = (WindowManager)getApplicationContext().getSystemService(WINDOW_SERVICE);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Paint paint = new Paint();
        switch (item.getItemId()){
            case R.id.menu_edit:
                paint.setColor(Color.RED);
                paint.setStrokeWidth(3);
                break;
            case R.id.menu_erase:
                paint.setColor(Color.WHITE);
                paint.setStrokeWidth(12);
                break;
            case R.id.menu_add:
                MyImageView newImage = new MyImageView(this);
                newImage.setWindowManager(mWindowManager);
                newImage.setImageResource(R.drawable.menu_edit);
                newImage.setParent(myGroup);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                myGroup.addView(newImage,params);
                newImage.setVisibility(View.VISIBLE);
                break;
        }
        MyPicture mypic = (MyPicture)findViewById(R.id.mypic);
        mypic.setPaint(paint);





        return super.onOptionsItemSelected(item);
    }
}
