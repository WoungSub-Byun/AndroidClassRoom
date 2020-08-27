package com.example.intentpractice2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.crypto.Mac;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSend = findViewById(R.id.btnSend);
        editText = findViewById(R.id.editText);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                intent.putExtra("value",editText.getText().toString());
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        Toast.makeText(getApplicationContext(), intent.getStringExtra("value"),Toast.LENGTH_SHORT).show();


    }

}
