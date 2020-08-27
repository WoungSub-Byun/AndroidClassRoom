package com.example.serverpractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText iptext, message;
    Button btnOpenServer, btnConnectServer,btnSendMessage;
    TextView result;

    ClientThread client;
    MainHandler handler = new MainHandler();
    int port = 9999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpenServer = findViewById(R.id.btnOpenServer);
        iptext = findViewById(R.id.iptext);
        message = findViewById(R.id.message);
        btnConnectServer = findViewById(R.id.btnConnectServer);
        result = findViewById(R.id.result);
        btnSendMessage = findViewById(R.id.btnSendMessage);
        btnSendMessage.setEnabled(false);

        btnOpenServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServerThread thread = new ServerThread();
                thread.start();
            }
        });

        btnConnectServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                client = new ClientThread(iptext.getText().toString());
                client.start();
                btnSendMessage.setEnabled(true);
            }
        });
        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
        //region ServerThread
        class ServerThread extends Thread {

            @Override
            public void run() {
                try {
                    ServerSocket server = new ServerSocket(port);
                    Log.d("ServerThread", "Server is running on port "+port);

                    while(true){
                        Socket socket = server.accept();
                        Log.d("SererThread","["+socket.getInetAddress().toString()+"] is Connected");

                        ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());
                        Object input = instream.readObject();
                        Log.d("ServerThread", "input: " + input);

                        ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream());
                        outstream.writeObject(input + " from server.");
                        outstream.flush();
                        Log.d("ServerThread", "output sent.");

                        socket.close();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //endregion


    class ClientThread extends Thread {
        String host;
        int port = 9999;
        Socket socket;
        ObjectOutputStream outputStream;
        ObjectInputStream inputStream;

        ClientThread(){ }
        ClientThread(String host){
            this.host = host;
        }

        @Override
        public void run() {
            try {
                socket = new Socket(host, port);

                //region Object 값 수신 => ObjectInputStream 사용
                inputStream = new ObjectInputStream(socket.getInputStream());
                Object input = inputStream.readObject();
                Log.d("ClientThread","Received data: "+input.toString());
                //endregion
                
                Bundle bundle = new Bundle();
                bundle.putString("value",input.toString());

                Message msg = handler.obtainMessage();
                msg.setData(bundle);
                handler.sendMessage(msg);

            } catch (Exception e){
                try {
                    socket.close();
                } catch (Exception err){
                    err.printStackTrace();
                }
                e.printStackTrace();
            }
        }

        public void sendMessage(String msg){
            try {
                //region Object 값 전송 => ObjectOutputStream 사용
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(msg);
                outputStream.flush();
                Log.d("ClientStream","Send to server ...");
                //endregion
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    class MainHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String data = msg.getData().toString();

        }
    }
}
