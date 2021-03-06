package com.example.yuritian.phototest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;


import java.io.File;
import java.util.ArrayList;

import me.iwf.photopicker.PhotoPicker;

public class MainActivity extends Activity {
    private ImageView imageView;
    private FrameLayout imgTest;
    private Button test;
    private GridView images;
    public static Context mContext;
    public ArrayList<String> photos;
    private HttpBitmapTest httpBitmapTest;

    private int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        httpBitmapTest = new HttpBitmapTest();
        images = (GridView) findViewById(R.id.testimg);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        PhotoPicker.builder()
                .setPhotoCount(9)
                .setShowCamera(true)
                .setShowGif(false)
                .setPreviewEnabled(false)
                .start(this, PhotoPicker.REQUEST_CODE);


    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                final Bitmap bitmap = BitmapFactory.decodeFile(photos.get(0));
                Toast.makeText(MainActivity.this,photos.get(0),Toast.LENGTH_LONG).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (photos.get(0).endsWith(".jpg")){
                            httpBitmapTest.loadClasscircle(bitmap,"jpg");
                        }else if (photos.get(0).endsWith(".png")){
                            httpBitmapTest.loadClasscircle(bitmap,"png");
                        }else {
                            httpBitmapTest.loadClasscircle(bitmap,"jpeg");
                        }
                    }
                }).start();
                images.setNumColumns(4);
                images.setAdapter(new GridImageAdapter(this, photos,screenWidth));
            }
        }
    }

    public void testtets() {
//        ArrayList<String> photos =
//                data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
//        if (photos != null) {
//            if (photos.size() > 1) {
//                GridView images = new GridView(this);
//                images.setNumColumns(3);
//                images.setAdapter(new GridImageAdapter(this, photos));
//                images.setPadding(0, 0, 0, 0);
//                int row = 1;
//                if (photos.size() > 3) {
//                    row = 2;
//                }else if (photos.size()>6){
//                    row = 3;
//                }
//                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,dip2px(this,88*row));
//                images.setLayoutParams(params);
//                imgTest.removeAllViews();
//                imgTest.addView(images);
//            }else if (photos.size() == 1){
//                ImageView image = new ImageView(this);
//                image.setLeft(0);
//                Bitmap bitmap = BitmapFactory.decodeFile(photos.get(0));
//                Toast.makeText(MainActivity.this,photos.get(0),Toast.LENGTH_SHORT).show();
//                image.setImageBitmap(bitmap);
//                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                image.setScaleType(ImageView.ScaleType.FIT_XY);
//                int dwidth = bitmap.getWidth();
//                int dheighet = bitmap.getHeight();
//                if (dheighet>dwidth){
//                    int theighet = dip2px(this,180);
//                    int twidth = theighet*dheighet/dwidth;
//                    params.height = theighet;
//                    params.width = twidth;
//                }else {
//                    int twidth = dip2px(this,180);
//                    int theight = twidth*dheighet/dwidth;
//                    params.height = theight;
//                    params.width = twidth;
//                }
//                image.setLayoutParams(params);
//                imgTest.removeAllViews();
//                imgTest.addView(image);
//            }
//        }
    }
}
