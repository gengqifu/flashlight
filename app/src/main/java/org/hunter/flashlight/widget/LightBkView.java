package org.hunter.flashlight.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;

import org.hunter.flashlight.util.FlashMode;

import java.util.Timer;
import java.util.TimerTask;

public class LightBkView extends View implements OnClickListener {

    private Camera camera = Camera.open();
    private Paint paint = new Paint();
    private Paint paint1 = new Paint();
    private int x = 0;
    private int y = 0;
    private boolean islight = false;
    private FlashMode flashMode = FlashMode.OFF;
    private Timer timer;
    private TimerTask task;

    public LightBkView(Context context, AttributeSet set) {
        super(context, set);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = this.getWidth();
        int heigth = this.getHeight();
        x = width / 2;
        y = heigth / 2;
        if (!islight) {
            invalidate();
        } else {
            invalidate();
        }
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

    private void turnOnFlashLight() {
        Parameters mParameters = camera.getParameters();
        mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(mParameters);
    }

    private void turnOffFlashLight() {
        Parameters mParameters = camera.getParameters();
        mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(mParameters);
    }

    private void flicker(long period) {
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
