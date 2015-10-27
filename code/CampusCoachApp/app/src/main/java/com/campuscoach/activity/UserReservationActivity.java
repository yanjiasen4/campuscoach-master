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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.campuscoach.campuscoachapp.R;
import com.campuscoach.entities.ClassEntity;
import com.campuscoach.entities.ClassEntityAdapter;
import com.campuscoach.entities.Course;
import com.campuscoach.entities.Reservation;
import com.campuscoach.entities.ReservationAdapter;
import com.campuscoach.network.Connector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserReservationActivity extends Activity {
    private List<Reservation> Reservation;
    private ListView mainListView;
    private List<Reservation> reservationEntities;
    private Handler handler = new Handler() {
        // 重写handleMessage()方法，此方法在UI线程运行
        @Override
        public void handleMessage(Message msg) {
            findViewById(R.id.loading).setVisibility(View.INVISIBLE);
            String strResult = (String) msg.obj;
            if (strResult.equals("NETWORK_ERROR")) {
                Toast.makeText(UserReservationActivity.this, "网络连接异常", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    JSONObject jResult = new JSONObject(strResult);
                    if (jResult.getInt("result") == 0) {
                        findViewById(R.id.loading).setVisibility(View.VISIBLE);
                        ((TextView) findViewById(R.id.loading)).setText("EmptyT_T");
                    } else {
                        String strReservation = jResult.getString("courses");
                        System.out.println("hehe:" + strReservation);
                        reservationEntities = UserReservationActivity.parseJsonReservations(strReservation);
                        System.out.println("hehe:" + reservationEntities.size());
                        initViews();
                        System.out.println("hehe:" + reservationEntities.size());
                        mainListView.setAdapter(new ReservationAdapter(UserReservationActivity.this, reservationEntities));
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
        setContentView(R.layout.activity_user_reservation);

        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {
                try {
                    SharedPreferences sp = getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
                    String accountID = sp.getString("ACCOUNTID", null);
                    String strCourses = new String();
                    if (accountID != null) {
                        strCourses = Connector.getUserReservation(accountID);
                    } else {
                        Intent intent = new Intent(UserReservationActivity.this, LoginActivity.class);
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
        for (int i = 0; i < reservationEntities.size(); ++i) {
            reservationEntities.get(i).setIntroduction("需求说明:" +
                    reservationEntities.get(i).getIntroduction());
        }
    }

    public static List<Reservation> parseJsonReservations(String jsonReservation) {
        List<Reservation> entities = new ArrayList<Reservation>();
        try {
            org.json.JSONArray jsonArray = new org.json.JSONArray(jsonReservation);
            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Reservation reservation = new Reservation();
                reservation.setIntroduction(jsonObject.getString("introduction"));
                reservation.setLearnerID(jsonObject.getString("learnerID"));
                reservation.setLearnerPhone(jsonObject.getString("learnerPhone"));
                reservation.setMaxNum(jsonObject.getInt("maxNum"));
                reservation.setPlace(jsonObject.getString("place"));
                reservation.setPrice(jsonObject.getString("price"));
                reservation.setReservationName(jsonObject.getString("reservationName"));
                reservation.setSportsName(jsonObject.getString("sportsName"));
                reservation.setTime(jsonObject.getString("time"));
                entities.add(reservation);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return entities;
    }
}