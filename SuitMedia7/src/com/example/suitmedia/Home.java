package com.example.suitmedia;



import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class Home extends Activity {

	TextView a;
	String get_nama;
	String name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		Intent intent = getIntent();
		name = intent.getStringExtra("name");
		a = (TextView)findViewById(R.id.textName);
		Bundle b = getIntent().getExtras();
		get_nama = b.getString("parse_nama");
		a.setText("Nama : "+get_nama);


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	public void onClick(View v)
	{
		if(v.getId() == R.id.pe)
		{
			Intent intent = new Intent(Home.this, Event.class);
			startActivity(intent);
		}
		else if(v.getId() == R.id.pg)
		{
			Intent intent = new Intent(Home.this, Guest.class);
			startActivity(intent);
		}
	}

}
