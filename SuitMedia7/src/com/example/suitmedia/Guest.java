package com.example.suitmedia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Guest extends Activity {

	String json;
	GridView Guestt;
	JSONArray jsonArray;
	ArrayList<HashMap<String,String>> alist=new ArrayList<HashMap<String,String>>(); 
	SimpleAdapter sd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guest);
		init();
	}
	
	private void init() {
		// TODO Auto-generated method stub
		Guestt = (GridView)findViewById(R.id.Guest1);
		new JSONAsyncTask().execute(); 
		Guestt.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				String namePeople = ((TextView) v.findViewById(R.id.tName)).getText().toString();
				String dob = ((TextView) v.findViewById(R.id.tBd)).getText().toString();
				int date = Integer.parseInt(dob.substring(8, dob.length())); 
				if(date % 3 == 0 && date % 2 == 0){
					Toast.makeText(getApplicationContext(), date+" = IOS", Toast.LENGTH_SHORT).show();
				}else if(date % 3 == 0){ 
					Toast.makeText(getApplicationContext(), date+" = Android", Toast.LENGTH_SHORT).show();
				}else if(date % 2 == 0){
					Toast.makeText(getApplicationContext(), date+" = Blackberry", Toast.LENGTH_SHORT).show();
				}
				SharedPreferences.Editor editor = getSharedPreferences("prefs", MODE_PRIVATE).edit();
				editor.putString("NamePeople", namePeople);
				editor.commit();
				startActivity(new Intent(Guest.this, Home.class));
				finish();
			}
		});
	}
	
class JSONAsyncTask extends AsyncTask<String, String, String> {
		

		ProgressDialog dialog; 
		Context context;
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(Guest.this);
			dialog.setMessage("Loading...");
			dialog.setTitle("Connecting");
			dialog.show(); 
			dialog.setCancelable(false);
		}
		
		@Override
		protected String doInBackground(String... urls) {
			StringBuilder builder = new StringBuilder();
		    HttpClient client = new DefaultHttpClient();
		    HttpGet httpGet = new HttpGet("http://dry-sierra-6832.herokuapp.com/api/people");
		    try { 
		    	HttpResponse response = client.execute(httpGet);
		    	StatusLine statusLine = response.getStatusLine();
		    	int statusCode = statusLine.getStatusCode();
		    	if (statusCode == 200) {
		    		HttpEntity entity = response.getEntity();
		    		InputStream content = entity.getContent();
		    		BufferedReader reader = new BufferedReader(new InputStreamReader(content));
		    		String line;
		    		while ((line = reader.readLine()) != null) {
		    			builder.append(line);
		        }
		    	}else{
		    		Log.e(Guest.class.toString(), "Failed");
		    	}
		    }catch(ClientProtocolException e) {
		    	e.printStackTrace();
		    }catch (IOException e) {
		    	e.printStackTrace();
		    }
		    json = builder.toString();
		    return json;
		}
		
		protected void onPostExecute(String json) {
			dialog.cancel();
			try {
			      jsonArray = new JSONArray(json);
			      Log.i(Guest.class.getName(),
			          "Number of entries " + jsonArray.length());
			      for (int i = 0; i < jsonArray.length(); i++) { 
			        JSONObject jsonObject = jsonArray.getJSONObject(i);
	                String name = jsonObject.getString("name");
	                String dob = jsonObject.getString("birthdate");
	                HashMap<String, String>map=new HashMap<String, String>();
	                map.put("name",name);
	                map.put("dob",dob);
	                alist.add(map);
			        Log.i(Guest.class.getName(),name);
			      }
			      sd=new SimpleAdapter(Guest.this,alist,R.layout.guest_item,
	               		new String[]{"name", "dob"},new int[]{R.id.tName, R.id.tBd});  
	              Guestt.setAdapter(sd);
			    } catch (Exception e) {
			      e.printStackTrace();  
			    }
			super.onPostExecute(json);
		}
	}
}