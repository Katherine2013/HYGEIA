package com.example.hygeia.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hygeia.R;

//任务详情
public class DetailActivity extends Activity {

    private Button mBtnMap;
    private Button mBtnGet;
    private Button mBtnLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置全屏
        setContentView(R.layout.activity_task_detail);
        // 初始化控件

        String index = getIntent().getStringExtra("index");
        String publisher = getIntent().getStringExtra("publisher");
        String location = getIntent().getStringExtra("location");
        String title = getIntent().getStringExtra("title");
        String reward = getIntent().getStringExtra("reward");
        String time = getIntent().getStringExtra("time");
        String detail = getIntent().getStringExtra("detail");
        String priority = getIntent().getStringExtra("priority");
        String label = getIntent().getStringExtra("label");
        String place = getIntent().getStringExtra("place");


        TextView name_tv = (TextView) findViewById(R.id.name_tv);
        name_tv.setText(publisher);
        TextView p_tv = (TextView) findViewById(R.id.p_tv);
        p_tv.setText(location);
        TextView tv_name = (TextView) findViewById(R.id.tv_name);
        tv_name.setText("Name："+title);
        TextView tv_reward = (TextView) findViewById(R.id.tv_reward);
        tv_reward.setText(reward);
        TextView tv_time = (TextView) findViewById(R.id.tv_time);
        tv_time.setText(time);
        TextView tv_detail = (TextView) findViewById(R.id.tv_detail);
        tv_detail.setText("Task details: "+detail);
        TextView tv_priority = (TextView) findViewById(R.id.tv_priority);
        tv_priority.setText("Task priority: "+priority);
        TextView tv_label = (TextView) findViewById(R.id.tv_label);
        tv_label.setText("Task label: "+label);
        TextView tv_place = (TextView) findViewById(R.id.tv_place);
        tv_place.setText("Task's loaction: "+place);

        mBtnMap=findViewById(R.id.btn_map);
        mBtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DetailActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });
        mBtnGet=findViewById(R.id.btn_get);
        mBtnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailActivity.this,"successful！",Toast.LENGTH_SHORT).show();
            }
        });
        mBtnLocation=findViewById(R.id.btn_location);
        mBtnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DetailActivity.this,GetLocationActivity.class);
                startActivity(intent);
            }
        });
    }
}
