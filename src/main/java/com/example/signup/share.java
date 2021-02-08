package com.example.signup;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class share extends AppCompatActivity {

    ImageView mail;
    ImageView fb;
    ImageView whatsapp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        mail = findViewById(R.id.imageView13);
        fb = findViewById(R.id.imageView14);
        whatsapp = findViewById(R.id.imageView15);

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gooUrl("https://mail.google.com/mail/u/0/");
            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gooUrl("https://www.facebook.com/");
            }
        });

        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gooUrl("https://web.whatsapp.com/");
            }
        });

    }

    private void gooUrl(String s) {
        Uri uri= Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));

    }
}