package com.example.hygeia.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hygeia.MyListAdapter.MyListAdapter;
import com.example.hygeia.R;
import com.example.hygeia.entity.Label;
import com.example.hygeia.entity.Priority;
import com.example.hygeia.entity.Task;
import com.example.hygeia.entity.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//主界面
public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private User user1= new User("123","123456");
    private Task task1=new Task();
    private String name;
    private Integer credit;
    private String title;
    private String description;
    private Priority priority;
    private Label label;
    private Calendar begintime;
    private Calendar endtime;
    private String place;

    private Button mBtnUserinfo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.activity_task_list, container, false);
        mBtnUserinfo= root.findViewById(R.id.userinfo);



        mBtnUserinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),UserInfo.class);
                startActivity(intent);
            }
        });


        //1.拿到listview对象
        ListView lv = root.findViewById(R.id.lv_1);

        //2.数据源
        user1.setName("Black");
        name=user1.getName();
        credit=task1.getCredit();
        task1.setTitle("feed a dog");
        title=task1.getTitle();
        task1.setDescription("I will leave home for one week, please help me feed my dog.feed the dog " +
                "once a day, try to do it from 10:00 to 12:00, and play with the kitten for half an hour. " +
                "Please contact me in case of any accident");
        description=task1.getDescription();
        task1.setPriority(Priority.HIGH);
        priority=task1.getPriority();
        task1.setLabel(Label.HELPING);
        label=task1.getLabel();
        task1.setPlace("Beijing West Railway Station");
        place=task1.getPlace();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar t1 = Calendar.getInstance();
        t1.set(2020,7,1,10,30);
        task1.setBeginTime(t1);
        begintime=task1.getBeginTime();

        Calendar t2 = Calendar.getInstance();
        t2.set(2020,7,7,10,30);
        task1.setEndTime(t2);
        endtime=task1.getEndTime();



        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("logo", R.drawable.icon_dog);
        map.put("publisher",name);
        map.put("location","NewYork");
        map.put("title", title);
        map.put("reward", "Reward: "+credit);
        map.put("time", "Time: "+dateFormat.format(begintime.getTime())+" to "+dateFormat.format(endtime.getTime()));
        map.put("detail",description);
        map.put("priority",priority);
        map.put("label",label);
        map.put("place",place);
        list.add(map);
/*
        map = new HashMap<String, Object>();
        map.put("logo", R.drawable.icon_dog);
        map.put("publisher","Jack");
        map.put("location","California");
        map.put("title", "seed my dog to hospital");
        map.put("version", "Reward: 40");
        map.put("size", "Time: 2020.5.5 9:00-11:00");
        map.put("detail","My dog is ill, but today I must to have a business travelI will leave home for one week." +
                " please help me seed the dog to the Mary's pets hospital " +
                "Please contact me in case of any accident");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.drawable.icon_dog);
        map.put("publisher","Jerry");
        map.put("location","Los Angeles");
        map.put("title", "walk my dog");
        map.put("version", "Reward: 10");
        map.put("size", "Time: 2020.5.5-2020.5.10 18:00-18:30");
        map.put("detail","Take my dog for a walk in the community every day");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.drawable.icon_dog);
        map.put("publisher","Black");
        map.put("location","NewYork");
        map.put("title", "feed a dog");
        map.put("version", "Reward: 20");
        map.put("size", "Time: 2020.5.5-2020.5.10, 30 min/day");
        map.put("detail","I will leave home for one week, please help me feed my dog.feed the dog " +
                "once a day, try to do it from 10:00 to 12:00, and play with the kitten for half an hour. " +
                "Please contact me in case of any accident");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.drawable.icon_dog);
        map.put("publisher","Jack");
        map.put("location","California");
        map.put("title", "seed my dog to hospital");
        map.put("version", "Reward: 40");
        map.put("size", "Time: 2020.5.5 9:00-11:00");
        map.put("detail","My dog is ill, but today I must to have a business travelI will leave home for one week." +
                " please help me seed the dog to the Mary's pets hospital " +
                "Please contact me in case of any accident");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.drawable.icon_dog);
        map.put("publisher","Jerry");
        map.put("location","Los Angeles");
        map.put("title", "walk my dog");
        map.put("version", "Reward: 10");
        map.put("size", "Time: 2020.5.5-2020.5.10 18:00-18:30");
        map.put("detail","Take my dog for a walk in the community every day");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.drawable.icon_dog);
        map.put("publisher","Black");
        map.put("location","NewYork");
        map.put("title", "feed a dog");
        map.put("version", "Reward: 20");
        map.put("size", "Time: 2020.5.5-2020.5.10, 30 min/day");
        map.put("detail","I will leave home for one week, please help me feed my dog.feed the dog " +
                "once a day, try to do it from 10:00 to 12:00, and play with the kitten for half an hour. " +
                "Please contact me in case of any accident");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.drawable.icon_dog);
        map.put("publisher","Jack");
        map.put("location","California");
        map.put("title", "seed my dog to hospital");
        map.put("version", "Reward: 40");
        map.put("size", "Time: 2020.5.5 9:00-11:00");
        map.put("detail","My dog is ill, but today I must to have a business travelI will leave home for one week." +
                " please help me seed the dog to the Mary's pets hospital " +
                "Please contact me in case of any accident");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put("logo", R.drawable.icon_dog);
        map.put("publisher","Jerry");
        map.put("location","Los Angeles");
        map.put("title", "walk my dog");
        map.put("version", "Reward: 10");
        map.put("size", "Time: 2020.5.5-2020.5.10 18:00-18:30");
        map.put("detail","Take my dog for a walk in the community every day");
        list.add(map);

 */


        //3.设置适配器
//        SimpleAdapter adapter = new SimpleAdapter(
//                this,
//                list,
//                R.layout.item,
//                new String[]{"logo","title","version","size"},
//                new int[]{R.id.logo,R.id.title,R.id.version,R.id.size}
//        );

        MyListAdapter adapter = new MyListAdapter(getActivity());
        adapter.setList(list);


        //4.关联适配器
        lv.setAdapter(adapter);

        //5
        lv.setOnItemClickListener(mItemClickListener);
        return root;
    }
    AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener()
    {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            //Toast.makeText(this,"点击"+position,Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.setClass(getActivity(),DetailActivity.class);

            //1.获得所点击行的数据(Map)
            HashMap<String, Object> itemMap
                    = (HashMap<String, Object>) parent.getItemAtPosition(position);
            intent.putExtra("index", ""+position);
            intent.putExtra("title", ""+itemMap.get("title"));
            intent.putExtra("publisher", ""+itemMap.get("publisher"));
            intent.putExtra("location", ""+itemMap.get("location"));
            intent.putExtra("reward", ""+itemMap.get("reward"));
            intent.putExtra("time", ""+itemMap.get("time"));
            intent.putExtra("detail", ""+itemMap.get("detail"));
            intent.putExtra("priority", ""+itemMap.get("priority"));
            intent.putExtra("label", ""+itemMap.get("label"));
            intent.putExtra("place", ""+itemMap.get("place"));
            startActivity(intent);
        }

    };

}