package com.example.suitmedia;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText name;
	Button next;
	String nama;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		name = (EditText)findViewById(R.id.editText1);
		next = (Button) findViewById(R.id.button1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}
	
	public void btnn(View v)
	{
		if(v.getId() == R.id.button1)
		{
			
			if(name.getText().toString().equals(""))
			{
				Toast.makeText(this, "Username must be filled", 1000).show();
			}
			else
			{
				nama = name.getText().toString();
				Bundle b = new Bundle();
				b.putString("parse_nama", nama);
				Intent intent = new Intent(MainActivity.this, Home.class);
				intent.putExtras(b);
				startActivity(intent);
			}
		
	}

}
}
