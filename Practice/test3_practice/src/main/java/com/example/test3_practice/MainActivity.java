package com.example.test3_practice;

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

    TextView selectedListItem;
    ListView listview;
    Button addBtn, editBtn, delBtn;

    int selNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedListItem = findViewById(R.id.selectedListItem);
        listview = findViewById(R.id.listView);
        addBtn = findViewById(R.id.addBtn);
        editBtn = findViewById(R.id.editBtn);
        delBtn = findViewById(R.id.delBtn);

        final ArrayList<String> dataSet = new ArrayList<>();
        dataSet.add("리스트 데이터1");
        dataSet.add("리스트 데이터2");
        dataSet.add("리스트 데이터3");
        dataSet.add("리스트 데이터4");
        dataSet.add("리스트 데이터5");

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_single_choice, dataSet);

        listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selectedListItem.setText(dataSet.get(position));
                selNum = position;
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataSet.add("리스트데이터"+(dataSet.size()+1));
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText et = new EditText(getApplicationContext());

                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("리스트아이템수정")
                        .setMessage(dataSet.get(selNum))
                        .setIcon(R.mipmap.ic_launcher_round)
                        .setNegativeButton("취소",null)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                dataSet.add(selNum, et.getText().toString());
                                dataSet.remove(selNum+1);
                                adapter.notifyDataSetChanged();
                            }
                        });

                dlg.setView(et);
                dlg.show();
            }
        });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataSet.remove(selNum);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
