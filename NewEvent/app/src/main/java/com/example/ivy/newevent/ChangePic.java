package com.example.ivy.newevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChangePic extends AppCompatActivity {


    private GridView gridView;
    private int[] image = {
            R.drawable.book,R.drawable.camera,R.drawable.coffee,R.drawable.coins,R.drawable.folder,R.drawable.folder_2,
            R.drawable.headphones,R.drawable.headphones2,R.drawable.key,R.drawable.laptop,R.drawable.lipstick,R.drawable.money_bag,R.drawable.pencil,
            R.drawable.phone,R.drawable.rucksack,R.drawable.shopping_bag,R.drawable.smartphone,R.drawable.ticket,R.drawable.trainers,
            R.drawable.umbrella,R.drawable.wallet,R.drawable.watch,R.drawable.water
    };
    private String[] imgText = {
            "book","camera","coffee","coins","folder","folder_2","headphones","headphones2","key",
            "laptop","lipstick","money_bag","pencil","phone","rucksack","shopping_bag","smartphone"
            ,"ticket","trainers","umbrella","wallet","watch","water"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pic);
        List<Map<String, Object>> items = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("image", image[i]);
            item.put("text", imgText[i]);
            items.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,
                items, R.layout.grid_item, new String[]{"image", "text"},
                new int[]{R.id.image, R.id.text});
        gridView = (GridView)findViewById(R.id.main_page_gridview);
        gridView.setNumColumns(3);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(ChangePic.this, "你選擇了" + imgText[position], Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(ChangePic.this,MainActivity.class);
                intent.putExtra("sayHi",imgText[position]);//試著傳值
                finish();


				/*
				 * 這邊是你要帶過去的資料
				 */

                startActivity(intent);
            }
        });
    }
}



