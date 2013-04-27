package edu.palermo.dondeestoy;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class AdaptadorImagenes extends BaseAdapter {

	public static int[] images = { R.drawable.brujula, R.drawable.map,
			R.drawable.config };
	private Context context;

	public AdaptadorImagenes(Context applicationContext) {
		context = applicationContext;
	}

	@Override
	public int getCount() {
		// number of datelements to be displayed
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
		ImageView iv;
		if (convertView != null) {
			iv = (ImageView) convertView;

		} else {
			iv = new ImageView(context);
			iv.setLayoutParams(new GridView.LayoutParams(180, 180));
			iv.setScaleType(ScaleType.CENTER_CROP);
			iv.setPadding(8, 100, 8, 20);

		}
		iv.setImageResource(images[position]);
		return iv;
	}

}
