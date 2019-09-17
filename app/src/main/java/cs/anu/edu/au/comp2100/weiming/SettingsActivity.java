package cs.anu.edu.au.comp2100.weiming;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the home; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent infoIntent = new Intent(this, MainActivity.class);
        startActivity(infoIntent);
        return super.onOptionsItemSelected(item);
    }

    public void bkgd(View view) {
        Intent infoIntent = new Intent(this, BackgroundActivity.class);
        startActivity(infoIntent);
    }
}