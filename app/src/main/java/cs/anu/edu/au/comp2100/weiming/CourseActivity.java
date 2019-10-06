package cs.anu.edu.au.comp2100.weiming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CourseActivity extends AppCompatActivity {

    Spinner collegeSpinner;
    Spinner fieldSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        //select college
        collegeSpinner = findViewById(R.id.input_college_spinner);
        ArrayAdapter<CharSequence> colleges = ArrayAdapter.createFromResource(this, R.array.All_ANU_colleges, R.layout.custom_spinner);
        colleges.setDropDownViewResource(R.layout.custom_spinner);
        collegeSpinner.setAdapter(colleges);

        //corresponding field e.g COMP MATH
        fieldSpinner = findViewById(R.id.info_degree_spinner);

        //Return button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
