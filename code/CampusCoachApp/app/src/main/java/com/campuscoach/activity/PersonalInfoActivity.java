package com.campuscoach.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.campuscoach.campuscoachapp.R;
import com.campuscoach.entities.RoundImageView;
import com.soundcloud.android.crop.Crop;
import com.squareup.picasso.Picasso;
import com.tandong.swichlayout.SwitchLayout;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PersonalInfoActivity extends Activity {
    private RoundImageView user_avatar;

    public class ListAdapter extends BaseAdapter{
        private LayoutInflater inflater;
        private Context context;
        public final List<String> more_list = new ArrayList<String>();//为条目提供数据
        public ListAdapter(Context context){
            this.context = context;
            inflater = LayoutInflater.from(this.context);
            more_list.add("我参加的培训班");
            more_list.add("我发布的预约");
        }
        @Override
        public int getCount() {
            return more_list.size();  //条目数量
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(
                        R.layout.activity_personal_info_list_item, null);
            }
            ImageView icon = (ImageView) convertView.findViewById(R.id.item_icon);//设置每个条目的图标
            if(0 == position){
                icon.setBackgroundResource(R.drawable.admin);
            }else if(1 == position){
                icon.setBackgroundResource(R.drawable.attachment);
            }
            TextView text = (TextView) convertView.findViewById(R.id.more_item_text); //设置条目的文字说明
            text.setText(more_list.get(position));
            return convertView;
        }

    }

    public static PersonalInfoActivity instance;
    public SharedPreferences sp;
    private ListView list;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        instance = this;
        sp = getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
        list = (ListView) findViewById(R.id.list);

        Intent intent = getIntent();
        String avatarUrl = sp.getString("AVATARURL", null);
        user_avatar = (RoundImageView) findViewById(R.id.user_avatar);
        if(avatarUrl != null) {
            //Picasso.with(context).load(avatarUrl).resize(50, 50).into(user_avatar);
        }
        ListAdapter adapter = new ListAdapter(this);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() { //为每一个item设置相应的响应
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch(position) {
                    case 0: { //关于
                        Intent intent = new Intent(PersonalInfoActivity.this, UserClassActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case 1: {
                        Intent intent = new Intent(PersonalInfoActivity.this, UserReservationActivity.class);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });

    findViewById(R.id.user_avatar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Crop.pickImage(PersonalInfoActivity.this);
            }
        });

        ((TextView) findViewById(R.id.account_name)).setText(sp.getString("ACCOUNT", null));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (requestCode == Crop.REQUEST_PICK && resultCode == RESULT_OK) {
            beginCrop(result.getData());
        } else if (requestCode == Crop.REQUEST_CROP) {
            handleCrop(resultCode, result);
        }
    }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(this);
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            ((ImageView)findViewById(R.id.crop_image)).setImageURI(Crop.getOutput(result));
        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
