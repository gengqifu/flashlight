package org.hunter.flashlight.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import org.hunter.flashlight.util.FlashMode;

import java.util.Timer;
import java.util.TimerTask;

public class LightView2 extends View implements View.OnClickListener {
    private CameraManager mCameraManager;
    private String mCameraId;
    private boolean islight = false;
    private FlashMode flashMode = FlashMode.OFF;
    private Timer timer;
    private TimerTask task;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
        switch (flashMode) {
            case OFF:
                flashMode = FlashMode.ON;
                turnOnFlashLight();
                break;
            case ON:
                flashMode = FlashMode.FLICKER_SLOW;
                flicker(300);
                break;
            case FLICKER_SLOW:
                timer.cancel();
                flashMode = FlashMode.FLICKER_FAST;
                flicker(100);
                break;
            case FLICKER_FAST:
                timer.cancel();
                flashMode = FlashMode.OFF;
                turnOffFlashLight();
                break;
            default:
                break;
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

    public void flicker(long period) {
        timer = new Timer();
        task = new MyTimerTask();
        timer.schedule(task, 0, period);
    }

    private class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            if (islight) {
                turnOffFlashLight();
                islight = false;
            } else {
                turnOnFlashLight();
                islight = true;
            }
        }
    }
}
