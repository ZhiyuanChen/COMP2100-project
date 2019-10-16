package cs.anu.edu.au.comp2100.weiming;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.preference.PreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cs.anu.edu.au.comp2100.weiming.object.JSON;

public class TutorialActivity extends AppCompatActivity {

    public static SharedPreferences preferences;
    public static LinearLayout layout;
    public ArrayList<String> selectedCourses;
    public ListView courseManage;
    public ArrayAdapter courseAdapter;
    public LinearLayout tutorialView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        layout = findViewById(R.id.tutorial_layout);
        courseManage = findViewById(R.id.courseManage);
        tutorialView = findViewById(R.id.tutorialView);

        //set current courses
        selectedCourses = CoursesFileHelper.readData(this, 1);
        courseAdapter = new ArrayAdapter<>(this, R.layout.custom_listview, selectedCourses);
        courseManage.setAdapter(courseAdapter);
        courseManage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for(int i = 0; i < selectedCourses.size(); i ++){
                    parent.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                }
                view.setBackgroundColor(getResources().getColor(R.color.pink));
                String course = selectedCourses.get(position);
                HashMap<String, List<String>> tuts = loadTutorials(course);
                for(String key : tuts.keySet()){
                    TextView title = new TextView(getApplicationContext());
                    title.setText(key);
                }
            }
        });

        //Return button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //background color
        int backgroundColor = preferences.getInt("background", 0);
        layout.setBackgroundColor(backgroundColor);

        Spinner spinner = new Spinner(getApplicationContext());
        tutorialView.addView(spinner);


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
        }        return super.onOptionsItemSelected(item);
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


    public  HashMap<String, List<String>> loadTutorials(String course){
        HashMap<String, List<String>> tutorials = new HashMap<>();
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

                int typeId = Integer.parseInt(obj.get("iid").toString());
                String type = (String) types.get(typeId);
                String subType = type.substring(0, 3);

                if(courseCode.equals(course) && !subType.equals("Lec") & !subType.equals("Dro")){
                    int locationId = Integer.parseInt(obj.get("lid").toString());
                    String location = (String) locas.get(locationId);

                    double durationD = Double.parseDouble(obj.get("dur").toString());
                    int duration = (int) (durationD*60);

                    String day = JSON.convertDay(Integer.parseInt(obj.get("day").toString()));
                    String start = JSON.convertTime(Double.parseDouble(obj.get("start").toString()));

                    String title = subType + " " + duration + " min";
                    String desp = type + " " + day + " " + start + " " + location;

                    if(tutorials.containsKey(title)){
                        tutorials.get(title).add(desp);
                    }
                    else{
                        List<String> list = new ArrayList<>();
                        list.add(desp);
                        tutorials.put(title, list);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tutorials;
    }

}
