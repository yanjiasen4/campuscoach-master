package com.campuscoach.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.campuscoach.campuscoachapp.R;
import com.campuscoach.entities.ClassEntity;
import com.campuscoach.entities.ClassEntityAdapter;
import com.campuscoach.entities.Course;
import com.campuscoach.network.Connector;


import net.sf.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class TrainingClassActivity extends Activity {
    public static TrainingClassActivity instance;
    private ListView mainListView;
    private List<ClassEntity> classEntities;
    String courseStrs;
    private List<Course> courseEntities;


    private SwipeRefreshLayout swipeRefreshLayout;
//    Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            swipeRefreshLayout.setRefreshing(false);
//        }
//    };

    //----------------------------test------------------------------
    private Handler handler = new Handler() {
        // 重写handleMessage()方法，此方法在UI线程运行
        @Override
        public void handleMessage(Message msg) {
            System.out.println("*****sailinmu");
            findViewById(R.id.loading).setVisibility(View.INVISIBLE);
            String strCourses = (String) msg.obj;
            if(strCourses.equals("NETWORK_ERROR")){
                Toast.makeText(TrainingClassActivity.this, "网络连接异常", Toast.LENGTH_LONG).show();
            }else {

                courseEntities = parseJsonCourses(strCourses);

                initViews();
                mainListView.setAdapter(new ClassEntityAdapter(TrainingClassActivity.this, classEntities));

                // ---------------------------下拉刷新--------------------------------------------
                // swipeRefreshLayout.setOnRefreshListener((SwipeRefreshLayout.OnRefreshListener) TrainingClassActivity.this);
                swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                        android.R.color.holo_red_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_green_light);
                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

                    public void onRefresh() {
                        swipeRefreshLayout.setRefreshing(true);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        }, 3000);
                    }
                });

                //---------------------------------点击查看训练班详情---------------------------------------
                mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(TrainingClassActivity.this, ClassDetailActivity.class);
                        intent.putExtra("sportName", courseEntities.get(position).getSportsName());
                        intent.putExtra("introduction", courseEntities.get(position).getIntroduction());
                        intent.putExtra("time", courseEntities.get(position).getTime());
                        intent.putExtra("place", courseEntities.get(position).getPlace());
                        intent.putExtra("price", courseEntities.get(position).getPrice());
                        intent.putExtra("courseID", Integer.toString(courseEntities.get(position).getCourseID()));
                        startActivity(intent);

                    }
                });
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_class);

        findViewById(R.id.loading).setVisibility(View.VISIBLE);
        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {
                try {
                    String strCourses = Connector.getCourses();

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
        instance = this;
        mainListView = (ListView) findViewById(R.id.main_listview);
        //leftListView = (ListView) findViewById(R.id.left_listview);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.refresh);

        classEntities = new ArrayList<ClassEntity>();
        for(int i = 0; i < courseEntities.size(); ++i) {
            ClassEntity classEntity = new ClassEntity(courseEntities.get(i).getSportsName(),
                    "时间:"+courseEntities.get(i).getTime(),
                    "地点:"+courseEntities.get(i).getPlace(),
                    "价格:"+courseEntities.get(i).getPrice(),
                    R.drawable.class_badminton);
            classEntities.add(classEntity);
        }
    }

    public static List<Course> parseJsonCourses(String jsonCourses){
        List<Course> entities = new ArrayList<Course>();
        try {
            org.json.JSONArray jsonArray = new org.json.JSONArray(jsonCourses);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Course course = new Course();
                course.setTime(jsonObject.getString("time"));
                course.setPrice(jsonObject.getString("price"));
                course.setPhoneNumber(jsonObject.getString("phoneNumber"));
                course.setEnrollNum(jsonObject.getInt("enrollNum"));
                course.setCoachID(jsonObject.getInt("coachID"));
                course.setCourseID(jsonObject.getInt("courseID"));
                course.setCoachName(jsonObject.getString("coachName"));
                course.setSportsName(jsonObject.getString("sportsName"));
                course.setStateFlag(jsonObject.getInt("stateFlag"));
                course.setPlace(jsonObject.getString("place"));
                course.setMaxNum(jsonObject.getInt("maxNum"));
                course.setIntroduction(jsonObject.getString("introduction"));
                entities.add(course);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return entities;
    }
}
