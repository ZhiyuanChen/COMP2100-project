package cs.anu.edu.au.comp2100.weiming;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.preference.PreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import cs.anu.edu.au.comp2100.weiming.object.JSON;

public class TutorialActivity extends AppCompatActivity {

    public static SharedPreferences preferences;
    public static LinearLayout layout;
    public ArrayList<String> selectedCourses;
    public ArrayList<String> selectedTuts;
    public ArrayList<String> availableTuts;
    public ListView courseManage;
    public ListView tutorialManage;
    public ArrayAdapter courseAdapter;
    public ArrayAdapter<String> tutorialAdapter;
    public String currentCourse;
    public String currentCourseFile;

    private AutoCompleteTextView addTutTxt;
    private Button addTutBtn;
    private ArrayAdapter<String> autoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        layout = findViewById(R.id.tutorial_layout);
        currentCourse = "";
        courseManage = findViewById(R.id.courseManage);
        tutorialManage = findViewById(R.id.tutorialManage);
        addTutTxt = findViewById(R.id.addTutTxt);
        addTutBtn = findViewById(R.id.addTutBtn);

        //load
        selectedCourses = CoursesFileHelper.readData(this, "courseSelected.dat");
//        selectedTutorial = new HashMap<>();
//        for(String course : selectedCourses){
//            String fileName = course + ".dat";
//            ArrayList<String> tutorials = CoursesFileHelper.readData(this, fileName);
//            selectedTutorial.put(course, tutorials);
//        }

        //set up adapters
        selectedTuts = new ArrayList<>();
        availableTuts = new ArrayList<>();
        courseAdapter = new ArrayAdapter<>(this, R.layout.custom_listview, selectedCourses);
        tutorialAdapter = new ArrayAdapter<>(TutorialActivity.this, R.layout.custom_listview, selectedTuts);
        autoAdapter = new ArrayAdapter<>(this, R.layout.custom_listview, availableTuts);
        courseManage.setAdapter(courseAdapter);
        tutorialManage.setAdapter(tutorialAdapter);
        autoAdapter.setNotifyOnChange(true);
        addTutTxt.setAdapter(autoAdapter);

        //select a course
        courseManage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //select effect
                for(int i = 0; i < selectedCourses.size(); i ++){
                    parent.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                }
                view.setBackgroundColor(getResources().getColor(R.color.pink));

                //load current tutorials
                currentCourse = selectedCourses.get(position);
                currentCourseFile = currentCourse + ".dat";
                selectedTuts.clear();
                selectedTuts.addAll(CoursesFileHelper.readData(getApplicationContext(), currentCourseFile));
                Log.d("initialize", ""+selectedTuts.size());
                tutorialAdapter.notifyDataSetChanged();

                //load available tutorials
                //set auto complete according to current course
                autoAdapter.clear();
                autoAdapter.addAll(loadTutorials(currentCourse));
                autoAdapter.notifyDataSetChanged();
            }
        });


        //add button
        addTutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tut = addTutTxt.getText().toString();
                //not selected course
                if(!selectedCourses.contains(currentCourse)){
                    addTutTxt.setText("");
                    Toast toast = Toast.makeText(getApplicationContext(), "Please select a course", Toast.LENGTH_SHORT);
                    View toastView = toast.getView();
                    toastView.getBackground().setColorFilter(getResources().getColor(R.color.pink), PorterDuff.Mode.SRC_IN);
                    toast.show();
                }
                //not in suggestion list
                else if(!isTutorial(tut)){
                    addTutTxt.setText("");
                    Toast toast = Toast.makeText(getApplicationContext(), "Not a tutorial", Toast.LENGTH_SHORT);
                    View toastView = toast.getView();
                    toastView.getBackground().setColorFilter(getResources().getColor(R.color.pink), PorterDuff.Mode.SRC_IN);
                    toast.show();
                }
                //same type
                else if(tutSameType(selectedTuts, tut)){
                    addTutTxt.setText("");
                    Toast toast = Toast.makeText(getApplicationContext(), "Duplicate tutorial", Toast.LENGTH_SHORT);
                    View toastView = toast.getView();
                    toastView.getBackground().setColorFilter(getResources().getColor(R.color.pink), PorterDuff.Mode.SRC_IN);
                    toast.show();
                }
                //add tut
                else{
                    tutorialAdapter.add(tut);
                    Log.d("after", ""+selectedTuts.size());
                    CoursesFileHelper.writeData(selectedTuts, getApplicationContext(), currentCourseFile);
                    addTutTxt.setText("");
                    Toast toast = Toast.makeText(getApplicationContext(), "Tutorial Added", Toast.LENGTH_SHORT);
                    View toastView = toast.getView();
                    toastView.getBackground().setColorFilter(getResources().getColor(R.color.green), PorterDuff.Mode.SRC_IN);
                    toast.show();
                }
            }
        });

        //Return button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //background color
        int backgroundColor = preferences.getInt("background", 0);
        layout.setBackgroundColor(backgroundColor);


        //delete
        tutorialManage.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                String tutorial = selectedTuts.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(TutorialActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Delete");
                builder.setMessage("Delete '"+tutorial+"' ?");

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        selectedTuts.remove(position);
                        tutorialAdapter.notifyDataSetChanged();
                        Log.d("delete", ""+selectedTuts.size());
                        CoursesFileHelper.writeData(selectedTuts, getApplicationContext(), currentCourseFile);

                        //file_helper
//                        CoursesFileHelper.writeData(takenCourses, getApplicationContext(), 0);
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


    public boolean tutSameType(ArrayList<String> list, String tut){
        for(String str : list){
            String type = str.substring(0, 3);
            if (tut.substring(0, 3).equals(type)) return true;
        }
        return false;
    }

    public boolean isTutorial(String tut){
        Adapter adapter = addTutTxt.getAdapter();
        for(int i = 0; i < adapter.getCount(); i ++){
            String temp = (String) adapter.getItem(i);
            if(temp.equals(tut)){
                return true;
            }
        }
        return false;
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

    public  ArrayList<String> loadTutorials(String course){
        ArrayList<String> tutorials = new ArrayList<>();
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

                    String desp = type + " " + duration + "min " + day + " " + start + " " + location;
                    tutorials.add(desp);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tutorials;
    }

}
