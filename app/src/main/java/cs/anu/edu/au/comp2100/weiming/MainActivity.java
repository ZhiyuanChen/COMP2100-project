package cs.anu.edu.au.comp2100.weiming;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Pop intro slides if first started
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean isFirstStart = sp.getBoolean("firstStart", true);
        if (isFirstStart) {
            Intent intent = new Intent(MainActivity.this, AppintroActivity.class); // Call the AppIntro java class
            startActivity(intent);
            SharedPreferences.Editor e = sp.edit();
            e.putBoolean("firstStart", false);
            e.apply();
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_edit) {
            Intent infoIntent = new Intent(this, LoginActivity.class);
            startActivity(infoIntent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        } else if (id == R.id.nav_course) {
            Intent infoIntent = new Intent(this, CourseActivity.class);
            startActivity(infoIntent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        } else if (id == R.id.nav_tutorial) {
            Intent infoIntent = new Intent(this, TutorialActivity.class);
            startActivity(infoIntent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        } else if (id == R.id.nav_manage) {
            Intent infoIntent = new Intent(this, ManageActivity.class);
            startActivity(infoIntent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        } else if (id == R.id.nav_add) {
            Intent infoIntent = new Intent(this, AddActivity.class);
            startActivity(infoIntent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        } else if (id == R.id.nav_settings) {
            Intent infoIntent = new Intent(this, SettingsActivity.class);
            startActivity(infoIntent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }else if (id == R.id.nav_export) {

        }else if (id == R.id.nav_help) {
            Intent intent = new Intent(MainActivity.this, AppintroActivity.class); // Call the AppIntro java class
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }else if (id == R.id.nav_about) {
            Intent infoIntent = new Intent(this, AboutActivity.class);
            startActivity(infoIntent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void imgbtnPress(View view){
        Intent infoIntent = new Intent(this, LoginActivity.class);
        startActivity(infoIntent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    public void fullScreen(View view) {
        Intent infoIntent = new Intent(this, FullscreenActivity.class);
        startActivity(infoIntent);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
