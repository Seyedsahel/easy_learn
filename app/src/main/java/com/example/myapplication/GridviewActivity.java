package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GridviewActivity extends AppCompatActivity {
    int[] imageArray = {R.mipmap.pic1 , R.mipmap.pic2 , R.mipmap.pic3 , R.mipmap.pic4 , R.mipmap.pic5 , R.mipmap.devepic};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gridview);

        GridView gridView = findViewById(R.id.gridView);
        GridAdapter adapter = new GridAdapter(imageArray , GridviewActivity.this);
        gridView.setAdapter(adapter);

    }}
