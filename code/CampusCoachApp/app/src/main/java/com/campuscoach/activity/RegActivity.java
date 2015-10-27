package com.campuscoach.activity;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.campuscoach.campuscoachapp.R;
import com.campuscoach.network.Connector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class RegActivity extends Activity {
    private EditText accountEditText;
    private EditText pwdEditText;
    private EditText comfirmPwdEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        //主线程url设置
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().
                detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().
                detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());


        accountEditText = (EditText) findViewById(R.id.reg_account);
        pwdEditText = (EditText) findViewById(R.id.reg_pwd);
        comfirmPwdEditText = (EditText) findViewById(R.id.reg_comfirm_pwd);

        findViewById(R.id.reg_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEditText.getText().toString();
                String pwd = pwdEditText.getText().toString();
                String comfirm_pwd = comfirmPwdEditText.getText().toString();
                if(account.equals("") || pwd.equals("") || comfirm_pwd.equals("")){
                    Toast.makeText(RegActivity.this, "输入不能为空", Toast.LENGTH_LONG).show();
                }else if(!comfirm_pwd.equals(pwd)){
                    Toast.makeText(RegActivity.this, "密码输入不一致", Toast.LENGTH_LONG).show();
                }else{
                    try {
                        String returnData = Connector.reg(account, pwd);
                        if(returnData.equals("NETWORK_ERROR")){
                            Toast.makeText(RegActivity.this, "网络连接异常", Toast.LENGTH_LONG).show();
                        }else {
                            JSONObject jsonObject = new JSONObject(returnData);
                            int result = jsonObject.getInt("result");
                            if (result == 0) {
                                Toast.makeText(RegActivity.this, "用户名已经存在", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(RegActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                                //startActivity(new Intent(RegActivity.this, LoginActivity.class));
                                finish();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}
