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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.campuscoach.campuscoachapp.R;
import com.campuscoach.entities.Coach;
import com.campuscoach.entities.RoundRectImageView;
import com.campuscoach.network.Connector;
import com.squareup.picasso.Picasso;

import net.sf.json.JSON;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class CoachInfoActivity extends Activity {
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
                Toast.makeText(CoachInfoActivity.this, "网络连接异常", Toast.LENGTH_LONG).show();
            }else {
                try {
                    jsonObject = new JSONObject(returnData);
                    int result =jsonObject .getInt("result");
                    if (result == 0) {
                        Toast.makeText(CoachInfoActivity.this, "报名成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else
                        Toast.makeText(CoachInfoActivity.this, "报名失败", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_info);

        roundRectImageView = (RoundRectImageView) findViewById(R.id.round_rect_imageview);
        btn = (Button) findViewById(R.id.btn);
        final Intent intent = getIntent();
        Picasso.with(context).load(intent.getStringExtra("imgUrl")).resize(120,120).into(roundRectImageView);

        ((TextView)findViewById(R.id.coach_name))
                .setText("教练姓名：" + intent.getStringExtra("coachName"));
        ((TextView)findViewById(R.id.phone))
                .setText("联系方式:" + intent.getStringExtra("phoneNumber"));
        ((TextView)findViewById(R.id.sport_name))
                .setText("运动项目:" + intent.getStringExtra("sportName"));

    }


}
