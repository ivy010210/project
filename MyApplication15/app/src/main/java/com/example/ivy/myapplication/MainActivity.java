package com.example.ivy.myapplication;

import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback,SensorEventListener{
    private ImageView imageView;
    private float currentDegree = 0f;
    private SensorManager mSensorManager;
    TextView testView;

    Camera camera;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    private Button btn_start;
    private Button btn_stop;
    private Button surfaceView1;

    private final String tag = "VideoServer";

    Button start, stop;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.compass_imageView);


        start = (Button) findViewById(R.id.btn_start);
        start.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {
                start_camera();
            }
        });
        stop = (Button) findViewById(R.id.btn_stop);
        stop.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {
                stop_camera();
            }
        });

        surfaceView = (SurfaceView) findViewById(R.id.surfaceView1);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);


        // 传感器管理器
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);

  /*
     註冊Sensor Listener (registerListener)
     第一個參數: Sensor.TYPE_ORIENTATION(方向傳感器);
     第二個參數:
     SENSOR_DELAY_FASTEST(0毫秒延遲);
     SENSOR_DELAY_GAME(20,000毫秒延遲)
     SENSOR_DELAY_UI(60,000毫秒延遲))
   */
        sm.registerListener(MainActivity.this,
                sm.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_FASTEST);

    }

    private void start_camera()
    {
        try{
            camera = Camera.open();
        }catch(RuntimeException e){
            Log.e(tag, "init_camera: " + e);
            return;
        }
        Camera.Parameters param;
        param = camera.getParameters();
        //modify parameter
        param.setPreviewFrameRate(20);
        //param.setPreviewSize(176,144);
//        param.setPreviewFormat();
        camera.setParameters(param);
        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.setDisplayOrientation(90);
            camera.startPreview();
        } catch (Exception e) {
            Log.e(tag, "init_camera: " + e);
            return;
        }
    }

    private void stop_camera()
    {
        camera.stopPreview();
        camera.release();
    }

    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
    }

    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
            float degree = event.values[0];


            RotateAnimation ra = new RotateAnimation(
                    currentDegree, // 動畫起始時物件的角度
                    -degree,       // 動畫結束時物件旋轉的角度(可大於360度)-表示逆時針旋轉,+表示順時針旋轉
                    Animation.RELATIVE_TO_SELF, 0.5f, //動畫相對於物件的X座標的開始位置, 從0%~100%中取值, 50%為物件的X方向坐標上的中點位置
                    Animation.RELATIVE_TO_SELF, 0.5f); //動畫相對於物件的Y座標的開始位置, 從0%~100%中取值, 50%為物件的Y方向坐標上的中點位置

            ra.setDuration(210); // 旋轉過程持續時間
            ra.setRepeatCount(-1); // 動畫重複次數 (-1 表示一直重複)
            imageView.startAnimation(ra); // 羅盤圖片使用旋轉動畫
            currentDegree = -degree; // 保存旋轉後的度數, currentDegree是一個在類中定義的float類型變量
        }
    }

    // 傳感器精度的改變
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}