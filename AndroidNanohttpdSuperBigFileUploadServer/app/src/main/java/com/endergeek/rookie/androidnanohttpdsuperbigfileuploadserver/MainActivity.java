package com.endergeek.rookie.androidnanohttpdsuperbigfileuploadserver;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WifiManager wm = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wi = wm.getConnectionInfo();
        int ipSelf = wi.getIpAddress();
        setContentView(R.layout.activity_main);
        mTextView = (TextView)findViewById(R.id.msg_view);
        mTextView.setText("Serve at http://" + UrlParserUtil.selfLongToStringIp(ipSelf) + ":" + MyServer.PORT + "\n" +
                "Root Dir: " + MyServer.rootDir + "\n");
        Intent intent = new Intent(MainActivity.this, MyServer.class);
        startService(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        mTextView = (TextView)findViewById(R.id.msg_view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

