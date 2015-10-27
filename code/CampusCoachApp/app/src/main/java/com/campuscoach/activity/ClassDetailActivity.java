package com.campuscoach.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.campuscoach.campuscoachapp.R;
import com.campuscoach.entities.RoundRectImageView;
import com.campuscoach.network.Connector;
import com.squareup.picasso.Picasso;

import net.sf.json.JSON;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class ClassDetailActivity extends Activity {
    private Context context;
    private Button btn;
    private RoundRectImageView roundRectImageView;

    private Handler signUpHandler = new Handler() {
        // 重写handleMessage()方法，此方法在UI线程运行
        @Override
        public void handleMessage(Message msg) {
            String returnData = (String) msg.obj;
            JSONObject jsonObject = null;
            if(returnData.equals("NETWORK_ERROR")){
                Toast.makeText(ClassDetailActivity.this, "网络连接异常", Toast.LENGTH_SHORT).show();
            }else {
                try {
                    jsonObject = new JSONObject(returnData);
                    int result =jsonObject .getInt("result");
                    if (result == 0) {
                        Toast.makeText(ClassDetailActivity.this, "报名失败", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ClassDetailActivity.this, "报名成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_detail);

        roundRectImageView = (RoundRectImageView) findViewById(R.id.round_rect_imageview);
        btn = (Button) findViewById(R.id.btn);
        final Intent intent = getIntent();

        ((TextView)findViewById(R.id.sport_name))
                .setText("运动项目：" + intent.getStringExtra("sportName"));
        ((TextView)findViewById(R.id.introduction))
                .setText("简介:" + intent.getStringExtra("introduction"));
        ((TextView)findViewById(R.id.time))
                .setText("时间:" + intent.getStringExtra("time"));
        ((TextView)findViewById(R.id.place))
                .setText("地点:" + intent.getStringExtra("place"));
        ((TextView)findViewById(R.id.price))
                .setText("价格:" + intent.getStringExtra("price"));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //是否填写手机号？
                if(((EditText) findViewById(R.id.phone)).getText().toString().equals("")) {
                    Toast.makeText(ClassDetailActivity.this, "请填写手机号码", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences sp;
                sp = getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
                final String localID = sp.getString("ACCOUNTID", null);
                if (localID == null)
                    startActivity(new Intent(ClassDetailActivity.this, LoginActivity.class));

                //-------添加
                new AsyncTask<String, Void, Void>() {
                    @Override
                    protected Void doInBackground(String... params) {
                        String returnData = Connector.signUp(localID, intent.getStringExtra("courseID"));
                        Message msg = Message.obtain();
                        msg.obj = returnData;
                        signUpHandler.sendMessage(msg);
                        return null;
                    }
                }.execute();
            }
        });
    }


}
