package cs.anu.edu.au.comp2100.weiming;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
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

import com.alamkanak.weekview.WeekViewEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cs.anu.edu.au.comp2100.weiming.object.JSON;


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
    public ListView selectedCoursesView;
    public ArrayAdapter<String> optionAdapter;
    public ArrayAdapter<String> selectAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        layout = findViewById(R.id.course_layout);
        courseOptionsView = findViewById(R.id.course_options);
        selectedCoursesView = findViewById(R.id.selected_courses);

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
        loadCourseOptions();

        courseOptionsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String course = courseOptions.get(position).split("\n")[0];

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
                        if(selectedCourses.contains(course)){
                            Toast toast = Toast.makeText(getApplicationContext(), "Course Duplicate", Toast.LENGTH_SHORT);
                            View toastView = toast.getView();
                            toastView.getBackground().setColorFilter(getResources().getColor(R.color.pink), PorterDuff.Mode.SRC_IN);
                            toast.show();
                        }
                        else {
                            selectedCourses.add(course);
                            selectAdapter.notifyDataSetChanged();
                            //file_helper
                            CoursesFileHelper.writeData(selectedCourses, getApplicationContext(), "courseSelected.dat");
                            Toast toast = Toast.makeText(getApplicationContext(), "Course Selected", Toast.LENGTH_SHORT);

                            //Add to weekView
                            List<List<WeekViewEvent>> events = loadEventsByCourse(course);
                            List<WeekViewEvent> lectures = events.get(0);
                            List<WeekViewEvent> dropins = events.get(2);
                            MainActivity.addEvents(lectures);
                            MainActivity.addEvents(dropins);

                            View toastView = toast.getView();
                            toastView.getBackground().setColorFilter(getResources().getColor(R.color.green), PorterDuff.Mode.SRC_IN);
                            toast.show();
                        }
                    }
                });
                builder.show();
            }
        });


        //selected courses
        selectedCourses = CoursesFileHelper.readData(this, "courseSelected.dat");
        selectAdapter = new ArrayAdapter<>(this, R.layout.custom_listview, selectedCourses);
        selectedCoursesView.setAdapter(selectAdapter);
        selectedCoursesView.setLongClickable(true);
        selectedCoursesView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final String course = selectedCourses.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(CourseActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Delete");
                builder.setMessage("Delete selected course " + course + " ?");

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        selectedCourses.remove(position);
                        List<List<WeekViewEvent>> events = loadEventsByCourse(course);
                        List<WeekViewEvent> lectures = events.get(0);
                        List<WeekViewEvent> dropins = events.get(2);
                        MainActivity.removeEvents(lectures);
                        MainActivity.removeEvents(dropins);
                        selectAdapter.notifyDataSetChanged();

                        //file_helper
                        CoursesFileHelper.writeData(new ArrayList<String>(), getApplicationContext(), course+".dat");
                        CoursesFileHelper.writeData(selectedCourses, getApplicationContext(), "courseSelected.dat");
                        Toast toast = Toast.makeText(getApplicationContext(), "Course Deleted", Toast.LENGTH_SHORT);
                        View toastView = toast.getView();
                        toastView.getBackground().setColorFilter(getResources().getColor(R.color.pink), PorterDuff.Mode.SRC_IN);
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

    //
    public String loadJSONFromAsset(String filename) {
        String json;
        try {
            InputStream is = this.getAssets().open(filename);
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

    //add course (with course name and course ID) options
    public void loadCourseOptions(){
        try {
            JSONArray array = new JSONArray(loadJSONFromAsset("nid.json"));
            for (int i = 0; i < array.length();i ++) {
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


    //input a course name, output a list of relative course events & set colors respectively for different course types.
    public List<List<WeekViewEvent>> loadEventsByCourse(String course){
        List<WeekViewEvent> lectures = new ArrayList<>();
        List<WeekViewEvent> tutorials = new ArrayList<>();
        List<WeekViewEvent> dropin = new ArrayList<>();
        try {
            JSONArray names = new JSONArray(loadJSONFromAsset("nid.json"));
            JSONArray types = new JSONArray(loadJSONFromAsset("iid.json"));
            JSONArray locas = new JSONArray(loadJSONFromAsset("lid.json"));
            JSONArray evnts = new JSONArray(loadJSONFromAsset("events.json"));
            for (int i = 0; i < evnts.length(); i ++) {
                JSONObject obj = (JSONObject) evnts.get(i);

                int courseId = Integer.parseInt(obj.get("nid").toString());
                String courseName = (String) names.get(courseId);
                String[] courseInfo = courseName.split("_S2 ");
                String courseCode = courseInfo[0];

                if(courseCode.equals(course)){
                    int typeId = Integer.parseInt(obj.get("iid").toString());
                    String type = (String) types.get(typeId);
                    String subType = type.substring(0, 3);

                    int locationId = Integer.parseInt(obj.get("lid").toString());
                    String location = (String) locas.get(locationId);

                    double durationD = Double.parseDouble(obj.get("dur").toString());
                    int duration = (int) (durationD*60);

                    int day = Integer.parseInt(obj.get("day").toString());

                    double start = Double.parseDouble(obj.get("start").toString());

                    String weeksStr = (String) obj.get("weeks");
                    List<Integer> weeks = JSON.parseWeeks(weeksStr);
                    for(int week : weeks){
                        Calendar startTime = JSON.weekToDate(week, day);
                        Calendar endTime = JSON.weekToDate(week, day);

                        int[] time = JSON.parseStartTime(start);
                        startTime.set(Calendar.HOUR_OF_DAY, time[0]);
                        startTime.set(Calendar.MINUTE, time[1]);
                        endTime.set(Calendar.HOUR_OF_DAY, time[0]);
                        endTime.set(Calendar.MINUTE, time[1]);
                        endTime.add(Calendar.MINUTE, duration);
                        WeekViewEvent schedule = new WeekViewEvent();
                        String name = courseCode + " " + type;
                        schedule.setId(startTime.get(Calendar.MONTH)*100 + startTime.get(Calendar.DAY_OF_MONTH) + name.hashCode());
                        schedule.setName(courseCode + " " + type);
                        schedule.setLocation(location);
                        schedule.setStartTime(startTime);
                        schedule.setEndTime(endTime);

                        if(subType.equals("Lec")){
                            schedule.setName(courseCode + "\nLecture");
                            schedule.setColor(Color.rgb(44, 114, 219));
                            lectures.add(schedule);
                        }
                        else if(subType.equals("Dro")){
                            schedule.setName(courseCode + "\nDrop-in");
                            schedule.setColor(Color.rgb(130, 130, 130));
                            dropin.add(schedule);
                        }
                        else{
                            schedule.setColor(Color.rgb(252, 186, 3));
                            tutorials.add(schedule);
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<List<WeekViewEvent>> result = new ArrayList<>();
        result.add(lectures);
        result.add(tutorials);
        result.add(dropin);
        return result;
    }

}
