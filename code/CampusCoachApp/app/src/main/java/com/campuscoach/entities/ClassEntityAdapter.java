package com.campuscoach.entities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.campuscoach.campuscoachapp.R;

import java.util.List;
import java.util.Map;

public class ClassEntityAdapter extends BaseAdapter{

    private Context context;
    private List<ClassEntity> lists;
    private LayoutInflater layoutInflater;
    RoundRectImageView imageView;
    TextView sport;
    TextView time;
    TextView place;
    TextView price;

    public ClassEntityAdapter(Context context,  List<ClassEntity> lists){
        this.context = context;
        this.lists = lists;
        layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convertView对象就是item的界面对象，只有为空的时候我们才需要重新赋值一次，这样可以提高效率，如果有这个对象的话，系统会自动复用
        //item_listview就是自定义的item的布局文件
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.class_entity, null);
        }
        //注意findViewById的时候，要使用convertView的这个方法，因为是在它里面进行控件的寻找
        imageView = (RoundRectImageView) convertView.findViewById(R.id.img);
        sport = (TextView) convertView.findViewById(R.id.sport);
        time = (TextView) convertView.findViewById(R.id.time);
        place = (TextView) convertView.findViewById(R.id.place);
        price = (TextView) convertView.findViewById(R.id.price);

        //将数据与控件进行绑定
        imageView.setImageResource(lists.get(position).getImgId());
        sport.setText(lists.get(position).getSport());
        time.setText(lists.get(position).getTime());
        place.setText(lists.get(position).getPlace());
        price.setText(lists.get(position).getPrice());

        return convertView;
    }
}