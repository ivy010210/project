package com.example.ivy.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button1;
    TextView hiword;
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String temp=getIntent().getStringExtra("sayHi");
        Toast.makeText(MainActivity.this, "測試" + temp, Toast.LENGTH_SHORT).show();
        image = (ImageView) findViewById(R.id.imageView4);


        String uri = "@drawable/" + temp; //圖片路徑和名稱

        int imageResource = getResources().getIdentifier(uri, null, getPackageName()); //取得圖片Resource位子

        image.setImageResource(imageResource);



//        int hi =   getIntent().getIntExtra("sayHi", 123);
//        hiword = (TextView) findViewById(R.id.textView4);
//        hiword.setText(Integer.toString(hi));//測試


//        String showtest=intent.getStringExtra("test");
//
//        Toast.makeText(MainActivity.this,showtest.toString(), Toast.LENGTH_LONG).show();
        button1 = (Button)findViewById(R.id.button4);

        //實做OnClickListener界面
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNextPage();
            }
        });
    }

    /**
     * 開啟Main2Activity之用
     *
     */
    private void startNextPage(){
        Intent intent = new Intent();
        intent.setClass(this , ChangePic.class);
        startActivity(intent);
        finish();
    }}