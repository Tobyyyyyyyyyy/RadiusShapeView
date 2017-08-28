package com.wt.radiusshapeview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 圆角遮盖view
 *
 * @author wt
 * @create 2017-08-28 15:26
 */
public class RadiusShapeView extends View {

    public RadiusShapeView(Context context) {
        super(context);
        init(context, null);
    }

    public RadiusShapeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RadiusShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RadiusShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private Paint mPaint;

    private RectF mRect;

    private int mRadiusColor;

    private int mTopColor;

    private int mRadius;

    private void init(Context context, AttributeSet attrs) {
        // init attrs
        if (attrs != null) {
            final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RadiusShapeView);
            try {
                mRadiusColor = array.getColor(R.styleable.RadiusShapeView_rsv_radius_color, 0);
                mRadius = array.getDimensionPixelSize(R.styleable.RadiusShapeView_rsv_radius, 0);
            } finally {
                array.recycle();
            }
        }

        // init paint
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        mRect = new RectF();

        // LAYER_TYPE_HARDWARE will make background always black
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // clear canvas
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        // reset rect
        mRect.set(0, 0, getWidth(), getHeight());

        // draw bg(radius color)
        mPaint.setColor(mRadiusColor);
        canvas.drawRect(mRect, mPaint);

        // draw top
        mPaint.setColor(mTopColor);
        canvas.drawRoundRect(mRect, mRadius, mRadius, mPaint);
    }

    public void setRadiusColor(int mRadiusColor) {
        this.mRadiusColor = mRadiusColor;
        invalidate();
    }

    public void setRadius(int mRadius) {
        this.mRadius = mRadius;
        invalidate();
    }
}
