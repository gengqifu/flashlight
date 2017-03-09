package org.hunter.flashlight.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;

public class LightBkView extends View implements OnClickListener {

    Camera camera = Camera.open();
    Paint paint = new Paint();
    Paint paint1 = new Paint();
    int x = 0;
    int y = 0;
    boolean islight;

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
        /*paint.setColor(Color.BLUE);
		canvas.drawCircle(x, y, 60, paint);
		paint1.setColor(Color.RED);
		paint1.setTextSize(20);*/
            //canvas.drawText("�������", x-50, y, paint1);
            invalidate();
        } else {
			/*paint.setColor(Color.WHITE);
			canvas.drawCircle(x, y, 60, paint);
			paint1.setColor(Color.RED);
			paint1.setTextSize(20);*/
            //canvas.drawText("�ر������", x-50, y, paint1);
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
        if (!islight) {
            Parameters mParameters = camera.getParameters();
            mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(mParameters);
            islight = true;
        } else {
            Parameters mParameters = camera.getParameters();
            mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(mParameters);
            islight = false;
        }
    }

}
