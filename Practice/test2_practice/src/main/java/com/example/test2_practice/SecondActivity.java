package com.example.test2_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    TextView title;
    RadioGroup radioGroup;
    Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        title = findViewById(R.id.title);
        radioGroup = findViewById(R.id.radiogroup);
        btnReturn = findViewById(R.id.btnReturn);

        final Intent intent = getIntent();
        title.setText(intent.getStringExtra("value"));

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1;
                String data = "";
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.call:
                        data = "call";
                        break;
                    case R.id.camera:
                        data = "camera";
                        break;
                }
                intent1 = new Intent(getApplicationContext(), MainActivity.class);
                intent1.putExtra("value",data);
                setResult(RESULT_OK,intent1);
                finish();
            }
        });

    }
}
