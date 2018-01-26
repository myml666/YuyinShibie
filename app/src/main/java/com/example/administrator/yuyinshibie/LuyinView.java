package com.example.administrator.yuyinshibie;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/1/26 0026.
 * Date 2018/1/26 0026
 */

public class LuyinView extends View {
    private int mWidth,mHeight;
    private Paint mPaint;

    public LuyinView(Context context) {
        this(context,null);
    }

    public LuyinView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LuyinView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mWidth/2,mHeight/2,mWidth/4,mPaint);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.animate().scaleX(2).scaleY(2).alpha(0).setDuration(1000).start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(MeasureSpec.getMode(widthMeasureSpec)==MeasureSpec.EXACTLY){
            mWidth=MeasureSpec.getSize(widthMeasureSpec);
        }else {
            mWidth=100;
        }
        if(MeasureSpec.getMode(heightMeasureSpec)==MeasureSpec.EXACTLY){
            mHeight=MeasureSpec.getSize(heightMeasureSpec);
        }else {
            mHeight=100;
        }
        setMeasuredDimension(mWidth, mHeight);
        initPaint();
    }
}
