package edu.palermo.dondeestoy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewItem extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listviewitem_layout);
		Intent i=getIntent();
		int position = (Integer) i.getExtras().get("position");
		String country = (String) i.getExtras().get("country");
		int[] flags = (int[]) i.getExtras().get("flags");
		TextView t= (TextView) findViewById(R.id.txtLeyenda);
		t.setTextColor(Color.WHITE);
		t.setText(country);
		t.setTextSize(30);
		ImageView imageview= (ImageView)findViewById(R.id.imgFlag);
		imageview.setImageResource(flags[position]); 
}
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }
}
