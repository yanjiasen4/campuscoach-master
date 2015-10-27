package com.campuscoach.entities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.campuscoach.campuscoachapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CoachEntityAdapter extends BaseAdapter {

    private Context context;
    private List<CoachEntity> lists;
    private LayoutInflater layoutInflater;
    ImageView imageView;
    TextView sport;
    TextView coachName;

    public CoachEntityAdapter(Context context,  List<CoachEntity> lists){
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
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convertView对象就是item的界面对象，只有为空的时候我们才需要重新赋值一次，这样可以提高效率，如果有这个对象的话，系统会自动复用
        //item_listview就是自定义的item的布局文件
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.coach_entity, null);
        }
        //注意findViewById的时候，要使用convertView的这个方法，因为是在它里面进行控件的寻找
        imageView = (ImageView) convertView.findViewById(R.id.img);
        sport = (TextView) convertView.findViewById(R.id.sport);
        coachName = (TextView) convertView.findViewById(R.id.coach_name);

        String url = lists.get(position).getImgUrl();
        //将数据与控件进行绑定
        Picasso.with(context).load(url).resize(100,100).into(imageView);
        //imageView.setImageResource(lists.get(position).getImgId());
        sport.setText(lists.get(position).getSport());
        coachName.setText(lists.get(position).getCoachName());

        return convertView;
    }
}
