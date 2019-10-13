package cs.anu.edu.au.comp2100.weiming;

import android.content.SharedPreferences;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;

public class CourseActivity extends AppCompatActivity {

    public Spinner collegeSpinner;
    public Spinner fieldSpinner;
    public SharedPreferences preferences;
    public LinearLayout layout;
    public ArrayList<String> selectedCourses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        layout = findViewById(R.id.course_layout);

        //select college
        collegeSpinner = findViewById(R.id.input_college_spinner);
        ArrayAdapter<CharSequence> colleges = ArrayAdapter.createFromResource(this, R.array.All_ANU_colleges, R.layout.custom_spinner);
        colleges.setDropDownViewResource(R.layout.custom_spinner);
        collegeSpinner.setAdapter(colleges);

        //corresponding field e.g COMP MATH
        fieldSpinner = findViewById(R.id.info_degree_spinner);

        //Return button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //background
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
