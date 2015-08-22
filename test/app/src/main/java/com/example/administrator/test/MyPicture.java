package com.example.administrator.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2015/8/19.
 */
public class MyPicture  extends View {

    private final static String TAG = "MYPIC";
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mPaint;
    private float mCurrentX;
    private float mCurrentY;
    private float mLastX;
    private float mLastY;


    public MyPicture(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        int viewWidth = this.getWidth();
        int viewHeight = this.getHeight();

        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(3);


    }


    public void setPaint(Paint paint){
        mPaint = paint;
    }

    public void onLayout(boolean changed, int left, int top, int right, int bottom){
        if(changed){
            mBitmap = Bitmap.createBitmap(right-left,bottom-top, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmap);
        }
    }

    public MyPicture(Context context) {
        this(context, null);
    }


    public MyPicture(Context context,AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawBitmap(mBitmap,0,0,null);
    }

    public boolean onTouchEvent(MotionEvent event){
        float xx = event.getX();
        float yy = event.getY();
        float xRaw = event.getRawX();
        float yRaw = event.getRawY();
        Log.d(TAG,"mypic view touch " + event.toString());
        switch (event.getAction()){
            case MotionEvent.ACTION_HOVER_ENTER:
                //Log.d(TAG,"action howver enter");
                break;
            case MotionEvent.ACTION_HOVER_EXIT:
                //Log.d(TAG,"action hover exit");
                break;
            case MotionEvent.ACTION_HOVER_MOVE:
                //Log.d(TAG,"action hover move");
                break;
            case MotionEvent.ACTION_DOWN:
                mCurrentX = xx;
                mCurrentY = yy;
                //Log.d(TAG,"action down");
                break;
            case MotionEvent.ACTION_MOVE:
                //Log.d(TAG,"action move");
                mCanvas.drawLine(mCurrentX, mCurrentY, xx, yy, mPaint);
                mCurrentX = xx;
                mCurrentY = yy;
                break;
            case MotionEvent.ACTION_UP:
               // Log.d(TAG,"action up");
                mCanvas.drawLine(mCurrentX,mCurrentY,xx,yy,mPaint);
                mCurrentX = xx;
                mCurrentY = yy;
                break;

        }
        invalidate();

        //Log.d(TAG, "getX() " + xx + " getY() " + yy + " getRawX() " + xRaw + " getRawY() " + yRaw);
        return true;
    }

}

