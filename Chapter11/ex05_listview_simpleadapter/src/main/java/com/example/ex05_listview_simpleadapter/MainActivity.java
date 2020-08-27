package com.example.ex05_listview_simpleadapter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    ListView listView;

    int[] imgResArray = {R.drawable.kakao01,R.drawable.kakao02,R.drawable.kakao03,R.drawable.kakao04,R.drawable.kakao05,R.drawable.kakao06,
            R.drawable.kakao07,R.drawable.kakao08,R.drawable.kakao09};

    ArrayList<String> titleData = getStringListForListView("title",9);
    ArrayList<String> contentsData = getStringListForListView("contents", 9);


    //String[] dataArray = getStringArrayForListView(100);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        listView = findViewById(R.id.listView);

        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
        for (int i = 0; i< titleData.size(); i++){
            HashMap<String,Object> map = new HashMap<>();
            map.put("img", imgResArray[i]);
            map.put("title", titleData.get(i));
            map.put("contents", contentsData.get(i));

            arrayList.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                arrayList,
                R.layout.row,
                new String[]{"img","title", "contents"},
                new int[]{R.id.imageView,R.id.textViewTitle,R.id.textViewContents}
        );
        listView.setAdapter(adapter);

        // 리스트뷰의 특정 항목을 클릭했을 때 발생하는 이벤트 처리
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 화면의 TextView에 선택된 항목의 데이터 출력하기
                //textView.setText(dataList.get(position));
                textView.setText(titleData.get(position) + ", " + contentsData.get(position));
            }
        });
    }
    private ArrayList<String> getStringListForListView(String str ,int count) {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 1; i<=count; i++){
            list.add(str + i);
        }
        return list;
    }
    /*private String[] getStringArrayForListView(int count) {
        String[] strings = new String[count];
        for (int i = 0; i<count; i++){
            strings[i] = "배열 데이터" + (i+1);

        }
        return strings;
    }*/
}