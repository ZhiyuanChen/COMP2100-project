package cs.anu.edu.au.comp2100.weiming;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.preference.PreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class CourseActivity extends AppCompatActivity implements View.OnClickListener{

    public Spinner collegeSpinner;
    public Spinner fieldSpinner;
    public SharedPreferences preferences;
    public LinearLayout layout;
    public ArrayList<String> selectedCourses;
    public ArrayList<String> courseOptions;
    public String currentCollege;
    public String currentField;
    public ListView courseOptionsView;
    public ArrayAdapter<String> optionAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        layout = findViewById(R.id.course_layout);
        courseOptionsView = findViewById(R.id.course_options);

        //select college
        collegeSpinner = findViewById(R.id.input_college_spinner);
        ArrayAdapter<CharSequence> colleges = ArrayAdapter.createFromResource(this, R.array.All_ANU_colleges, R.layout.custom_spinner);
        colleges.setDropDownViewResource(R.layout.custom_spinner);
        collegeSpinner.setAdapter(colleges);
        String defaultCollege = preferences.getString("defaultCollege", "College of Arts and Social Sciences");
        currentCollege = defaultCollege;
        final int position1 = colleges.getPosition(defaultCollege);
        collegeSpinner.post(new Runnable() {
            public void run() {
                collegeSpinner.setSelection(position1);
            }
        });
        collegeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor e = preferences.edit();
                currentCollege = parent.getItemAtPosition(position).toString();
                e.putString("defaultCollege", currentCollege);
                e.apply();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //corresponding field e.g COMP MATH
        fieldSpinner = findViewById(R.id.input_field_spinner);
        ArrayAdapter<CharSequence> fields = ArrayAdapter.createFromResource(this, R.array.ANU_curse_fields, R.layout.custom_spinner);
        fields.setDropDownViewResource(R.layout.custom_spinner);
        fieldSpinner.setAdapter(fields);
        String defaultField = preferences.getString("defaultField", "ACST");
        currentField = defaultField;
        final int position2 = fields.getPosition(defaultField);
        fieldSpinner.setSelection(position2);
        fieldSpinner.post(new Runnable() {
            public void run() {
                fieldSpinner.setSelection(position2);
            }
        });
        fieldSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor e = preferences.edit();
                currentField = parent.getItemAtPosition(position).toString();
                e.putString("defaultField", currentField);
                e.apply();

                courseOptions.clear();
                optionAdapter.notifyDataSetChanged();
                loadCourseOptions();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //get course options
        courseOptions = new ArrayList<>();
        optionAdapter = new ArrayAdapter<>(this, R.layout.custom_listview, courseOptions);
        courseOptionsView.setAdapter(optionAdapter);
        courseOptionsView.setLongClickable(true);
        loadCourseOptions();

        courseOptionsView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View v, final int position, long id) {
                String course = courseOptions.get(position).split("\n")[0];

                AlertDialog.Builder builder = new AlertDialog.Builder(CourseActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Select");
                builder.setMessage("Select course " + course + " ?");

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton("SELECT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        courseOptions.remove(position);
//                        optionAdapter.notifyDataSetChanged();
//
//                        //file_helper
//                        CoursesTakenFileHelper.writeData(courseOptions, getApplicationContext());
                        Toast toast = Toast.makeText(getApplicationContext(), "Course Selected!", Toast.LENGTH_SHORT);
                        View toastView = toast.getView();
                        toastView.getBackground().setColorFilter(getResources().getColor(R.color.green), PorterDuff.Mode.SRC_IN);
                        toast.show();
                    }
                });
                builder.show();
                return true;
            }
        });

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

    @Override
    public void onClick(View v) {

    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("nid.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void loadCourseOptions(){
        try {
            JSONArray array = new JSONArray(loadJSONFromAsset());
            for (int i = 0; i < array.length();i++) {
                String str = (String) array.get(i);
                String[] split = str.split("_S2 ");
                String field = split[0].substring(0, 4);
                if(field.equals(currentField)){
                    String add = (split[0] + '\n' + split[1]);
                    optionAdapter.add(add);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
