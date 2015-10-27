package com.campuscoach.network;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Message;
import android.widget.Toast;

import com.campuscoach.activity.ClassDetailActivity;
import com.campuscoach.activity.LoginActivity;
import com.campuscoach.entities.ClassEntity;
import com.campuscoach.entities.Course;

import net.sf.json.JSON;
import net.sf.json.JSONArray;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class Connector{
    private static int timeout = 3000;   //连接超时时间
    private static String domainName = "http://xiaoyuanjiaolian.sinaapp.com/";
    //private static String domainName = "192.168.233.1/";  //杨铭
    //private static String domainName = "10.188.121.164/";  //杨铭



    public static String getCourses() throws IOException {  //获取所有课程
        final String strUrl = domainName + "app-showcourses.action";
        return getResult(strUrl);
    }

    public static String getUserCourses(String userID) throws IOException {  //获取用户报名的课程
        final String strUrl = domainName + "app-showusercourse?userID=" + userID;
        return getResult(strUrl);
    }

    public static String getUserReservation(String userID) throws IOException {
        final String strUrl = domainName + "app-showuserreservation?userID=" + userID;
        return getResult(strUrl);
    }

    public static String getCoaches() throws IOException {
        final String strUrl = domainName + "app-showcoaches.action";
        return getResult(strUrl);
    }

    public static String signUp(String userID, String courseID){
        final String strUrl = domainName + "app-signup?userID=" + userID
                + "&courseID=" + courseID;
        return getResult(strUrl);
    }

    public static String login(String account, String pwd) throws IOException {
        //result:2（用户不存在）或3（密码错误）
        //result:0，登录成功，返回learnerName , learnerID
        //result:1 登录成功，教练身份 返回coachName, coachID
        final String strUrl = domainName + "app-login?learner.username="
                + account + "&learner.password=" + pwd;
        return getResult(strUrl);
    }

    public static String reg(String account, String pwd) throws IOException {
        //0：注册失败
        //1：注册成功
        final String strUrl = domainName + "app-register?learner.username="
                + account + "&learner.password=" + pwd;
        return getResult(strUrl);
    }

    public static String submitRequirement(String title, String sports,String price,String count,
                                         String time,String phone,String remark,
                                           String place, String numberOfLearner, String userID) throws IOException {
        String result = "";
        final String strUrl = domainName + "app-reservation?reservation.reservationName="
                + title + "&reservation.sportsName="+ sports + "&reservation.price=" + price + "&reservation.time=" + time
                + "&reservation.learnerPhone=" + phone + "&reservation.place="
                + place + "&reservation.maxNum=" + numberOfLearner + "&userID=" + userID;
        System.out.println(strUrl);
        return getResult(strUrl);
    }

    private static String getResult(String _url) {
        String result = "";
        try {
            URL url = new URL(_url);
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(timeout); //连接超时
            System.out.println();
            System.out.println(_url);
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String line = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            br.close();
            isr.close();
            is.close();

        }catch(IOException e){
            e.printStackTrace();
            result = "NETWORK_ERROR";
            System.out.println("result:" + result);
            return result;
        }
        System.out.println(result);
        System.out.println("result:" + result);
        return result;
    }
}
