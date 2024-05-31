package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class LessonActivity extends AppCompatActivity {

    int i;

    MediaPlayer mediaPlayer;
    SharedPreferences prefs;
    ArrayList<lessonClass> lessonsInfo;

    PopupWindow popupWindow;


    Gson gson;

    int[] lessonIcons = {R.drawable.circleone,R.drawable.circletwo,R.drawable.circlethree,R.drawable.circlefour,R.drawable.circlefive};;

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

        TextView titlelesson = findViewById(R.id.titlelesson);
        titlelesson.setText(lessonsInfo.get(i).getTitle());

        ImageView logoLesson = findViewById(R.id.logolesson);
        logoLesson.setImageResource(lessonIcons[i]);

        TextView dec1 = findViewById(R.id.lessonDec1);
        dec1.setText(lessonsInfo.get(i).getDes1());

        ImageView pic = findViewById(R.id.lessonPic);
        pic.setImageResource(lessonsInfo.get(i).getPic());

        TextView dec2 = findViewById(R.id.lessonDec2);
        dec2.setText(lessonsInfo.get(i).getDes2());

        ImageView playVid = findViewById(R.id.plyvid);

        playVid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer == null)
                    mediaPlayer = MediaPlayer.create(LessonActivity.this,lessonsInfo.get(i).getVid());
                getWindow().setFormat(PixelFormat.UNKNOWN);
                SurfaceView mPreview = (SurfaceView)findViewById(R.id.lessonVid);
                SurfaceHolder holder = mPreview.getHolder();
                holder.setFixedSize(800, 480);
                holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
                mediaPlayer.setDisplay(holder);
                mediaPlayer.start();
                mediaPlayer.setLooping(false);
            }
        });

        ImageView pauseVid = findViewById(R.id.pusvid);
        pauseVid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying())
                    mediaPlayer.pause();
            }
        });

        ImageView stopVid = findViewById(R.id.stpvid);
        stopVid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
            }
        });

        SeekBar setvalume = findViewById(R.id.setvalume);
        setvalume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mediaPlayer == null)
                    mediaPlayer = MediaPlayer.create(LessonActivity.this,lessonsInfo.get(i).getVid());
                mediaPlayer.setVolume((float)progress*2,(float)progress*2);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        LottieAnimationView finishBtn = findViewById(R.id.lessonFinishBtn);

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lessonsInfo.get(i).setStatus(true);
                String json = gson.toJson(lessonsInfo);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("data", json);
                editor.apply();

                // Add this part to go back to MainActivity and update the UI
                Intent intent = new Intent(LessonActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // This will finish the current LessonActivity
            }
        });


        ImageView menuimg = findViewById(R.id.FontSize);
        menuimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
            }
        });


    }


    private void showPopupMenu(View anchor) {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View popupView = inflater.inflate(R.layout.menu_item_with_seekbar, null);

            // پیدا کردن seekbar در پاپ آپ منو
            SeekBar seekBar = popupView.findViewById(R.id.menu_item_seekbar);

        // اضافه کردن listener به seekbar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView dec1 = findViewById(R.id.lessonDec1);
                TextView dec2 = findViewById(R.id.lessonDec2);
                dec1.setTextSize((float)progress);
                dec2.setTextSize((float)progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.showAsDropDown(anchor);
    }}


}