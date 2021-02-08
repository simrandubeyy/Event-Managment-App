package com.example.signup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Connect extends AppCompatActivity {


    private EditText input;
    private Button btn;
    private DatabaseReference rootdatabaseref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        input=findViewById(R.id.editText);
        btn= findViewById(R.id.button5);

        rootdatabaseref= FirebaseDatabase.getInstance().getReference().child("Mydata");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String data = input.getText().toString();

               rootdatabaseref.setValue(data);
            }
        });
    }
}