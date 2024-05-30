package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editTextFullName = findViewById(R.id.editTextFullName);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);
        EditText editTextRepeatPassword = findViewById(R.id.editTextRepeatPassword);
        EditText editTextUserName = findViewById(R.id.editTextUserName);
        TextView erromsg = findViewById(R.id.errorMessage);
        Button buttonSignUp = findViewById(R.id.buttonSignUp);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(String.valueOf(editTextPassword.getText()).equals(String.valueOf(editTextRepeatPassword.getText())))
                {
                    SharedPreferences.Editor editor = getSharedPreferences("MY_PREF",MODE_PRIVATE).edit();
                    editor.putString("USERNAME", "admin");
                    editor.putString("PASSWORD", "123");
                    editor.putBoolean("IS_LOGGED_IN", true);
                    editor.putString("NAME",String.valueOf(editTextFullName.getText()));
                    editor.putString("EMAIL",String.valueOf(editTextEmail.getText()));
                    editor.apply();

                    Toast.makeText(RegisterActivity.this , "ثبت نام با موفقیت انجام شد" , Toast.LENGTH_LONG).show();
                    Intent i = new Intent(RegisterActivity.this , LoginActivity.class);
                    i.putExtra("USERNAME",String.valueOf(editTextUserName.getText()));
                    i.putExtra("PASSWORD",String.valueOf(editTextPassword.getText()));
                    startActivity(i);
                    finish();
                }
                else
                    erromsg.setText("پسورد با تکرار مطابقت ندارد!");

            }
        });



    }
}