package cs.anu.edu.au.comp2100.weiming;

import android.content.SharedPreferences;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.preference.PreferenceManager;
import cs.anu.edu.au.comp2100.weiming.ui.login.ANULogin;

public class LoginActivity extends AppCompatActivity {


    public static SharedPreferences preferences;
    public static RelativeLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_options);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        layout = findViewById(R.id.loginOption_layout);

        //Return button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //background color
        int backgroundColor = preferences.getInt("background", 0);
        layout.setBackgroundColor(backgroundColor);
    }

    public void loginPress(View view) {
        Intent intent = new Intent(this, ANULogin.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void manualPress(View view) {
        Intent intent = new Intent(this, ManualinputActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
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
