package com.example.myapplication;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
public class ListAdapter extends BaseAdapter {

    ArrayList<lessonClass> lessonList;
    Context context;
    LayoutInflater inflater;

    public ListAdapter(ArrayList<lessonClass> lessonList,Context context ) {
        this.lessonList=lessonList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lessonList.size();
    }

    @Override
    public Object getItem(int position) {
        return lessonList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.list_row, null);


        Gson gson;
        gson = new Gson();

        SharedPreferences prefs = context.getSharedPreferences("lessons_prefs", Context.MODE_PRIVATE);
        if(prefs.contains("data")==false){
            dataClass data = new dataClass();
            ArrayList<lessonClass> lessons = data.createData();
            String json = gson.toJson(lessons);
            SharedPreferences.Editor editor = context.getSharedPreferences("lessons_prefs", Context.MODE_PRIVATE).edit();
            editor.putString("data",json);
            editor.apply();
        }

        String json = prefs.getString("data",null);
        Type type = new TypeToken<ArrayList<lessonClass>>() {} .getType();
        lessonList = gson.fromJson(json , type);


        ImageView img = view.findViewById(R.id.listViewImage);
        if(lessonList.get(i).getStatus())
            img.setImageResource(R.drawable.check_circle);
        else
            img.setImageResource(R.drawable.circle);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lessonList.get(i).getStatus()) {
                    lessonList.get(i).setStatus(false);
                    img.setImageResource(R.drawable.circle);
                    MainActivity.MyApplication.progressCount=MainActivity.MyApplication.progressCount-1;
                    MainActivity.MyApplication.progressBar.setProgress(MainActivity.MyApplication.progressCount,true);
                    MainActivity.MyApplication.progressPer.setText(String.valueOf(MainActivity.MyApplication.progressCount)+"of 5");
                    String json = gson.toJson(lessonList);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("data", json);
                    editor.apply();
                } else {
                    lessonList.get(i).setStatus(true);
                    img.setImageResource(R.drawable.check_circle);
                    MainActivity.MyApplication.progressCount=MainActivity.MyApplication.progressCount+1;
                    MainActivity.MyApplication.progressBar.setProgress(MainActivity.MyApplication.progressCount,true);
                    MainActivity.MyApplication.progressPer.setText(String.valueOf(MainActivity.MyApplication.progressCount)+"of 5");
                    String json = gson.toJson(lessonList);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("data", json);
                    editor.apply();
                }

            }
        });



        TextView title = (TextView) view.findViewById(R.id.listViewTitle);
        title.setText(lessonList.get(i).getTitle());

        TextView shortDescription = (TextView) view.findViewById(R.id.listViewDesc);
        shortDescription.setText(lessonList.get(i).getDes1());


        return view;
    }
}
