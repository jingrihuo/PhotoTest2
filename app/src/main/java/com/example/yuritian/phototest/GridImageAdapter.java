package com.example.yuritian.phototest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by SunFly on 2016/10/27.
 */
public class GridImageAdapter extends BaseAdapter {
    private Context context;
    private List<String> data;
    private int screenWidth;

    public GridImageAdapter(Context context, List<String> data,int screenWidth) {
        this.context = context;
        this.data = data;
        this.screenWidth = screenWidth;
    }

    @Override
    public int getCount() {
        if (data.size()==9){
            return data.size();
        }else {
            return data.size()+1;
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            if (position > data.size()-1){
                ImageView imageView = new ImageView(context);
                imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_addpic_focused));
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setPadding(5, 5, 5, 5);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(dip2px(context, 80), dip2px(context, 80));
                imageView.setLayoutParams(params);
                convertView = imageView;
            }else {
                ImageView imageView = new ImageView(context);
                Bitmap bitmap = BitmapFactory.decodeFile(data.get(position));
                imageView.setImageBitmap(bitmap);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setPadding(5, 5, 5, 5);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(dip2px(context, 80), dip2px(context, 80));
                imageView.setLayoutParams(params);
                convertView = imageView;
            }
        }
        return convertView;
    }
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
