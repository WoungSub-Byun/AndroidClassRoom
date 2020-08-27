package com.example.chapter12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    EditText sqlTitle, sqlContents;

    DBHelper helper;    //테이블 생성, 스키마 변경 등의 작업을 도와주는 클래스
    SQLiteDatabase db;  // 데이터베이스 저장, 수정, 삭제 등 모든 SQL 질의문 처리

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = findViewById(R.id.btnAdd);
        sqlContents = findViewById(R.id.sqlContents);
        sqlTitle = findViewById(R.id.sqlTitle);

        //버튼을 클릭하면 editText의 내용을 DB에 저장
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = sqlTitle.getText().toString();
                String contents = sqlContents.getText().toString();

                try {
                    helper = new DBHelper(MainActivity.this);
                    db = helper.getWritableDatabase();
                    String sql = String.format("INSERT INTO test_table (title, contents) VALUES('%s','%s')",
                            title, contents);
                    db.execSQL(sql);
                }catch (Exception e){
                    Log.d("DatabaseError",e.toString());
                }finally {
                    db.close();
                }
                Intent intent = new Intent(getApplicationContext(), ShowDBActivity.class);
                startActivity(intent);
            }
        });
    }
}