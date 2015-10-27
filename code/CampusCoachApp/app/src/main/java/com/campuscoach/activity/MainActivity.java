package com.campuscoach.activity;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.Toast;

import com.campuscoach.campuscoachapp.R;
import com.campuscoach.entities.RoundImageView;
import com.campuscoach.network.Connector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class MainActivity extends TabActivity {
    static boolean haveLogin = false;
    private TabHost tabHost;
    DrawerLayout drawerLayout;
    private JSONObject jsonObject;
    private Context context;
    private RoundImageView user_avatar;
    public TabHost getTabHost() {
        return tabHost;
    }

    /*private Handler autoLoginHandler = new Handler() {
        // 重写handleMessage()方法，此方法在UI线程运行
        @Override
        public void handleMessage(Message msg) {
            JSONObject jsonObject = (JSONObject) msg.obj;
            int result = 0;
            try {
                result = jsonObject.getInt("result");
                if (result == 0) {
                    Toast.makeText(MainActivity.this, "自动登录成功", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(MainActivity.this, "自动登录失败，请重新登录", Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };*/

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //主线程url设置
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().
                detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().
                detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());

        Intent intent;
        TabHost.TabSpec tabSpec;
        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        tabHost.setup(this.getLocalActivityManager());

        //
        intent = new Intent().setClass(this, BookCoachActivity.class);
        tabSpec = tabHost.newTabSpec("coach").setIndicator("coach").setContent(intent);
        tabHost.addTab(tabSpec);

        //
        intent = new Intent().setClass(this, TrainingClassActivity.class);
        tabSpec = tabHost.newTabSpec("class").setIndicator("class").setContent(intent);
        tabHost.addTab(tabSpec);

        //
        intent = new Intent().setClass(this, ReservationActivity.class);
        tabSpec = tabHost.newTabSpec("requirement").setIndicator("test").setContent(intent);
        tabHost.addTab(tabSpec);

        //
        intent = new Intent().setClass(this, PersonalInfoActivity.class);
        tabSpec = tabHost.newTabSpec("infomation").setIndicator("infomatino").setContent(intent);
        tabHost.addTab(tabSpec);


        RadioGroup radioGroup=(RadioGroup) this.findViewById(R.id.main_tab_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.book_coach:
                        if(ReservationActivity.instance != null)
                            ((DrawerLayout)(ReservationActivity.instance.findViewById(R.id.drawerlayout)))
                                .closeDrawers();
                        if(TrainingClassActivity.instance != null)
                            ((DrawerLayout)(TrainingClassActivity.instance.findViewById(R.id.drawerlayout)))
                                .closeDrawers();
                        if(PersonalInfoActivity.instance != null)
                            ((DrawerLayout)(PersonalInfoActivity.instance.findViewById(R.id.drawerlayout)))
                                .closeDrawers();
                        tabHost.setCurrentTabByTag("coach");
                        break;
                    case R.id.training_class:
                        if(ReservationActivity.instance != null)
                            ((DrawerLayout)(ReservationActivity.instance.findViewById(R.id.drawerlayout)))
                            .closeDrawers();
                        if(BookCoachActivity.instance != null)
                            ((DrawerLayout)(BookCoachActivity.instance.findViewById(R.id.drawerlayout)))
                                .closeDrawers();
                        if(PersonalInfoActivity.instance != null)
                            ((DrawerLayout)(PersonalInfoActivity.instance.findViewById(R.id.drawerlayout)))
                                .closeDrawers();
                        tabHost.setCurrentTabByTag("class");
                        break;
                    case R.id.register_requirement:
                        if(BookCoachActivity.instance != null)
                            ((DrawerLayout)(BookCoachActivity.instance.findViewById(R.id.drawerlayout)))
                                .closeDrawers();
                        if(TrainingClassActivity.instance != null)
                            ((DrawerLayout)(TrainingClassActivity.instance.findViewById(R.id.drawerlayout)))
                                .closeDrawers();
                        if(PersonalInfoActivity.instance != null)
                            ((DrawerLayout)(PersonalInfoActivity.instance.findViewById(R.id.drawerlayout)))
                                .closeDrawers();
                        tabHost.setCurrentTabByTag("requirement");
                        break;
                    case R.id.personal_info:
                        if(ReservationActivity.instance != null)
                            ((DrawerLayout)(ReservationActivity.instance.findViewById(R.id.drawerlayout)))
                                .closeDrawers();
                        if(TrainingClassActivity.instance != null)
                            ((DrawerLayout)(TrainingClassActivity.instance.findViewById(R.id.drawerlayout)))
                                .closeDrawers();
                        if(BookCoachActivity.instance != null)
                            ((DrawerLayout)(BookCoachActivity.instance.findViewById(R.id.drawerlayout)))
                                .closeDrawers();
                        tabHost.setCurrentTabByTag("infomation");

                        try {
                            if(autoLogin()) {
                            } else
                                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public boolean autoLogin() throws JSONException, InterruptedException, IOException {
        //---------------------自动登录----------------------------------------
        if(haveLogin)
            return true;
        SharedPreferences sp = getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);

        final String localAccount = sp.getString("ACCOUNT", null);
        final String localPwd = sp.getString("PASSWORD", null);
        jsonObject = new JSONObject();
        if (localAccount != null) {
            Toast.makeText(MainActivity.this, "自动登录中", Toast.LENGTH_SHORT).show();
            //PersonalInfoActivity.instance.findViewById(R.id.logout_btn).setVisibility(View.INVISIBLE);

            String returnData = Connector.login(localAccount, localPwd);
            if(returnData.equals("NETWORK_ERROR")){
                Toast.makeText(MainActivity.this, "网络连接异常", Toast.LENGTH_SHORT).show();
            }else {
                jsonObject = new JSONObject(returnData);

                int result = jsonObject.getInt("result");
                if (result == 0) {
                    Toast.makeText(MainActivity.this, "自动登录成功", Toast.LENGTH_SHORT).show();
                    user_avatar = (RoundImageView) findViewById(R.id.user_avatar);

                    haveLogin = true;
                    //PersonalInfoActivity.instance.findViewById(R.id.logout_btn).setVisibility(View.VISIBLE);
                    return true;
                } else {
                    Toast.makeText(MainActivity.this, "账号已过期，请重新登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                }
            }
        }
        return false;
    }
}
