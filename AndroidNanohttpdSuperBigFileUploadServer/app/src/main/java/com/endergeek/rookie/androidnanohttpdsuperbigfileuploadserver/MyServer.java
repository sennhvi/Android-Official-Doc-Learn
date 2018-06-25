package com.endergeek.rookie.androidnanohttpdsuperbigfileuploadserver;

import android.content.Intent;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MyServer extends NanoHTTPD {
    private final String TAG = "imWebService";

    public static int PORT = 8899;
    public static String rootDir = Environment.getExternalStorageDirectory().getPath();
    private static Boolean serverState = false;
    private static String uploadFileHTML =
            "<form method='post' enctype='multipart/form-data' action='/u'>"+
            "    Step 1. Choose a file: <input type='file' name='upload' /><br />"+
            "    Step 2. Click Send to upload file: <input type='submit' value='Send' /><br />"+
            "</form>";

    public MyServer() {
        myPort = PORT;
    }

    /*
     * 在此處理從 activity 透過 startService(intent) 中的 intent 傳遞過來的訊息
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected Response getForbiddenResponse(String s) {
        Response r = newFixedLengthResponse(Response.Status.FORBIDDEN, NanoHTTPD.MIME_PLAINTEXT, "FORBIDDEN: " + s);
        return r;
    }

    /**
     * Serves file from homeDir and its' subdirectories (only). Uses only URI,
     * ignores all headers and HTTP parameters.
     */

    private Response respondUpload(IHTTPSession session) {
        Log.d("MyServer", "session.getMethod:" + session.getMethod());
        if (session.getMethod() == Method.GET) {
            return newFixedLengthResponse(Response.Status.OK, NanoHTTPD.MIME_HTML, uploadFileHTML);
        }
        else {

            /**
             * 需要增加外存空间大小满足,判断,参考FileOperationUtils.java
             * 需要处理连接超时异常 : java.net.SocketException: recvfrom failed: ETIMEDOUT (Connection timed out)
             */
            // {remote-addr=192.168.203.251, content-length=43798720, host=192.168.220.58:8080, http-client-ip=192.168.203.251,
            // user-agent=Mozilla/4.0, pragma=no-cache, content-type=multipart/form-data; boundary=---------------------------baofengupload}
            Log.d("singleconn", "session.headers:" + session.getHeaders());

            String msg = "";
            // mkdir Upload
            File upload = new File(rootDir+"/Upload");
            if (!upload.exists()) upload.mkdir();
            if (!upload.exists()) upload = new File(rootDir);
            Log.d("MyServer", "MyServer.respondUpload:" + upload + " rootDir:" + rootDir); // MyServer.respondUpload:/storage/emulated/0/Upload rootDir:/storage/emulated/0
            Map<String, String>files = new HashMap<String, String>();
            try {
                session.parseBody(files); // Adds the files in the request body to the files map. 阻塞cache!!!!实际下载进度在此,绕不过
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
            catch (ResponseException e1) {
                e1.printStackTrace();
            }
            return newFixedLengthResponse(Response.Status.OK, NanoHTTPD.MIME_HTML, msg);
        }
    }

    protected String getFileSavePath(String fileName) {
        if (TextUtils.isEmpty(fileName)) {
            return null;
        }
        return rootDir+"/Upload/"+fileName;
    }

    private Response respond(Map<String, String> headers, IHTTPSession session, String uri) {
        Response r;
        Log.i(TAG, "MyServer.respond("+rootDir+","+uri+") without cors");
        if (uri.startsWith("H")) {
            return respondUpload(session);
        } else {
            return getForbiddenResponse("Won't serve ../ for security reasons.");
        }
    }

    @Override
    public Response serve(IHTTPSession session) {
        String uri = session.getUri();
        Log.i(TAG, "MyServer.serve("+rootDir+","+uri+")"); // /storage/emulated/0,/

        Map<String, String> headers = session.getHeaders(); // 最重要的是 "host", "http-client-ip" = "remote-addr"

        return respond(Collections.unmodifiableMap(headers), session, uri);
    }
}
