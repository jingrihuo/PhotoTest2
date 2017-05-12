package com.example.yuritian.phototest;

import android.graphics.Bitmap;
import android.os.StrictMode;
import android.util.Base64;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 82173 on 2017/5/12.
 */

public class HttpBitmapTest {
    String sevlet = "http://192.168.1.16:8080/ClassCircle/clientservlet";
    public String loadClasscircle(Bitmap bitmap,String fileType){
        String result = "null";
        try {
            String loadSevlet = sevlet+"?method=uploadfile";
            URL url = new URL(loadSevlet);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            // 设置请求方式
            urlConnection.setRequestMethod("POST");
            // 设置编码格式
            urlConnection.setRequestProperty("Charset", "UTF-8");
            // 设置容许输出
            urlConnection.setDoOutput(true);
            //把上面访问方式改为异步操作,就不会出现 android.os.NetworkOnMainThreadException异常
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            // 获得一个输出流，向服务器写数据，默认情况下，不允许程序向服务器输出数据
            JSONObject uploadImage = new JSONObject();
            uploadImage.put("check","classcircle-android");
            uploadImage.put("userAccount","13588204717");
            JSONArray Images = new JSONArray();
            JSONObject Image = new JSONObject();
            Image.put("fileType",fileType);
            Image.put("file",convertIconToString(bitmap));
            Images.put(Image);
            uploadImage.put("file",Images);
            OutputStream os = urlConnection.getOutputStream();
            os.write(uploadImage.toString().getBytes());
            os.flush();
            os.close();
            urlConnection.setConnectTimeout(20);
            if (urlConnection.getResponseCode() == 200){
                InputStream is = urlConnection.getInputStream();
                result = getStringFromInputStream(is);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public static String getStringFromInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = is.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        String result = baos.toString();
        is.close();
        baos.close();
        return result;
    }
    public static String convertIconToString(Bitmap bitmap)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();// outputstream
        bitmap.compress(Bitmap.CompressFormat.JPEG, 30, baos);
        byte[] appicon = baos.toByteArray();// 转为byte数组
        return Base64.encodeToString(appicon, Base64.DEFAULT);

    }
}
