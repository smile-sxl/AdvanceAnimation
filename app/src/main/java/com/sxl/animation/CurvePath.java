package com.sxl.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * author: smile .
 * date: On 2018/6/25
 */
public class CurvePath extends View {

    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    public Path path;


    public CurvePath(Context context) {
        this(context, null);
    }

    public CurvePath(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CurvePath(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mWidth = getResources().getDisplayMetrics().widthPixels;
        mHeight = getResources().getDisplayMetrics().heightPixels;
        path = new Path();
        path.moveTo(100, 100);
        path.quadTo(mWidth - 300, 200, mWidth - 100, mHeight - 600);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, mPaint);
    }
}
