package com.endergeek.rookie.popupwindowdemo;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnTouchListener, OnLongClickListener {

    private TextView tvLongText;
    private PopupWindow mPopupWindow;
    private int x, y;
    private LinearLayout llLongText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvLongText = (TextView) findViewById(R.id.tv_long_text);
        llLongText = (LinearLayout) findViewById(R.id.ll_long_text);
        tvLongText.setOnTouchListener(this);
        tvLongText.setOnLongClickListener(this);
    }

    //初始化popwindow
    private void initPopWindow() {
        //获取自定义视图的View
        View popView = getLayoutInflater().inflate(R.layout.mypopwindow, null, false);
        //创建一个popwindow,并设置布局和宽高
        mPopupWindow = new PopupWindow(popView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
        TextView btnTmp = (TextView) popView.findViewById(R.id.tv_popup_window_copy);
        btnTmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final android.content.ClipboardManager clipboardManager = (android.content.ClipboardManager) getApplicationContext()
                        .getSystemService(Context.CLIPBOARD_SERVICE);
                final android.content.ClipData clipData = android.content.ClipData
                        .newPlainText("text label", tvLongText.getText());
                clipboardManager.setPrimaryClip(clipData);
                doDismissPopupWindow();

                Toast.makeText(getApplicationContext(), clipboardManager.getPrimaryClip().getItemAt(0).getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });
        /**
         * 点击返回时清除PopupWindow
         */
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        mPopupWindow.showAsDropDown(llLongText, x, 0);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        tvLongText.setSelected(true); // 屏幕旋转后依然滚动
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            doDismissPopupWindow(); // 屏幕旋转后取消PopupWindow
        }
    }

    /**
     * 清除对话框:点击页面其他部分,点击对话框,屏幕旋转
     */
    private void doDismissPopupWindow() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
            mPopupWindow = null;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.tv_long_text:
                x = (int)event.getRawX();
                y = (int)event.getRawY();
                Log.d("coordinate", " onTouch print:" + x + " y:" +  y);
                return false;
        }
        return false;
    }

    @Override
    public boolean onLongClick(View view) {
        switch (view.getId()) {
            case R.id.tv_long_text:
                initPopWindow();
                return true;
        }
        return false;
    }
}
