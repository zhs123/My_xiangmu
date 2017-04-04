package utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
*1.类的用途
*2.zhanghaisheng
*3.2017/3/22
**/
public class MyView extends View {
    private Paint mPaint;
    private Paint mstrokePaint;
    private int mRadius;
    private Paint mDotLinePaint;
    public MyView(Context context) {
        super(context);
        init();
    }
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        mPaint=new Paint();
        //抗锯齿
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mstrokePaint=new Paint();
        mstrokePaint.setAntiAlias(true);
        mstrokePaint.setColor(Color.RED);
        mstrokePaint.setStyle(Paint.Style.STROKE);
        mstrokePaint.setStrokeWidth(3);

        mDotLinePaint =new Paint();
        mDotLinePaint.setAntiAlias(true);
        mDotLinePaint.setColor(Color.RED);
        mDotLinePaint.setStyle(Paint.Style.STROKE);
        mDotLinePaint.setStrokeWidth(4);
        //就是绘制那条线
        mDotLinePaint.setPathEffect(new DashPathEffect(new float[]{5,4},0));
        mRadius= DanweiUtil.dp2px(getContext(),6);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode==MeasureSpec.UNSPECIFIED)//未指定模式
        {
            widthSize=DanweiUtil.dp2px(getContext(),20);

        }
        if (heightMode==MeasureSpec.UNSPECIFIED){
            heightSize=DanweiUtil.dp2px(getContext(),20);
        }
        setMeasuredDimension(widthSize,heightSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(getWidth()/2,0,getWidth()/2,getHeight()/5,mstrokePaint);
        canvas.drawCircle(getWidth()/2,getHeight()/5+mRadius,mRadius*2/3,mPaint);
        Path path=new Path();
        path.moveTo(getWidth()/2+mRadius+DanweiUtil.dp2px(getContext(),3),getHeight()/5+mRadius);
        path.lineTo(getWidth(),getHeight()/5+mRadius);
        canvas.drawPath(path,mDotLinePaint);
        canvas.drawCircle(getWidth()/2,getHeight()/5+mRadius,mRadius,mstrokePaint);
        canvas.drawLine(getWidth()/2,getHeight()/5+2*mRadius,getWidth()/2,getHeight(),mstrokePaint);
    }
}
