package com.example.hygeia.MyListAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hygeia.R;
import com.example.hygeia.ui.home.DetailActivity;
import com.example.hygeia.ui.home.HomeFragment;

import java.util.List;
import java.util.Map;

import static android.util.Log.i;

//主界面任务列表listview对应的adapter
public class MyListAdapter extends BaseAdapter {

    List<Map<String,Object>> list;
    LayoutInflater inflater;
    private Context mcontent;

    public MyListAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.mcontent=context;
    }



    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.layout_list_item, null);
            holder = new ViewHolder();
            holder.logo = (ImageView) convertView.findViewById(R.id.logo);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.version = (TextView) convertView.findViewById(R.id.version);
            holder.size = (TextView) convertView.findViewById(R.id.size);
            holder.btn = (Button) convertView.findViewById(R.id.btn);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Map map = list.get(position);
        holder.logo.setImageResource((Integer) map.get("logo"));
        holder.title.setText((String) map.get("title"));
        holder.version.setText((String) map.get("reward"));
        holder.size.setText((String) map.get("time"));
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mcontent,"successful！",Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    public class ViewHolder{
        ImageView logo;
        TextView title;
        TextView version;
        TextView size;
        Button btn;
    }
    public void addListener(View convertView){

    }
}