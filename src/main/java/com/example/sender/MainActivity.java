package com.example.sender;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.hardware.SensorEventListener;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private UploadTask task;
    private TextView textView;
    private float X,Y,Z;
    private TextView orientation_text;
    // wordを入れる
    private EditText editText;

    private SensorManager sensorManager;

    // phpがPOSTで受け取ったwordを入れて作成するHTMLページ(適宜合わせてください)
    String url = "http://androidweb.php.xdomain.jp/show.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        editText = findViewById(R.id.uriname);

        Button post = findViewById(R.id.post);

        // ボタンをタップして非同期処理を開始
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] param = {null,null,null,null};
                param[0] = editText.getText().toString();
                param[1] = "X :" + X;
                param[2] = "Y :" + Y;
                param[3] = "Z :" + Z;

                if (param[0].length() != 0) {
                    task = new UploadTask();
                    task.setListener(createListener());
                    task.execute(param);
                }

            }
        });

        // ブラウザを起動する
        Button browser = findViewById(R.id.browser);
        browser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // phpで作成されたhtmlファイルへアクセス
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

                // text clear
                textView.setText("");
            }
        });

        textView = findViewById(R.id.text_view);
        orientation_text = findViewById(R.id.orientation_data);
    }
    @Override
    public void onResume(){
        super.onResume();
        List<Sensor> sensor = null;
        sensor = sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
        for (Sensor s : sensor) {
            sensorManager.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
            X = event.values[0];
            Y = event.values[1];
            Z = event.values[2];

            orientation_text.setText(String.format("X : %f\nY : %f\nZ : %f" , X, Y, Z));
        }
    }

    @Override
    protected void onDestroy() {
        task.setListener(null);
        super.onDestroy();
    }

    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    private UploadTask.Listener createListener() {
        return new UploadTask.Listener() {
            @Override
            public void onSuccess(String result) {
                textView.setText(result);
            }
        };
    }
}