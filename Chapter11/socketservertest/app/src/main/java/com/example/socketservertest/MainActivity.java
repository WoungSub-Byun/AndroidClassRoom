package com.example.socketservertest;

import android.hardware.usb.UsbEndpoint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.Buffer;


public class MainActivity extends AppCompatActivity {

    TextView textView, result;
    EditText editText;
    Button btnSend;

    ValueHandler handler = new ValueHandler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        result = findViewById(R.id.result);
        editText = findViewById(R.id.editText);
        btnSend = findViewById(R.id.btnSend);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClientThread thread = new ClientThread();
                thread.start();
            }
        });
    }

    class ClientThread extends Thread {
        String host = "192.168.1.8";
        int port = 9999;

        ClientThread(){ }

        @Override
        public void run() {
            try {
                Socket socket = new Socket(host, port);

                //region Text입출력
                //region text값 전송 => Printwriter 사용
//                OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
//                BufferedWriter writer = new BufferedWriter(osw);
//                writer.write("Hello!");
//                writer.flush();
                //endregion
                //region Server로부터 온 text값 받아서 출력
//                InputStreamReader isr = new InputStreamReader(socket.getInputStream());
//                BufferedReader reader = new BufferedReader(isr);
//                String input = reader.readLine();
                //endregion
                //endregion

                //region Object 값 수신 => ObjectInputStream 사용
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                final Object input = inputStream.readObject();
                Log.d("ClientThread","Received data: "+input.toString());
                //endregion

                //region Object 값 전송 => ObjectOutputStream 사용
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(editText.getText().toString());
                outputStream.flush();
                Log.d("ClientStream","Send to server ...");
                //endregion

                Bundle bundle = new Bundle();
                bundle.putString("value",input.toString());

                Message msg = handler.obtainMessage();
                msg.setData(bundle);
                handler.sendMessage(msg);

            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    class ValueHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String value = msg.getData().getString("value");
            result.setText(value);
        }
    }


}
