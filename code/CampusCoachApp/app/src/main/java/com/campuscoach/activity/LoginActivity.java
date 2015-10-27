package com.campuscoach.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.EditText;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

import com.campuscoach.campuscoachapp.R;
import com.campuscoach.entities.RoundImageView;
import com.campuscoach.entities.RoundRectImageView;
import com.campuscoach.network.Connector;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;


public class LoginActivity extends Activity {
    public static LoginActivity instance;
    private SharedPreferences sp;
    private String account;
    private String pwd;
    private EditText accountInput;
    private EditText pwdInput;
    private RoundImageView user_avatar;
    private Context context;

    private Handler loginHandler = new Handler() {
        // 重写handleMessage()方法，此方法在UI线程运行
        @Override
        public void handleMessage(Message msg) {
            String returnData = (String) msg.obj;
            JSONObject jsonObject = null;
            if(returnData.equals("NETWORK_ERROR")){
                Toast.makeText(LoginActivity.this, "网络连接异常", Toast.LENGTH_SHORT).show();
            }else {
                try {
                    jsonObject = new JSONObject(returnData);
                    int result = jsonObject.getInt("result");
                    if (result == 0) {
                        Editor editor = sp.edit();
                        editor.putString("ACCOUNT", account);
                        editor.putString("ACCOUNTID", jsonObject.getString("learnerID"));
                        editor.putString("PASSWORD", pwd);
                        String avatarUrl = jsonObject.getString("avatar");
                        if(avatarUrl != null) {
                            editor.putString("AVATARURL", avatarUrl);
                        }
                        editor.commit();
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                        finish();
                    } else
                        Toast.makeText(LoginActivity.this, "用户名或密码错误，请重新登录", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        instance = this;
        //c初始化
        sp = getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
        accountInput = (EditText) findViewById(R.id.login_account);
        pwdInput = (EditText) findViewById(R.id.login_pwd);


        findViewById(R.id.login_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account = accountInput.getText().toString();
                pwd = pwdInput.getText().toString();
                //检查
                new AsyncTask<String, Void, Void>() {
                    @Override
                    protected Void doInBackground(String... params) {
                        try {
                            String returnData = Connector.login(account, pwd);
                            Message msg = Message.obtain();
                            msg.obj = returnData;
                            loginHandler.sendMessage(msg);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }.execute();
            }
        });
        findViewById(R.id.reg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegActivity.class));
            }
        });
    }
}