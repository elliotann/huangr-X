package com.easysoft.jeap;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014/6/16.
 */
public class MainActivity extends Activity{
    private GridView gridView;
    private ImageView imageView;
    private int[] menuImages = new int[]{
            R.drawable.usermgr,
            R.drawable.report

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
        for(int i=0;i<menuImages.length;i++){
            Map<String,Object> item = new HashMap<String, Object>();
            item.put("imageMenu",menuImages[i]);
            listItems.add(item);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,R.layout.cell,new String[]{"imageMenu"},new int[]{R.id.image1});
        gridView = (GridView)this.findViewById(R.id.mainGrid);
        gridView.setAdapter(simpleAdapter);


    }
}
