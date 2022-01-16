package com.fengdi.mytestdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ScaleXSpan;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyRingView extends View {

    private Paint mTextPaint = null;
    private Paint mArcPaint = null;
    private Paint mBgPaint = null;

    private int mCount = 6;

    private int sweepAngle = 0;

    private int startAngle = 0;


    private RectF sectorRectF = new RectF(0, 0, 400, 400);

    private RectF subBgRectF = new RectF(100, 100, 300, 300);


    private int mRadius = 200;

    private String[] textInfo = new String[]{"菜单1", "菜单菜单菜单哈哈哈2",
            "菜单菜单菜单哈哈哈哈哈哈2", "菜单4", "菜单5", "菜单6"};


    public MyRingView(Context context) {
        super(context);
        initPaint();
    }

    public MyRingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public MyRingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();

    }

    public MyRingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }

    void initPaint() {
        mTextPaint = new Paint();
        mArcPaint = new Paint();
        mBgPaint = new Paint();
        mBgPaint.setColor(Color.WHITE);
        mArcPaint.setColor(Color.BLUE);
        mTextPaint.setColor(Color.RED);
        mTextPaint.setTextSize(25);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawWheel(canvas);
    }

    public void drawWheel(Canvas canvas) {
        //绘制扇形
        //设置每一个扇形的角度
        sweepAngle = 360 / mCount;
        startAngle = -120;


        for (int i = 0; i < mCount; i++) {
            //sectorRectF 扇形绘制范围  startAngle 弧开始绘制角度 sweepAngle 每次绘制弧的角度
            // useCenter 是否连接圆心
            canvas.drawArc(sectorRectF, startAngle, sweepAngle, true, mArcPaint);
//            //绘制二级小背景
            canvas.drawArc(subBgRectF, startAngle, sweepAngle, true, mBgPaint);

            //3.绘制文字
            drawTexts(canvas, textInfo[i]);

//            angles[i] = startAngle;
//            Log.d(TAG, "onDraw: " + angles[i] + "     " + i);
            startAngle += sweepAngle;
        }
    }


    private void drawTexts(Canvas canvas, String mString) {
        Path path = new Path();
        //添加一个圆弧的路径
        path.addArc(sectorRectF, startAngle, sweepAngle);
        String startText = null;
        String endText = null;
        //测量文字的宽度
        float textWidth = mTextPaint.measureText(mString);
        //水平偏移
        int hOffset = (int) (mRadius * 2 * Math.PI / mCount / 2 - textWidth / 2);
        //计算弧长 处理文字过长换行
        int l = (int) ((360 / mCount) * Math.PI * mRadius / 180);
        if (textWidth > l * 4 / 5f) {
            int index = mString.length() / 2;
            startText = mString.substring(0, index);
            endText = mString.substring(index);

            float startTextWidth = mTextPaint.measureText(startText);
            float endTextWidth = mTextPaint.measureText(endText);
            //水平偏移
            hOffset = (int) (mRadius * 2 * Math.PI / mCount / 2 - startTextWidth / 2);
            int endHOffset = (int) (mRadius * 2 * Math.PI / mCount / 2 - endTextWidth / 2);
            //文字高度
            int h = (int) ((mTextPaint.ascent() + mTextPaint.descent()) * 1.5);

            //根据路径绘制文字
            //hOffset 水平的偏移量 vOffset 垂直的偏移量
            canvas.drawTextOnPath(startText, path, hOffset, mRadius / 5f, mTextPaint);
            canvas.drawTextOnPath(endText, path, endHOffset, mRadius / 5f - h, mTextPaint);
        } else {
            //根据路径绘制文字
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < mString.length(); i++) {
                String c = "" + mString.charAt(i);
                builder.append(c.toLowerCase());
                if (i + 1 < mString.length()) {
                    builder.append("\u00A0");
                }
            }
            SpannableString finalText = new SpannableString(builder.toString());
            if (builder.toString().length() > 1) {
                for (int i = 1; i < builder.toString().length(); i += 2) {
                    finalText.setSpan(new ScaleXSpan((1 + 1) / 10), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
            canvas.drawTextOnPath(finalText.toString(), path, hOffset, mRadius / 3.5f, mTextPaint);
        }

    }


}
