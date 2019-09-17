package cs.anu.edu.au.comp2100.weiming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import cs.anu.edu.au.comp2100.weiming.ui.login.ANULogin;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_options);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void loginPress(View view) {
        Intent intent = new Intent(this, ANULogin.class);
        startActivity(intent);
    }

    public void manualPress(View view) {
        Intent intent = new Intent(this, ManualinputActivity.class);
        startActivity(intent);
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
}
