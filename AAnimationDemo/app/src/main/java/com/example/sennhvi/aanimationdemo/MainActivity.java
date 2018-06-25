package com.example.sennhvi.aanimationdemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnAlpha;
    private Button btnRotate;
    private Button btnTranslate;
    private Button btnScale;
    private Button btnMix;
    private Button btnCustom;

    private RotateAnimation ra;
    private TranslateAnimation ta;
    private ScaleAnimation sa;
    private AnimationSet as; // mix effect animation
    private CustomAnimation ca; // custom effect animation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAlpha = (Button) this.findViewById(R.id.btn_alpha);
        btnRotate = (Button) this.findViewById(R.id.btn_rotate);
        btnTranslate = (Button) this.findViewById(R.id.btn_translate);
        btnScale = (Button) this.findViewById(R.id.btn_scale);
        btnMix = (Button) this.findViewById(R.id.btn_mix_effect);
        btnCustom = (Button) this.findViewById(R.id.btn_custom);
        btnAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlphaAnimation aa = new AlphaAnimation(0, 1); // transparency from 0 to 1
//                aa.setDuration(2000);
//                v.startAnimation(aa);

                v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.aa)); // or xml approach
            }
        });

//        ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        ra.setDuration(3000);
        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                v.startAnimation(ra);
                v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.ra));
            }
        });

//        ta = new TranslateAnimation(0, 200, 0, 200);
//        ta.setDuration(3000);
        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                v.startAnimation(ta);
                v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.ta));
            }
        });

//        sa = new ScaleAnimation(0, 1, 0, 1);
//        sa.setDuration(3000);
        btnScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                v.startAnimation(sa);
                v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.sa));
            }
        });


        // NOTE: if you want to add color change action in animation, Using ObjectAnimator is suggested. or use custom effect animation like below.
//        as = new AnimationSet(true);
//        as.setDuration(3000);
//
//        AlphaAnimation aa = new AlphaAnimation(0, 1);
//        aa.setDuration(2000);
//        as.addAnimation(aa);
//
//        TranslateAnimation ta = new TranslateAnimation(200, 0, 200, 0);
//        ta.setDuration(3000);
//        as.addAnimation(ta);
        btnMix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                v.startAnimation(as);
                Animation a = AnimationUtils.loadAnimation(MainActivity.this, R.anim.as);
                a.setAnimationListener(new Animation.AnimationListener() { // add event listener to animation
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        btnMix.setBackgroundColor(0xff888888);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                v.startAnimation(a);
            }
        });

        ca = new CustomAnimation();
        ca.setDuration(2000);
        btnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(ca);
            }
        });
    }
}
