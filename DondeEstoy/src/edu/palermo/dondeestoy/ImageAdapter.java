package edu.palermo.dondeestoy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {

	public static int[] images = {
			R.drawable.menu_item_buscador,	
			R.drawable.menu_item_donde_estoy,
			R.drawable.menu_item_config
	};
			
	private Context context;
	
	public ImageAdapter(Context applicationContext){
		context=applicationContext;
	}
	
	@Override
	public int getCount() {
		//number of datelements to be displayed
		return images.length;
	}

	@Override
	public Object getItem(int position) {
		
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			 LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	         v = li.inflate(R.layout.grid_item, null);
	         TextView tv = (TextView)v.findViewById(R.id.grid_item_text);
	         ImageView iv = (ImageView)v.findViewById(R.id.grid_item_image);
	         switch (position) {
	         case 0:
	        	 tv.setText("Donde Estoy");
	        	 iv.setImageResource(images[position]);
	        	 break;
	         case 1:
	        	 tv.setText("Busqueda");
	        	 iv.setImageResource(images[position]);
	        	 break;
	         case 2:
	        	 tv.setText("Configuracion");
	        	 iv.setImageResource(images[position]);
	        	 break;
	         }
	         
		}
		return v;
	}
}
