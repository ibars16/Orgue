package app.david.orgue;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;

@SuppressLint("ResourceAsColor")
public class MyImageView extends AppCompatImageView {
    private Paint currentPaint;
    public boolean drawRect = false;
    public float left = 0f;
    public float top = 0f;
    public float right = 0f;
    public float bottom = 0f;

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        currentPaint = new Paint();
        currentPaint.setDither(true);
        currentPaint.setColor(-0xff3400);
        currentPaint.setStyle(Paint.Style.STROKE);
        currentPaint.setStrokeJoin(Paint.Join.ROUND);
        currentPaint.setStrokeCap(Paint.Cap.ROUND);
        currentPaint.setStrokeWidth(5f);
        currentPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (drawRect) {
            canvas.drawRect(left, top, right, bottom, currentPaint);
        }
    }

    public void selectColor(String color){
        if (color.equals("verd")){
            currentPaint.setColor(Color.GREEN);
        }else{
            currentPaint.setColor(Color.RED);
        }

    }
}
