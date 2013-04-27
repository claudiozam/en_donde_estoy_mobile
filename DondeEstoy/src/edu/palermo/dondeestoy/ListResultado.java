package edu.palermo.dondeestoy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ListResultado extends Activity {

	public static String[] countries = new String[] { "India", "Pakistan",
			"Sri Lanka", "China", "Bangladesh", "Nepal", "Afghanistan",
			"North Korea", "South Korea", "Japan" };

	public static int[] flags = new int[] { R.drawable.india,
			R.drawable.pakistan, R.drawable.srilanka, R.drawable.china,
			R.drawable.bangladesh, R.drawable.nepal, R.drawable.afghanistan,
			R.drawable.nkorea, R.drawable.skorea, R.drawable.japan };

	public static String[] currency = new String[] { "Indian Rupee",
			"Pakistani Rupee", "Sri Lankan Rupee", "Renminbi",
			"Bangladeshi Taka", "Nepalese Rupee", "Afghani",
			"North Korean Won", "South Korean Won", "Japanese Yen" };

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.configuracion_layout);
		List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < 10; i++) {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("txt", "Country : " + countries[i]);
			hm.put("cur", "Currency : " + currency[i]);
			hm.put("flag", Integer.toString(flags[i]));
			aList.add(hm);
		}

		String[] from = { "flag", "txt", "cur" };
		int[] to = { R.id.flag, R.id.txt, R.id.cur };
		SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList,
				R.layout.listview_layout, from, to);
		ListView listView = (ListView) findViewById(R.id.listview);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent newListViewActivity = new Intent(ListResultado.this,
						ListViewItem.class);
				newListViewActivity.putExtra("position", position);
				newListViewActivity.putExtra("country", countries[position]);
				newListViewActivity.putExtra("flags", flags);
				startActivity(newListViewActivity);
			}
		});

	}

}
