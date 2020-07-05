package com.example.hygeia.MyListAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hygeia.R;

import java.util.List;
import java.util.Map;

//个人任务列表的listview 对应的adapter
public class MyTaskAdapter extends BaseAdapter {

    List<Map<String,Object>> list;
    LayoutInflater inflater;

    public MyTaskAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
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
            convertView = inflater.inflate(R.layout.layout_user_list_item, null);
            holder = new ViewHolder();
            holder.logo = (ImageView) convertView.findViewById(R.id.logo);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.version = (TextView) convertView.findViewById(R.id.version);
            holder.size = (TextView) convertView.findViewById(R.id.size);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Map map = list.get(position);
        holder.logo.setImageResource((Integer) map.get("logo"));
        holder.title.setText((String) map.get("title"));
        holder.version.setText((String) map.get("reward"));
        holder.size.setText((String) map.get("state"));

        return convertView;
    }

    public class ViewHolder{
        ImageView logo;
        TextView title;
        TextView version;
        TextView size;
    }
}