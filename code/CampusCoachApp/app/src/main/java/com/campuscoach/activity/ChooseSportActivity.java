package com.campuscoach.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.campuscoach.campuscoachapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ChooseSportActivity extends Activity {
    private GridView mGridView;   //MyGridView
    //    ?
    private int[] imageRes = {R.drawable.sport_01,R.drawable.sport_02,
            R.drawable.sport_03,R.drawable.sport_04,
            R.drawable.sport_05,R.drawable.sport_06,
            R.drawable.sport_07,R.drawable.sport_08,
            R.drawable.sport_09,R.drawable.sport_10,
            R.drawable.sport_11,R.drawable.sport_12,};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_sport);

        mGridView = (GridView) findViewById(R.id.my_grid_view);

        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        int length = imageRes.length;
        for (int i = 0; i < length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImageView", imageRes[i]);
            data.add(map);
        }

        //?itme.xml
        SimpleAdapter simpleAdapter = new SimpleAdapter(ChooseSportActivity.this, data, R.layout.sport_type_item, new String[] { "ItemImageView" }, new int[] { R.id.ItemImageView });
        mGridView.setAdapter(simpleAdapter);

        //?mGridView  ?   ?
        mGridView.setOnItemClickListener(new GridViewItemOnClick());
    }

    public class GridViewItemOnClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> arg0, View view, int position,long arg3) {
            Intent intent = new Intent(ChooseSportActivity.this, MainActivity.class);
            intent.putExtra("position", Integer.toString(position));
            startActivity(intent);
            //Toast.makeText(getApplicationContext(), position + "",
            //Toast.LENGTH_SHORT).show();
        }
    }
}