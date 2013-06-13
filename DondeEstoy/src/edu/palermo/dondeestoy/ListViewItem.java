package edu.palermo.dondeestoy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewItem extends Activity implements OnClickListener {
	private MapPoint mapPoint;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listviewitem_layout);
		Intent i = getIntent();
		mapPoint = (MapPoint) i.getSerializableExtra("objpunto");
		TextView txtViewDevice = (TextView) findViewById(R.id.txtDevice);
		txtViewDevice.setTextColor(Color.BLACK);
		txtViewDevice.setText(mapPoint.getDevice());
		txtViewDevice.setTextSize(30);

		TextView txtViewdescription = (TextView) findViewById(R.id.txtdescription);
		txtViewdescription.setTextColor(Color.BLACK);
		txtViewdescription.setText(mapPoint.getDescription());
		txtViewdescription.setTextSize(30);
		ImageView image = (ImageView) findViewById(R.id.imgFlag);
		try{
			
			image.setImageResource(Utils.categoryImages.get(mapPoint.getCategory().toUpperCase()));	
		}
		
		catch(Exception ex){
			image.setImageResource(Utils.categoryImages.get("DEFAULT"));
		}
		
		
		Button btn = (Button) findViewById(R.id.buttonVerEnElMapa);
		btn.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		Intent i = getIntent();
		Intent iMap = new Intent(getApplicationContext(), MapActivity.class);
		iMap.putExtra("puntos", new MapPoint[] { mapPoint });
		startActivity(iMap);
	}

}
