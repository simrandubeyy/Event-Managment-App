package com.example.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public Button button1;
    public Button button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         button1 = findViewById(R.id.button1);
         button2 = findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //Will handle click here
                //Build an Intent to open another activity
                Intent intent = new Intent(MainActivity.this, Log.class);
                startActivity(intent);
            }
        });

      button2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              Intent intent1=new Intent(MainActivity.this, Signup.class);
              startActivity(intent1);
          }
      });
    }
}