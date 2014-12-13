package com.example.suitmedia;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Event extends Activity {

	protected ListView list;
    protected ListAdapter adapter;
    SimpleAdapter Adapter;
    HashMap<String, String> map;
    ArrayList<HashMap<String, String>> mylist;
    String[] Pil;
    String[] Ltn;
    String[] Gbr;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event);
		
		 list = (ListView) findViewById(R.id.listView1);
	       
	        Pil = new String[] {"DWP", "euphoria", "Hardwell", "Fable", "Afrojack"};
	        Ltn = new String[] {"12-13 Desember 2014", "12 Desember 2014", "21 Maret 2015", "31 Oktober 2014", "17 Januari 2014"};
	        Gbr = new String[] {Integer.toString(R.drawable.dwp),
	                                    Integer.toString(R.drawable.euphoria),
	                                    Integer.toString(R.drawable.hardwell),
	                                    Integer.toString(R.drawable.fable),
	                                    Integer.toString(R.drawable.afrojack) };
	       
	        mylist = new ArrayList<HashMap<String,String>>();
	       
	        for (int i = 0; i < Pil.length; i++){
	            map = new HashMap<String, String>();
	            map.put("list", Pil[i]);
	            map.put("latin", Ltn[i]);
	            map.put("gbr", Gbr[i]);
	            mylist.add(map);             
	        }
	       
	        Adapter = new SimpleAdapter(this, mylist, R.layout.layout_isi_lv,
	                  new String[] {"list", "latin", "gbr"}, new int[] {R.id.tv_nama, R.id.tv_ltn, R.id.imV});
	        list.setAdapter(Adapter);
	    }

	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event, menu);
		return true;
	}

}
