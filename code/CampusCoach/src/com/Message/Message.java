package com.Message;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Message {
	//通用发送接口的http地址
    private static String URI_SEND_SMS = "http://yunpian.com/v1/sms/send.json";
    private static String ENCODING = "UTF-8";
    private static String apikey = "723bb7281289d53c51ee1df4669c1b7e ";
	
	/**
	 * Constructor of the object.
	 */
	public Message() {
		super();
	}

	 public static String post(String url, Map<String, String> paramsMap) {
		 	HttpClient client = new DefaultHttpClient();
	        String responseText = "";
	        HttpResponse response = null;
	        try {
	            HttpPost method = new HttpPost(url);
	            if (paramsMap != null) {
	                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
	                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
	                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
	                    paramList.add(pair);
	                }
	                method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
	            }
	            response = client.execute(method);
	            HttpEntity entity = response.getEntity();
	            if (entity != null) {
	                responseText = EntityUtils.toString(entity);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	            	 client.getConnectionManager().shutdown();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return responseText;
	    }
	
	 public static String sendSms(String text, String mobile) throws IOException {
	        Map<String, String> params = new HashMap<String, String>();
	        params.put("apikey", apikey);
	        params.put("text", text);
	        params.put("mobile", mobile);
	        return post(URI_SEND_SMS, params);
	     }
	

}
