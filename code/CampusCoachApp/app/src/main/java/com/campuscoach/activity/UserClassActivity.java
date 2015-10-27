package com.campuscoach.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.campuscoach.campuscoachapp.R;
import com.campuscoach.entities.ClassEntity;
import com.campuscoach.entities.ClassEntityAdapter;
import com.campuscoach.entities.Course;
import com.campuscoach.network.Connector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserClassActivity extends Activity {
    private List<Course> courseEntities;
    private ListView mainListView;
    private List<ClassEntity> classEntities;

    private Handler handler = new Handler() {
        // 重写handleMessage()方法，此方法在UI线程运行
        @Override
        public void handleMessage(Message msg) {
            findViewById(R.id.loading).setVisibility(View.INVISIBLE);
            String strResult = (String) msg.obj;
            if (strResult.equals("NETWORK_ERROR")) {
                Toast.makeText(UserClassActivity.this, "网络连接异常", Toast.LENGTH_SHORT).show();
            }else {
                try {
                    JSONObject jResult = new JSONObject(strResult);
                    if(jResult.getInt("result") == 0){
                        ((TextView)findViewById(R.id.loading)).setText("EmptyT_T");
                        findViewById(R.id.loading).setVisibility(View.VISIBLE);
                    }
                    String strCourses = jResult.getString("courses");
                    courseEntities = TrainingClassActivity.parseJsonCourses(strCourses);
                    initViews();
                    mainListView.setAdapter(new ClassEntityAdapter(UserClassActivity.this, classEntities));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_class);

        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {
                try {
                    SharedPreferences sp = getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
                    String accountID = sp.getString("ACCOUNTID", null);
                    String strCourses = new String();
                    if(accountID != null) {
                        strCourses = Connector.getUserCourses(accountID);
                    }else {
                        Intent intent = new Intent(UserClassActivity.this, LoginActivity.class);
                    }
                    Message msg = Message.obtain();
                    msg.obj = strCourses;
                    handler.sendMessage(msg);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();

    }

    private void initViews() {
        mainListView = (ListView) findViewById(R.id.main_listview);
        classEntities = new ArrayList<ClassEntity>();

        for(int i = 0; i < courseEntities.size(); ++i) {
            ClassEntity classEntity = new ClassEntity(courseEntities.get(i).getSportsName(),
                    "时间:"+courseEntities.get(i).getTime(),
                    "地点:"+courseEntities.get(i).getPlace(),
                    "价格:"+courseEntities.get(i).getPrice(),
                    R.drawable.nopicture);
            classEntities.add(classEntity);
        }
    }
}
