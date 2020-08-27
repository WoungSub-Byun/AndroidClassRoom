package com.example.ex04_simpleadapter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ListView listView;

    //리스트뷰에 표시할 데이터
    ArrayList<String> titleData = getStringListForListView("title",100);
    ArrayList<String> contentsData = getStringListForListView("contents",100);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        listView = findViewById(R.id.listView);

        //SimpleAdapter에 연결할 List 생성
        ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
        for (int i =0;i<titleData.size();i++) {
            HashMap<String, String> map = new HashMap<>();
            map.put("title", titleData.get(i));
            map.put("contents", contentsData.get(i));

            arrayList.add(map);
        }


        //ArrayAdapter 객체 생성
        SimpleAdapter adapter = new SimpleAdapter(this,
                        arrayList,
                        android.R.layout.simple_list_item_2,
                new String[] {"titles","contents"},
                new int[]{android.R.id.text1, android.R.id.text2}
                        );



        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //리스트뷰에 adapter 연결
        listView.setAdapter(adapter);
        //리스트뷰의 특정 항목을 클릭했을 때 발생하는 이벤트 처리
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //화면의 TextView에 선택된 항목의 데이터 출력하기
                textView.setText(titleData.get(position)+",  "+contentsData.get(position));
            }
        });
    }

    private ArrayList<String> getStringListForListView(String contents, int count) {
        ArrayList<String> list = new ArrayList<String>();
        for(int i=0;i<count;i++){
            list.add("리스트 데이터"+i);
        }
        return list;
    }


}
