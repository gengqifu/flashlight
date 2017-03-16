package org.hunter.flashlight.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

public class LightView2 extends View implements View.OnClickListener {
    private int x = 0;
    private int y = 0;
    private CameraManager mCameraManager;
    private String mCameraId;
    private boolean islight;

    public LightView2(Context context, AttributeSet set) {
        super(context, set);
        mCameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        try {
            mCameraId = mCameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = this.getWidth();
        int heigth = this.getHeight();
        x = width / 2;
        y = heigth / 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getWidth(widthMeasureSpec),
                getHeight(heightMeasureSpec));

    }

    public int getWidth(int widthMeasureSpec) {
        int reslut = 0;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {
            reslut = 120;
        }
        if (widthMode == MeasureSpec.EXACTLY) {
            reslut = MeasureSpec.getSize(widthMeasureSpec);
        }
        return reslut;
    }

    public int getHeight(int heightMeasureSpec) {
        int reslut = 0;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode == MeasureSpec.AT_MOST) {
            reslut = 120;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            reslut = MeasureSpec.getSize(heightMeasureSpec);
        }
        return reslut;
    }

    @Override
    public void onClick(View v) {
        if (!islight) {
            turnOnFlashLight();
            islight = true;
        } else {
            turnOffFlashLight();
            islight = false;
        }
    }

    public void turnOnFlashLight() {

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mCameraManager.setTorchMode(mCameraId, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void turnOffFlashLight() {

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mCameraManager.setTorchMode(mCameraId, false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
