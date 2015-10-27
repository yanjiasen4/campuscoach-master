package com.campuscoach.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.campuscoach.campuscoachapp.R;
import com.campuscoach.network.Connector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class ReservationActivity extends Activity {
    public static ReservationActivity instance;
    private EditText titleInput;
    private EditText sportsInput;
    private EditText priceInput;
    private EditText countInput;
    private EditText timeInput;
    private EditText placeInput;
    private EditText numberOfLearnerInput;

    private EditText phoneInput;
    private EditText remarkInput;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        //主线程url设置
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().
                detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().
                detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());

        initViews();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleInput.getText().toString();
                String sports = sportsInput.getText().toString();
                String price = priceInput.getText().toString();
                String count = countInput.getText().toString();
                String time = timeInput.getText().toString();
                String place = placeInput.getText().toString();
                //String numberOfLearner = numberOfLearnerInput.getText().toString();
                String phone = phoneInput.getText().toString();
                String remark = remarkInput.getText().toString();

                if(title.equals("")
                        || sports.equals("")
                        || price.equals("")
                        || count.equals("")
                        || time.equals("")
                        || phone.equals("")) {
                    Toast.makeText(ReservationActivity.this, "请输入必要信息", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        //判断是否登录
                        SharedPreferences sp;
                        sp = getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
                        String localID = sp.getString("ACCOUNTID", null);
                        if(localID == null)
                            startActivity(new Intent(ReservationActivity.this, LoginActivity.class));

                        String result = Connector.submitRequirement(title,sports,price,count,
                                time,phone,remark,place,"10",localID);
                        if(result.equals("NETWORK_ERROR")){
                            Toast.makeText(ReservationActivity.this, "网络连接异常", Toast.LENGTH_LONG).show();
                        }else {
                            JSONObject jsonObject = new JSONObject(result);
                            if (jsonObject.getInt("result") == 0) {
                                Toast.makeText(ReservationActivity.this, "提交失败", Toast.LENGTH_LONG).show();
                            } else {
                                clearText();
                                Toast.makeText(ReservationActivity.this, "提交成功", Toast.LENGTH_LONG).show();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        //Picasso.with(this).load(R.drawable.test).resize(100,100).into((RoundRectImageView) findViewById(R.id.img));
        //进入动画
        //SwitchLayout.get3DRotateFromRight(this, false, null);
    }

    public void initViews(){
        instance = this;
        titleInput = (EditText) findViewById(R.id.user_order_title_input);
        sportsInput = (EditText) findViewById(R.id.user_order_sports_input);
        priceInput = (EditText) findViewById(R.id.user_order_price_input);
        countInput = (EditText) findViewById(R.id.user_order_count_input);
        timeInput = (EditText) findViewById(R.id.user_order_time_input);
        placeInput = (EditText) findViewById(R.id.user_order_place_input);
        numberOfLearnerInput = (EditText) findViewById(R.id.user_order_number_of_learner_input);
        phoneInput = (EditText) findViewById(R.id.user_order_phone_input);
        remarkInput = (EditText) findViewById(R.id.user_order_remark_input);
        btn = (Button) findViewById(R.id.user_order_commit);
    }

    public void clearText(){
        titleInput.setText("");
        sportsInput.setText("");
        priceInput.setText("");
        countInput.setText("");
        timeInput.setText("");
        placeInput.setText("");
        numberOfLearnerInput.setText("");
        phoneInput.setText("");
        remarkInput.setText("");
    }
}
