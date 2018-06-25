package com.endergeek.rookie.animationlistdemo;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView animationList;
    private Button btnAsStart;
    private Button btnStop;
    private Button btnDesStart;
    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animationList = (ImageView) findViewById(R.id.animation_list);
        btnAsStart = (Button) findViewById(R.id.start_ascend);
        btnStop = (Button) findViewById(R.id.stop);
        btnDesStart = (Button) findViewById(R.id.start_descend);

        btnAsStart.setOnClickListener(this);
        btnDesStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_ascend:
                animationList.setImageResource(R.drawable.animation_list_ascend);
                animationDrawable = (AnimationDrawable) animationList.getDrawable();
                animationDrawable.start();
                break;
            case R.id.stop:
                animationDrawable = (AnimationDrawable) animationList.getDrawable();
                animationDrawable.stop();
                break;
            case R.id.start_descend:
                animationList.setImageResource(R.drawable.animation_list_descend);
                animationDrawable = (AnimationDrawable) animationList.getDrawable();
                animationDrawable.start();
                break;
            default:
                break;
        }
    }
}
