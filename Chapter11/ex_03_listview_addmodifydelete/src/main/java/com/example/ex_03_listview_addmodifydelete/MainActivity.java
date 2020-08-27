package com.example.ex_03_listview_addmodifydelete;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    ListView listView;
    Button btnAdd, btnModify, btnDelete;
    ArrayAdapter adapter;
    //리스트뷰에 표시할 데이터
    ArrayList<String> dataList = getStringListForListView(5);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btn_add);
        btnModify = findViewById(R.id.btn_modify);
        btnDelete = findViewById(R.id.btn_delete);

        //ArrayAdapter 객체 생성
        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_single_choice,
                dataList);

        //리스트뷰의 각 항목이 라디오 버튼으로 되었음을 설정함.
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //리스트뷰에 adapter 연결
        listView.setAdapter(adapter);
        //리스트뷰의 특정 항목을 클릭했을 때 발생하는 이벤트 처리
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //화면의 TextView에 선택된 항목의 데이터 출력하기
                textView.setText(dataList.get(position));
            }
        });
        //버튼 클릭 이벤트 처리 리스너 연결
        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnModify.setOnClickListener(this);

    }

    private ArrayList<String> getStringListForListView(int count) {
        ArrayList<String> list = new ArrayList<String>();
        for(int i=0;i<count;i++){
            list.add("리스트 데이터"+(i+1));
        }
        return list;
    }


    @Override
    public void onClick(View view) {
        final int checkedIndex = listView.getCheckedItemPosition();
        switch (view.getId()){
            case R.id.btn_add:
                int count = adapter.getCount(); //어댑터에 연결되어 있는 데이터의 개수 저장
                dataList.add("리스트 데이터"+(count+1));
                break;
            case R.id.btn_modify:
                final EditText editText = new EditText(getApplicationContext());
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("리스트 아이템 수정")
                        .setMessage("현재 데이터 : "+ dataList.get(checkedIndex))
                        .setIcon(R.mipmap.ic_launcher)
                        .setCancelable(false)
                        .setView(editText)
                        .setPositiveButton("수정", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dataList.set(checkedIndex,editText.getText().toString());
                            }
                        })
                        .setNegativeButton("취소",null)
                        .show();
                break;
            case R.id.btn_delete:
                dataList.remove(checkedIndex);
                break;

        }
        adapter.notifyDataSetChanged(); //수정된 상태 반영
        listView.clearChoices();
    }
}
