package com.ppw.projectdemos.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.ppw.projectdemos.R;

import androidx.annotation.Nullable;

/**
 * <pre>
 *     Created by ppW
 *     e-mail : wangpanpan05@163.com
 *     time   : 2019/04/08
 *     desc   : 自定义对比条
 *     version: 1.0   初始化
 *     params:
 *  <pre>
 */
public class ComparedView extends View {

    private int mSpace = 30;//左右两边之间的空隙
    private float mTextLeftX;//文字左边 x轴
    private float mTextY;//y轴距离
    private String mTextLeft;//左边文字
    private String mTextRight;//右边文字
    private int mTextRightX;//文字右边 x轴
    private int mY = 100000000;//亿
    private int mW = 10000;//万
    private int mColorLeft;//左边颜色
    private int mColorRight;//右边颜色
    private int mColorText;//文字颜色
    private float mTextSize;
    private Paint mPaintLeft;
    private Paint mPaintRight;
    private Paint mPaintText;
    private Path mPathLeft;
    private Path mPathRight;
    private long leftScore;//左边数字
    private long rightScore;//右边数字

    public ComparedView (Context context) {
        super(context);
        initPaint();
        initPath();
    }

    public ComparedView (Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttr(attrs);
        initPaint();
        initPath();
    }

    private void initAttr (AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ComparedView);
        mColorLeft = typedArray.getColor(R.styleable.ComparedView_leftColor, Color.parseColor("#fe8c8b"));
        mColorRight = typedArray.getColor(R.styleable.ComparedView_rightColor, Color.parseColor("#8fc3f2"));
        mColorText = typedArray.getColor(R.styleable.ComparedView_textColor, Color.parseColor("#ffffff"));
        mTextSize = typedArray.getDimension(R.styleable.ComparedView_textSize, 40);
        typedArray.recycle();
    }

    public ComparedView (Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setLeftRightScore (long leftScore, long rightScore) {
        this.leftScore = leftScore;
        this.rightScore = rightScore;
        initPath();
        invalidate();
    }

    private void initPath () {
        int width = getWidth();
        int height = getHeight();

        if (width == 0) {
            return;
        }
        long all = leftScore + rightScore;
        float scale;
        if (all == 0) {
            scale = 0.5f;
        } else {
            scale = leftScore * 1.0f / all;
        }
        float leftWidth = width * scale;
        float rightWidth = width - leftWidth;
        mTextLeft = formatBigNum(leftScore);
        mTextRight = formatBigNum(rightScore);
        float leftTxtWidth = mPaintText.measureText(mTextLeft);
        float rightTxtWidth = mPaintText.measureText(mTextRight);
        if (leftWidth < leftTxtWidth + mSpace * 2) {
            leftWidth = leftTxtWidth + mSpace * 2;
            rightWidth = width - leftWidth;
        }
        if (rightWidth < rightTxtWidth + mSpace * 2) {
            rightWidth = rightTxtWidth + mSpace * 2;
            leftWidth = width - rightWidth;
        }
        mPathLeft = new Path();
        mPathLeft.moveTo(0, 0);
        mPathLeft.rLineTo(leftWidth, 0);
        mPathLeft.rLineTo(- mSpace, height);
        mPathLeft.rLineTo(- leftWidth + mSpace, 0);
        mPathLeft.close();

        mPathRight = new Path();
        mPathRight.moveTo(leftWidth, height);
        mPathRight.rLineTo(rightWidth, 0);
        mPathRight.rLineTo(0, - height);
        mPathRight.rLineTo(- (rightWidth - mSpace), 0);
        mPathRight.close();
        mTextLeftX = mSpace/2;
        Paint.FontMetrics fontMetrics = mPaintText.getFontMetrics();
        float offset = - (fontMetrics.ascent + fontMetrics.descent) / 2;
        mTextY = height / 2 + offset;//处理文字不居中的情况
        mTextRightX = (int) (width - mSpace/2 - rightTxtWidth);
    }


    private void initPaint () {
        mPaintLeft = new Paint();
        mPaintLeft.setColor(mColorLeft);
        mPaintLeft.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintLeft.setTextSize(20);
        mPaintLeft.setAntiAlias(true);
        mPaintRight = new Paint();
        mPaintRight.setColor(mColorRight);
        mPaintRight.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintRight.setTextSize(20);
        mPaintRight.setAntiAlias(true);
        mPaintText = new Paint();
        mPaintText.setColor(mColorText);
        mPaintText.setTextSize(mTextSize);
        mPaintText.setAntiAlias(true);
    }


    @Override
    protected void onDraw (Canvas canvas) {
        if (mPathRight == null || mPathLeft == null) {
            //防止初始化 不做处理
            return;
        }
        canvas.drawPath(mPathLeft, mPaintLeft);
        canvas.drawPath(mPathRight, mPaintRight);
        canvas.drawText(mTextLeft, mTextLeftX, mTextY, mPaintText);
        canvas.drawText(mTextRight, mTextRightX, mTextY, mPaintText);
    }

    /**
     * 返回加上万 亿的字符串
     *
     * @param longNum
     * @return
     */
    private String formatBigNum (long longNum) {
        String result;
        if (longNum >= mY) {
            result = longNum / mY + "." + longNum % mY / (mY / 10) + "亿";
        } else if (longNum >= mW) {
            result = longNum / mW + "." + longNum % mW / (mW / 10) + "万";

        } else {
            result = String.valueOf(longNum);
        }
        return result;
    }

}
