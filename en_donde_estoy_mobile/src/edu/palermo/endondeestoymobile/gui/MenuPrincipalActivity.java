package edu.palermo.endondeestoymobile.gui;

import edu.palermo.endondeestoymobile.R;
import edu.palermo.endondeestoymobile.R.layout;
import edu.palermo.endondeestoymobile.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MenuPrincipalActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu_principal, menu);
        return true;
    }
}
