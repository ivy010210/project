package com.example.ivy.newgroup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Button button1;
    TextView hiword;
    ImageView image;
    private Button button_add;
    EditText edittext_gName;
    String gPic;

    String uEmail = "sandy@gmail.com";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gPic = getIntent().getStringExtra("sayHi");
        Toast.makeText(MainActivity.this, "測試" + gPic, Toast.LENGTH_SHORT).show();
        image = (ImageView) findViewById(R.id.imageView4);


        String uri = "@drawable/" + gPic; //圖片路徑和名稱

        int imageResource = getResources().getIdentifier(uri, null, getPackageName()); //取得圖片Resource位子

        image.setImageResource(imageResource);


        button1 = (Button) findViewById(R.id.button4);

        //實做OnClickListener界面
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNextPage();
            }
        });

        button_add = (Button) findViewById(R.id.button_add);

        //實做OnClickListener界面
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGroup();
            }
        });
        edittext_gName = (EditText) findViewById(R.id.editText_gName);

}

    /**
     * 開啟Main2Activity之用
     */
    private void startNextPage() {
        Intent intent = new Intent();
        intent.setClass(this, ChangePic.class);
        startActivity(intent);
        finish();
    }

    private void addGroup() {
        final String gName = edittext_gName.getText().toString().trim();

        class AddGroup extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Adding...","Wait...",false,false);
                //這裡把MainActivity改為相對應的java檔名就好
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put("gName",gName);
                params.put("uEmail",uEmail);
                params.put("gPic",gPic);
                //params.put(php檔內的接收變數  $_POST["___"] , 要傳給php檔的java變數)

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_CREATE_GROUP, params);
                //String res = rh.sendPostRequest("php檔的網址", params);
                //URL_ADD 是在 Config.java設定好的字串 也就是 http://140.117.71.114/employee/addEmp.php
                //php檔可在ftp上傳下載
                return res;
            }
        }


        //這兩行不用理
        AddGroup ae = new AddGroup();
        ae.execute();



    }

}


