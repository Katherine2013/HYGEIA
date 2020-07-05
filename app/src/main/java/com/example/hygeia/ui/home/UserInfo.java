package com.example.hygeia.ui.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hygeia.MyListAdapter.MyTaskAdapter;
import com.example.hygeia.R;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//个人信息界面
public class UserInfo extends Activity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        //1.拿到listview对象
        ListView lv = (ListView) this.findViewById(R.id.lv_user);

        //2.数据源
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("logo", R.drawable.icon_dog);
        map.put("title", "feed a dog");
        map.put("reward", "Reward: 20");
        map.put("state", "State: Completed");
        map.put("detail","I will leave home for one week, please help me feed my dog.feed the dog " +
                "once a day, try to do it from 10:00 to 12:00, and play with the kitten for half an hour. " +
                "Please contact me in case of any accident");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.drawable.icon_dog);
        map.put("title", "seed my dog to hospital");
        map.put("reward", "Reward: 40");
        map.put("state", "State: Received");
        map.put("detail","My dog is ill, but today I must to have a business travelI will leave home for one week." +
                " please help me seed the dog to the Mary's pets hospital " +
                "Please contact me in case of any accident");
        list.add(map);


        //3.设置适配器
//        SimpleAdapter adapter = new SimpleAdapter(
//                this,
//                list,
//                R.layout.item,
//                new String[]{"logo","title","version","size"},
//                new int[]{R.id.logo,R.id.title,R.id.version,R.id.size}
//        );

        MyTaskAdapter adapter = new MyTaskAdapter(this);
        adapter.setList(list);


        //4.关联适配器
        lv.setAdapter(adapter);

        //5
        //lv.setOnItemClickListener(this);
        //lv.setOnItemLongClickListener(this);


    }

/*
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(this,"点击"+position,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setClass(this,DetailActivity.class);

        //1.获得所点击行的数据(Map)
        HashMap<String, Object> itemMap
                = (HashMap<String, Object>) parent.getItemAtPosition(position);
        intent.putExtra("index", ""+position);
        intent.putExtra("title", ""+itemMap.get("title"));
        intent.putExtra("publisher", ""+itemMap.get("publisher"));
        intent.putExtra("location", ""+itemMap.get("location"));
        intent.putExtra("reward", ""+itemMap.get("version"));
        intent.putExtra("time", ""+itemMap.get("size"));
        intent.putExtra("detail", ""+itemMap.get("detail"));
        startActivity(intent);
    }

    ArrayList<Integer> choice = new ArrayList<Integer>();
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(this,"长按"+position,Toast.LENGTH_SHORT).show();
        final String[] arr = {"游泳","读书","足球","逛街","其他"};
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.icon_dog)
                .setTitle("消息提示")
                .setMultiChoiceItems(arr,
                        new boolean[]{false, false, false, false, false},
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    choice.add(which);
                                } else {
                                    choice.remove(which);
                                }
                                Toast.makeText(UserInfo.this, "用户的选择:" + choice.toString(), Toast.LENGTH_SHORT).show();
                            }
                        })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // finish();
                        //System.exit(0);
                        Toast.makeText(UserInfo.this, "用户的决定是:" + choice.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
        return true;
    }

 */
}
