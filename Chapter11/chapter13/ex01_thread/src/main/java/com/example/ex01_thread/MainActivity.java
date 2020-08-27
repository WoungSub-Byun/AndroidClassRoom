package com.example.ex01_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    ProgressBar pb1, pb2;
    Button btnStartThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb1 = findViewById(R.id.progressbar1);
        pb2 = findViewById(R.id.progressbar2);
        btnStartThread = findViewById(R.id.btnStartThread);

//        // region
//        btnStartThread.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                for(int i=0;i<10;i++){
//                    pb1.setProgress(pb1.getProgress()+2);
//                    pb2.setProgress(pb2.getProgress()+1);
//                    SystemClock.sleep(100);
//                }
//            }
//        });
//        //endregion
        //region
//        btnStartThread.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ProgressThread pt1 = new ProgressThread(pb1,2);
//                pt1.start();
//                ProgressThread pt2 = new ProgressThread(pb2,1);
//                pt2.start();
//            }
//        });
        //endregion
        //region3. 익명의 Tread 객체 구현을 이용한 처리
        btnStartThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                    for(int i =pb1.getProgress();i<100;i++){
                                pb1.setProgress(pb1.getProgress()+ 2);
                                SystemClock.sleep(100);
                            }
                    }
                }.start();

                new Thread(){
                    @Override
                    public void run() {
                        for(int i =pb2.getProgress();i<100;i++){
                            pb2.setProgress(pb2.getProgress()+ 2);
                            SystemClock.sleep(100);
                        }
                    }
                }.start();
            }
        });
        //endregion

    }

    private class ProgressThread extends Thread{
        ProgressBar pb;
        int increaseValue;

        public ProgressThread(ProgressBar pb, int increaseValue){
            this.pb = pb;
            this.increaseValue = increaseValue;
        }
        @Override
        public void run() {
            for(int i =pb.getProgress();i<100;i++){
                pb.setProgress(pb.getProgress()+ increaseValue);
                SystemClock.sleep(100);
            }
        }
    }
}
