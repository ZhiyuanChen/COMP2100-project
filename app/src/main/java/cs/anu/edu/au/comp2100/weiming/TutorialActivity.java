package cs.anu.edu.au.comp2100.weiming;

import android.content.SharedPreferences;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.preference.PreferenceManager;

public class TutorialActivity extends AppCompatActivity {

    public static SharedPreferences preferences;
    public static LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        layout = findViewById(R.id.tutorial_layout);

        //Return button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //background color
        int backgroundColor = preferences.getInt("background", 0);
        layout.setBackgroundColor(backgroundColor);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case (android.R.id.home):
                finish();
                return true;
            case (R.id.home):
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar_home, menu);
        return true;
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
