package com.endergeek.rookie.animationtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView ivImage;

    private Button btnStart;

    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivImage = (ImageView) findViewById(R.id.image);
        ivImage.bringToFront();
        btnStart = (Button) findViewById(R.id.btn_start);
//        btnStart.setOnClickListener(this);
//        btnStop.setOnClickListener(this);
        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                TranslateAnimation animation = new TranslateAnimation(0, 0, 100, 200);
                animation.setInterpolator(new LinearInterpolator());
                animation.setDuration(400);
                animation.setFillAfter(true);
                ivImage.startAnimation(animation);
            }
        });
    }
}
