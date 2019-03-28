package com.example.titanic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CanvasView extends View {

    public int width;
    public int height;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    Context context;
    private Paint mPaint;
    private float mX=100, mY=1000;
    private static final float TOLERANCE = 5;
    Drawable image = ResourcesCompat.getDrawable(getResources(), R.drawable.titanic, null);
    Bitmap picTitanic = BitmapFactory.decodeResource(getResources(), R.drawable.titanic);
    Bitmap picIceberg = BitmapFactory.decodeResource(getResources(), R.drawable.iceberg);

    public CanvasView(Context c, AttributeSet attrs) {
        super(c, attrs);
        context = c;

        
        // we set a new Path
        mPath = new Path();

        // and we set a new Paint with the desired attributes
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(4f);

    }

    // override onSizeChanged
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // your Canvas will draw onto the defined Bitmap
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor (111111);
    }

    // override onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // draw the mPath with the mPaint on the canvas when onDraw
        canvas.drawCircle(mX,mY,100,mPaint);
        canvas.drawARGB(255, 0, 143, 231);
        for (iceberg ice : MainActivity.getIceberg())
        {
            canvas.drawBitmap (picIceberg, ice.getX(), ice.getY(), mPaint);
        }
        canvas.drawBitmap (picTitanic, mX, mY, mPaint);

    }

    // when ACTION_DOWN start touch according to the x,y values
    public void startTouch(float x, float y) {
        mX = x;
        mY = y;
    }

    // when ACTION_MOVE move touch according to the x,y values
    public void moveTouch(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    public void moveCircle(float x, float y){
        startTouch(x,y);
        moveTouch(x,y);
       // upTouch();
        invalidate();

    }
    public void drawIce(float x, float y){

    }

   /* // when ACTION_UP stop touch
    private void upTouch() {
        mPath.lineTo(mX, mY);
    }*/


}