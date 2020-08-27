package com.example.ex07_listview_customadapter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ListView listView;
    Button btnAdd;

    ArrayList<ItemVO> dataList = new ArrayList<ItemVO>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btnAdd);

        // dataList에 초기 데이터 추가
        dataList.add(new ItemVO("doc", "Document 1", "Sample Data"));
        dataList.add(new ItemVO("img", "Image 1", "Sample Data"));
        dataList.add(new ItemVO("file", "File 1", "Sample Data"));

        // CustomAdapter 생성
        final CustomAdapter adapter = new CustomAdapter(this, R.layout.row, dataList);

        // 리스트뷰에 어댑터 연결
        listView.setAdapter(adapter);

        //region 추가 버튼 클릭 이벤트 설정
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = getLayoutInflater();
                final View myView = inflater.inflate(R.layout.alertdialog_view, null);
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("리스트 아이템 추가")
                        .setIcon(R.mipmap.ic_launcher)
                        .setView(myView)
                        .setCancelable(false)
                        .setNegativeButton("취소", null)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //데이터 저장용 임시 변수 선언
                                String type = null, title, content;

                                //대화상자에서 입력된 데이터를 가져와 저장하기
                                int checkedId = ((RadioGroup)myView.findViewById(R.id.radioGroup)).getCheckedRadioButtonId();
                                switch (checkedId){
                                    case R.id.rDocu:
                                        type = "doc";
                                        break;
                                    case R.id.rFile:
                                        type = "img";
                                        break;
                                    case R.id.rImg:
                                        type = "file";
                                        break;
                                }
                                title = ((EditText)myView.findViewById(R.id.editTextTitle)).getText().toString();
                                content = ((EditText)myView.findViewById(R.id.editTextContent)).getText().toString();

                                dataList.add(new ItemVO(type, title, content));
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .show();
            }
        });
        //endregion

        //리스트뷰의 아이템 클릭 이벤트 설정
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                textView.setText(dataList.get(position).getTitleStr());
            }
        });
    }
}