package com.example.sennhvi.acustomviewrectdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sennhvi on 3/14/17.
 */

public class CustomRect extends View{

    public CustomRect(Context context) {
        super(context);
    }

    public CustomRect(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 2. get custom attrs from xml file
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CusomView);

        // 3. get value from attrs array with default value
        int color = ta.getColor(R.styleable.CusomView_rect_color, 0xffff0000);
        // 4. do with value
        setBackgroundColor(color);

        // 5. recycle TypedArray
        ta.recycle();
    }
}
