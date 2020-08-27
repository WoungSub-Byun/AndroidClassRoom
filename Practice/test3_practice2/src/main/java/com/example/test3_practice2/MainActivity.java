package com.example.test3_practice2;

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

    Button btnAdd, btnDel, btnEdit;
    TextView selecteItemContent;
    ListView listView;

    int selectedNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("2105변웅섭");

        btnAdd = findViewById(R.id.btnAdd);
        btnDel = findViewById(R.id.btnDel);
        btnEdit = findViewById(R.id.btnEdit);
        selecteItemContent = findViewById(R.id.selectedItemcontent);
        listView = findViewById(R.id.listView);

        final ArrayList<String> dataSet = new ArrayList<>();
        dataSet.add("리스트데이터1");
        dataSet.add("리스트데이터2");
        dataSet.add("리스트데이터3");
        dataSet.add("리스트데이터4");
        dataSet.add("리스트데이터5");

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_single_choice,dataSet);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selecteItemContent.setText("리스트데이터"+(position+1));
                selectedNum = position;
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.add("리스트데이터"+(dataSet.size()+1));
                adapter.notifyDataSetChanged();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText et = new EditText(getApplicationContext());
                String data;
                AlertDialog.Builder alt = new AlertDialog.Builder(MainActivity.this)
                        .setIcon(R.mipmap.ic_launcher_round)
                        .setTitle("리스트아이템수정")
                        .setNegativeButton("취소",null)
                        .setMessage("현재 데이터: 리스트데이터"+(selectedNum+1))
                        .setCancelable(false)
                        .setPositiveButton("수정", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dataSet.add(selectedNum, et.getText().toString());
                                selecteItemContent.setText(et.getText().toString());
                                dataSet.remove(selectedNum+1);
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
                dataSet.remove(selectedNum);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
