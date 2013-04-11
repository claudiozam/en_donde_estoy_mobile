package edu.palermo.dondeestoy;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainActivity extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_layout);
		GridView gv = (GridView) findViewById(R.id.gridView);
		gv.setAdapter(new AdaptadorImagenes(this));	
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parentView, View iv, int position,
					long id) {
				//Toast.makeText(getApplicationContext(), ""+position, Toast.LENGTH_SHORT).show();
				Intent i,j,k;
				switch(position){
				case 0:
					i= new Intent(getApplicationContext(),MiUbicacion.class);
					i.putExtra("id", position);
					startActivity(i);
					break;
				case 1:
					i= new Intent(getApplicationContext(),Busqueda.class); 
					//i.putExtra("id", position);
					startActivity(i);
					break;
				case 2:
					i= new Intent(getApplicationContext(),Configuracion.class); 
					//i.putExtra("id", position);
					startActivity(i);
					break;
					
				}
				
				
			}
			
			
		});
		
	}
	@Override
	public void onBackPressed() {
	Log.d(this.getLocalClassName(), "onBackPressed()");
	 this.finish();
	}
}
