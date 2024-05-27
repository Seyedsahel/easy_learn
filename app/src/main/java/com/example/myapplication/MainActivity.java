package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    Gson gson;
    SharedPreferences prefs;

    ArrayList<lessonClass> lessonsInfo;
    int progresscount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        prefs = getSharedPreferences("lessons_prefs" ,MODE_PRIVATE);
        gson = new Gson();
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



        ListView list = findViewById(R.id.listView);
        ListAdapter adapter = new ListAdapter(lessonsInfo , MainActivity.this);

        list.setAdapter(adapter);



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MainActivity.this,LessonActivity.class);
                    intent.putExtra("SELECTEDLESSON",position);
                    startActivity(intent);
            }
        });

        for (int i=0;i<lessonsInfo.size();i++){
            if(lessonsInfo.get(i).getStatus())
                progresscount++;
        }
        ProgressBar progress = findViewById(R.id.progressBar);
        progress.setProgress(progresscount,true);

        TextView progressPer = findViewById(R.id.progress_per);
        int pp = progresscount*100/3;
        progressPer.setText(String.valueOf(pp)+"%");



    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the list and progress bar
        String json = prefs.getString("data", null);
        Type type = new TypeToken<ArrayList<lessonClass>>() {}.getType();
        lessonsInfo = gson.fromJson(json, type);

        ListView list = findViewById(R.id.listView);
        ListAdapter adapter = new ListAdapter(lessonsInfo, MainActivity.this);
        list.setAdapter(adapter);

        progresscount = 0;
        for (int i = 0; i < lessonsInfo.size(); i++) {
            if (lessonsInfo.get(i).getStatus())
                progresscount++;
        }
        ProgressBar progress = findViewById(R.id.progressBar);
        progress.setProgress(progresscount, true);
        int pp = progresscount * 100 / 3;

        TextView progressPer = findViewById(R.id.progress_per);
        progressPer.setText(String.valueOf(pp) + "%");
    }




}

