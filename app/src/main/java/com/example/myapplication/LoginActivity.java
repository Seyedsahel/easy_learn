package com.example.myapplication;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences user_prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // دریافت اطلاعات از حافظه shared preferences
        SharedPreferences sp = getSharedPreferences("MY_PREF", MODE_PRIVATE);
        String username = sp.getString("USERNAME", null);
        String password = sp.getString("PASSWORD", null);
        EditText usernameEdt = findViewById(R.id.usernameEdt);
        EditText passwordEdt = findViewById(R.id.passwordEdt);
        usernameEdt.setText(username);
        passwordEdt.setText(password);

        boolean isLoggedIn = sp.getBoolean("IS_LOGGED_IN", false);
        if (isLoggedIn) {
            // انتقال از یک اکتیویتی به اکیتیویتی دیگر به کمک اینتنت
            Intent i = new Intent(LoginActivity.this, MainActivity.class);

            // ارسال اطلاعات دلخواه به اکتیویتی مقصد در صورت نیاز
            i.putExtra("USERNAME", username);
            i.putExtra("PASSWORD", password);
            i.putExtra("IS_VALID_LOGIN", true);

            startActivity(i);
            finish();
        }
    }

    public void doLogin(View view) {
        EditText username = findViewById(R.id.usernameEdt);
        EditText password = findViewById(R.id.passwordEdt);
        CheckBox rememberMe = findViewById(R.id.rememberMeChbx);

        String user =  String.valueOf( username.getText() );
        String pass =  String.valueOf( password.getText() );

        if(user.equals("admin") && pass.equals("123"))
        {
            Toast.makeText(LoginActivity.this , "به برنامه EASY LEARN خوش آمدید" , Toast.LENGTH_LONG).show();

            if(rememberMe.isChecked()) {
                // ذخیره اطلاعات در حافظه sharedpreferences
                SharedPreferences sp = getSharedPreferences("MY_PREF", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("USERNAME", user);
                editor.putString("PASSWORD", pass);
                editor.putBoolean("IS_LOGGED_IN", true);
                editor.apply();
            }
            // انتقال از یک اکتیویتی به اکیتیویتی دیگر به کمک اینتنت
            Intent i = new Intent(LoginActivity.this , MainActivity.class);

            // ارسال اطلاعات دلخواه به اکتیویتی مقصد در صورت نیاز
            i.putExtra("USERNAME", user);
            i.putExtra("PASSWORD" , pass);
            i.putExtra("IS_VALID_LOGIN",true);

            startActivity(i);
            finish();
        }
        else {TextView message = findViewById(R.id.errorMessage);
            message.setText("نام کاربری یا رمز عبور اشتباه است ! اگر اکانت ندارید ثبت نام کنید");}
    }
    public void doRegister(View view) {
        EditText username = findViewById(R.id.usernameEdt);
        EditText password = findViewById(R.id.passwordEdt);

        String user =  String.valueOf( username.getText() );
        String pass =  String.valueOf( password.getText() );

        Intent i = new Intent(LoginActivity.this , RegisterActivity.class);

        i.putExtra("USERNAME", user);
        i.putExtra("PASSWORD" , pass);
        i.putExtra("IS_VALID_LOGIN",true);
        startActivity(i);
    }
}

