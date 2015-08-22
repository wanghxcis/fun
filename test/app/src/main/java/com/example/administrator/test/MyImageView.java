package com.example.administrator.test;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import org.w3c.dom.Attr;

/**
 * Created by Administrator on 2015/8/20.
 */
public class MyImageView extends ImageView{

    private final static String TAG = "MYPIC";

    private WindowManager mWm;
    private float mCurrentX;
    private float mCurrentY;
    private ViewGroup mParent;

    private float mFingerOffsetX;
    private float mFingerOffsetY;


    public void setParent(ViewGroup parent) {
        mParent = parent;
    }
    public MyImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context,attrs,defStyle);

    }


    public MyImageView(Context ctx, AttributeSet atts){
        this(ctx, atts, 0);
    }

    public MyImageView(Context ctx){
        this(ctx,null,0);
    }

    public void setWindowManager(WindowManager wm){
        mWm = wm;
    }
    public boolean onTouchEvent(MotionEvent event){
        float xx = event.getX();
        float yy = event.getY();
        float xRaw = event.getRawX();
        float yRaw = event.getRawY();

        Log.d(TAG,"myimage view touch " + event.toString());
        switch (event.getAction()) {
            case MotionEvent.ACTION_HOVER_ENTER:
                //Log.d(TAG, "myimage view action howver enter");
                break;
            case MotionEvent.ACTION_HOVER_EXIT:
                //Log.d(TAG, "myimage action hover exit");
                break;
            case MotionEvent.ACTION_HOVER_MOVE:
                //Log.d(TAG, "myimage action hover move");
                break;
            case MotionEvent.ACTION_DOWN:
                mCurrentX = xRaw;
                mCurrentY = yRaw;
                mFingerOffsetX = xx;
                mFingerOffsetY = yy;
                //Log.d(TAG, "myimage action down");
                break;
            case MotionEvent.ACTION_MOVE:
                Point offset = new Point();
                if(InParent(xRaw,yRaw, offset)) {

                    int newL = (int) (xRaw - mFingerOffsetX);
                    int newT = (int) (yRaw - mFingerOffsetY);

                    layout(newL-offset.x,newT-offset.y,newL-offset.x+getWidth(),newT-offset.y+getHeight());
                }
               // Log.d(TAG, "myimage action move");

                break;
            case MotionEvent.ACTION_UP:
                //Log.d(TAG, "myimage action up");

                break;

        }

        //Log.d(TAG, "getX() " + xx + " getY() " + yy + " getRawX() " + xRaw + " getRawY() " + yRaw);
        return true;
    }

    // getRawx(0, scrren coordinate
    private boolean InParent(float x, float y, Point off) {
        Rect parentRect = new Rect();

        boolean  visibileArea  = mParent.getGlobalVisibleRect(parentRect,off);
        if(!visibileArea) {
            return false;
        }

        return parentRect.contains((int)x, (int)y);
    }
}
