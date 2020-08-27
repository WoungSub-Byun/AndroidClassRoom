package com.example.test3_test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView selectedItemContent;
    Button btnAdd, btnEdit, btnDel;
    ListView listView;

    int selectedNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("2105변웅섭");

        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);
        btnDel = findViewById(R.id.btnDel);
        selectedItemContent = findViewById(R.id.selecteditemcontent);
        listView = findViewById(R.id.listView);

        final ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("리스트 데이터1");
        arrayList.add("리스트 데이터2");
        arrayList.add("리스트 데이터3");
        arrayList.add("리스트 데이터4");
        arrayList.add("리스트 데이터5");

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, arrayList);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selectedItemContent.setText(arrayList.get(position));
                selectedNum = position;
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.add("리스트데이터"+(arrayList.size()+1));
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText et = new EditText(getApplicationContext());

                AlertDialog.Builder alt = new AlertDialog.Builder(MainActivity.this)
                        .setCancelable(false)
                        .setTitle("리스트아이템수정")
                        .setNegativeButton("취소",null)
                        .setMessage("현재 데이터: 리스트데이터"+(selectedNum+1))
                        .setIcon(R.mipmap.ic_launcher_round)
                        .setPositiveButton("수정", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                arrayList.add(selectedNum, et.getText().toString());
                                arrayList.remove(selectedNum+1);
                                adapter.notifyDataSetChanged();
                            }
                        });
                alt.setView(et);
                alt.show();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.remove(selectedNum);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
