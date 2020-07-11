package com.shuangtu.prison.discern.face;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.shuangtu.prison.discern.face.DrawInfo;

public class BoundingBoxView extends SurfaceView implements SurfaceHolder.Callback {

    protected SurfaceHolder mSurfaceHolder;

    private Paint mPaint;

    private boolean mIsCreated;

    public BoundingBoxView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mSurfaceHolder = getHolder();
        mSurfaceHolder.setFormat(PixelFormat.TRANSPARENT);
        mSurfaceHolder.addCallback(this);

        setZOrderOnTop(true);

        mPaint = new Paint();
        mPaint.setTextSize(25.0f);//设置字体大小
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(5f);
        mPaint.setStyle(Paint.Style.STROKE);


    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mIsCreated = true;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        mIsCreated = false;
    }

    public void setResults(DrawInfo drawInfo)
    {
        if (!mIsCreated) {
            return;
        }

        Canvas canvas = mSurfaceHolder.lockCanvas();

        //清除掉上一次的画框。
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        canvas.drawColor(Color.TRANSPARENT);

        //for (VisionDetRet detRet : detRets) {
        if (drawInfo.getRect() != null && drawInfo.getRect().bottom > 0)
        {
            canvas.drawRect(drawInfo.getRect(), mPaint);
        }

        if (drawInfo.getUserFeatureVo() != null)
        {
            String text = "用户id:" + drawInfo.getUserFeatureVo().getId() +
                          "\n姓名:" + drawInfo.getUserFeatureVo().getTrueName() +
                          "\n电话:" + drawInfo.getUserFeatureVo().getPhone();
            canvas.drawText(text, drawInfo.getRect().right + 10,
                        drawInfo.getRect().top  +  (drawInfo.getRect().bottom - drawInfo.getRect().top)/2,
                            mPaint);
        }
        //}

        mSurfaceHolder.unlockCanvasAndPost(canvas);

    }
}
