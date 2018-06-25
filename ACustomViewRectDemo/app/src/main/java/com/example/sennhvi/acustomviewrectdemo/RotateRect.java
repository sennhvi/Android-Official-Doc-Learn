package com.example.sennhvi.acustomviewrectdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sennhvi on 3/14/17.
 */

public class RotateRect extends View {

    private Paint paint; // 1. paint to control view
    private float d = 0;

    public RotateRect(Context context) {
        super(context);
        initProperties(); // 2. call init method in all constructor
    }

    public RotateRect(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initProperties();
    }

    public RotateRect(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initProperties();
    }

    private void initProperties() {
        paint = new Paint(); // 3. init paint, set default attrs
        paint.setColor(Color.RED);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.save();
        canvas.translate(200, 200); // make a move
        canvas.rotate(d, 50, 50); // rotate center
        canvas.drawRect(0, 0, 100, 100, paint);
        d++;
        canvas.restore(); // restore the state before save() called

        invalidate(); // 4. invalidate to repaint view for animation, resource consume
    }
}
