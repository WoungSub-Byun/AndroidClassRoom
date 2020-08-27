package com.example.test1_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView result;
    Button btnAdd, btnDis, btnMul, btnDiv;
    EditText num1, num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        btnAdd = findViewById(R.id.btnAdd);
        btnDis = findViewById(R.id.btnDis);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnAdd:
                        result.setText("계산결과: "+(Integer.parseInt(num1.getText().toString())+Integer.parseInt(num2.getText().toString())));
                        break;
                    case R.id.btnDis:
                        result.setText("계산결과: "+(Integer.parseInt(num1.getText().toString())-Integer.parseInt(num2.getText().toString())));
                        break;
                    case R.id.btnMul:
                        result.setText("계산결과: "+(Integer.parseInt(num1.getText().toString())*Integer.parseInt(num2.getText().toString())));
                        break;
                    case R.id.btnDiv:
                        result.setText("계산결과: "+(Integer.parseInt(num1.getText().toString())/Integer.parseInt(num2.getText().toString())));
                        break;
                }
            }
        };

        btnAdd.setOnClickListener(listener);
        btnDis.setOnClickListener(listener);
        btnMul.setOnClickListener(listener);
        btnDiv.setOnClickListener(listener);
    }


}
