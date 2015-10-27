package com.campuscoach.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.campuscoach.campuscoachapp.R;
import com.campuscoach.entities.ClassEntity;
import com.campuscoach.entities.ClassEntityAdapter;
import com.campuscoach.entities.Coach;
import com.campuscoach.entities.CoachEntity;
import com.campuscoach.entities.CoachEntityAdapter;
import com.campuscoach.entities.Course;
import com.campuscoach.network.Connector;
import com.tandong.swichlayout.SwitchLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.AccessControlContext;
import java.util.ArrayList;
import java.util.List;


public class BookCoachActivity extends Activity implements  SwipeRefreshLayout.OnRefreshListener{
    public static BookCoachActivity instance;
    private ListView mainListView;
    private List<Coach> coaches;
    private List<CoachEntity> coachEntities;
    private SwipeRefreshLayout swipeRefreshLayout;


    private Handler handler = new Handler() {
        // 重写handleMessage()方法，此方法在UI线程运行
        @Override
        public void handleMessage(Message msg) {
            System.out.println("*****sailinmu");
            findViewById(R.id.loading).setVisibility(View.INVISIBLE);
            final String strCoaches = (String) msg.obj;
            if(strCoaches.equals("NETWORK_ERROR")){
                Toast.makeText(BookCoachActivity.this, "网络连接异常", Toast.LENGTH_LONG).show();
            }else {
                coaches = parseJsonCoaches(strCoaches);

                initViews();
                mainListView.setAdapter(new CoachEntityAdapter(BookCoachActivity.this, coachEntities));

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

                        Intent intent = new Intent(BookCoachActivity.this, CoachInfoActivity.class);
                        intent.putExtra("imgUrl", coachEntities.get(position).getImgUrl());
                        System.out.println("img:" + coachEntities.get(position).getImgUrl());
                        intent.putExtra("phoneNumber",parseJsonCoaches(strCoaches).get(position).getPhoneNumber());
                        intent.putExtra("coachName", coachEntities.get(position).getCoachName());
                        intent.putExtra("sportName", coachEntities.get(position).getSport());
                        /*intent.putExtra("sportsName", coachEntities.get(position).getCoachName());
                        intent.putExtra("introduction", coachEntities.get(position).getImgId());
                        intent.putExtra("time", coachEntities.get(position).getSport());
                        intent.putExtra("place", coachEntities.get(position).getClass());*/
                        startActivity(intent);

                    }
                });
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_coach);


        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {
                try {
                    String strCoaches = Connector.getCoaches();

                    Message msg = Message.obtain();
                    msg.obj = strCoaches;
                    handler.sendMessage(msg);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();



        //进入动画
        //SwitchLayout.get3DRotateFromRight(this, false, null);
    }

    private void initViews() {
        instance = this;
        mainListView = (ListView) findViewById(R.id.main_listview);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);

        coachEntities = new ArrayList<CoachEntity>();

        for(int i = 0; i < coaches.size(); ++i) {
            CoachEntity coachEntity = new CoachEntity(coaches.get(i).getSportsName(), coaches.get(i).getRealname(),
                    coaches.get(i).getAvatar());
            coachEntities.add(coachEntity);
        }
    }

    public List<Coach> parseJsonCoaches(String jsonCoaches){
        List<Coach> entities = new ArrayList<Coach>();
        try {
            org.json.JSONArray jsonArray = new org.json.JSONArray(jsonCoaches);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Coach coach = new Coach();
                coach.setSportsName(jsonObject.getString("sportsName"));
                coach.setAvatar(jsonObject.getString("avatar"));
                coach.setCoachID(jsonObject.getInt("coachID"));
                coach.setLearnerID(jsonObject.getInt("learnerID"));
                coach.setPhoneNumber(jsonObject.getString("phoneNumber"));
                coach.setRealname(jsonObject.getString("realname"));
                coach.setStateFlag(jsonObject.getInt("stateFlag"));
                entities.add(coach);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return entities;
    }

    @Override
    public void onRefresh() {
        //刷新数据
        handler.sendEmptyMessageDelayed(1, 5000);
    }
}
