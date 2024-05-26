package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class LessonActivity extends AppCompatActivity {

    int i;
    SharedPreferences prefs;
    ArrayList<lessonClass> lessonsInfo;

    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lesson);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Intent intent = getIntent();
        i= intent.getIntExtra("SELECTEDLESSON",0);
        gson = new Gson();
        prefs = getSharedPreferences("lessons_prefs" ,MODE_PRIVATE);
        if(prefs.contains("data")==false){
            dataClass data = new dataClass();
            ArrayList<lessonClass> lessons = data.createData();
            String json = gson.toJson(lessons);
            SharedPreferences.Editor editor = getSharedPreferences("lessons_prefs",MODE_PRIVATE).edit();
            editor.putString("data",json);
            editor.apply();
        }

        String json = prefs.getString("data",null);
        Type type = new TypeToken<ArrayList<lessonClass>>() {} .getType();
        lessonsInfo = gson.fromJson(json , type);

        TextView dec1 = findViewById(R.id.lessonDec1);
        dec1.setText(lessonsInfo.get(i).getDes1());

        ImageView pic = findViewById(R.id.lessonPic);
        pic.setImageResource(lessonsInfo.get(i).getPic());

        TextView dec2 = findViewById(R.id.lessonDec2);
        dec2.setText(lessonsInfo.get(i).getDes2());


    }
}