package edu.palermo.dondeestoy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.palermo.dondeestoy.bo.LocationPoint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ListResult extends Activity {

	public static int[] img = new int[] { R.drawable.india,
			R.drawable.pakistan, R.drawable.srilanka, R.drawable.china,
			R.drawable.bangladesh, R.drawable.nepal, R.drawable.afghanistan,
			R.drawable.nkorea, R.drawable.skorea, R.drawable.japan };
	private ArrayList<PuntoMapa> puntosMapa;

	@SuppressWarnings("unchecked")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent i = getIntent();
		setContentView(R.layout.listview_result_layout);
		puntosMapa = (ArrayList<PuntoMapa>) i
				.getSerializableExtra("resultadoBusqueda");

		if (puntosMapa == null)
			return;

		List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
		for (PuntoMapa puntomapa : puntosMapa) {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("device", "Dispositivo : " + puntomapa.getDevice());
			hm.put("description", "Descripcion : " + puntomapa.getDescription());
			hm.put("img", Integer.toString(img[1]));
			aList.add(hm);
		}

		String[] from = { "img", "device", "description" };
		int[] to = { R.id.img, R.id.device, R.id.description };
		SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList,
				R.layout.listview_layout, from, to);
		ListView listView = (ListView) findViewById(R.id.listview);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent intentListView = new Intent(ListResult.this,
						ListViewItem.class);
				intentListView.putExtra("objpunto", puntosMapa.get(position));
				startActivity(intentListView);
			}
		});

	}

}