package com.example.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GridAdapter extends BaseAdapter {

    int screenWidth;
    Context context;
    int[] images;

    public GridAdapter(int[] images , Context context)
    {
        this.context = context;
        this.images = images;
        // مشخص کردن عرض صفحه نمایش بر اساس پیکسل
        screenWidth= context.getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    public int getCount() {
        // تعداد تصاویر گرید ویو
        return images.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView img;

        if(view == null)
        {
            img = new ImageView(context);
            img.setLayoutParams(new GridView.LayoutParams(screenWidth/3, screenWidth/3));
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img.setPadding(7, 7, 7, 7);
        }
        else {
            img = (ImageView) view;
        }

        img.setImageResource(images[i]);
        return img;
    }
}
