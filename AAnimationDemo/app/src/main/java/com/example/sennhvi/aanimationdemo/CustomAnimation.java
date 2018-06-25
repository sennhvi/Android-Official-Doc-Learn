package com.example.sennhvi.aanimationdemo;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by sennhvi on 3/15/17.
 */

public class CustomAnimation extends Animation {

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {  // used to get height, width...
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) { // interpolatedTime will start from 0 to 1(end).
//        t.getMatrix().setTranslate(200*interpolatedTime, 200*interpolatedTime);
        t.getMatrix().setTranslate((float) (Math.sin(interpolatedTime*20)*30), 0); // shake shake
        super.applyTransformation(interpolatedTime, t);

    }
}
