package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener
{

    public static class MyApplication extends Application{
        static int progressCount;
        static ProgressBar progressBar ;
        static TextView progressPer;

    }

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView navMenu;


    Gson gson;
    SharedPreferences prefs;

    ArrayList<lessonClass> lessonsInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
            //navigathin

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navMenu = findViewById(R.id.navMenu); // فایند کردن ImageView با ایدی navMenu


        // تنظیم منوی Navigation Drawer
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.inflateMenu(R.menu.nav_menu);


        // تنظیم کلیک روی آیکون منو
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // کلیک روی آیکون منو برای باز/بسته کردن Navigation Drawer
        navMenu.setOnClickListener(v -> {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


//end navigathin

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
        MyApplication.progressCount=0;
        for (int i=0;i<lessonsInfo.size();i++){
            if(lessonsInfo.get(i).getStatus())
                MyApplication.progressCount++;

        }

        MyApplication.progressBar = findViewById(R.id.progressBar);
        MyApplication.progressBar.setProgress(MyApplication.progressCount,true);

        MyApplication.progressPer = findViewById(R.id.progress_per);
        MyApplication.progressPer.setText(String.valueOf(MyApplication.progressCount)+"of 5");


    }




        @Override
    protected void onResume() {
        super.onResume();
        // Refresh the list and progress bar

        MyApplication.progressBar = findViewById(R.id.progressBar);
        MyApplication.progressPer = findViewById(R.id.progress_per);

        String json = prefs.getString("data", null);
        Type type = new TypeToken<ArrayList<lessonClass>>() {}.getType();
        lessonsInfo = gson.fromJson(json, type);

        ListView list = findViewById(R.id.listView);
        ListAdapter adapter = new ListAdapter(lessonsInfo, MainActivity.this);
        list.setAdapter(adapter);

        MyApplication.progressBar.setProgress(MyApplication.progressCount, true);

        MyApplication.progressPer.setText(String.valueOf(MyApplication.progressCount)+"of 5");

    }

    public boolean isDarkmode(){
        int nightflag = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return nightflag == Configuration.UI_MODE_NIGHT_YES;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
       if (item.getTitle().equals("dark mode on/off")){
           if(isDarkmode()){
               AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
               Toast.makeText(MainActivity.this , "Dark mode off" , Toast.LENGTH_LONG).show();
           }
           else {
               AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
               Toast.makeText(MainActivity.this, "Dark mode on", Toast.LENGTH_LONG).show();
           }
       } else if (item.getTitle().equals("About Us")) {
           Intent intent = new Intent(MainActivity.this,AboutUsActivity.class);
           startActivity(intent);
           
       } else if (item.getTitle().equals("Log out")) {
           AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
           builder.setTitle("خروج");
           builder.setMessage("آیا مایل به خروج از حساب کاربری هستید؟");
           builder.setIcon(R.drawable.logout);

           builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                   finish();
                   Intent intent = new Intent(MainActivity.this , LoginActivity.class);
                   startActivity(intent);

               }
           });
           builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int i) {
                   dialogInterface.dismiss();
               }
           });
           builder.setCancelable(false);
           builder.show();

           
       }
        return false;
    }


}



