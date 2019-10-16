package cs.anu.edu.au.comp2100.weiming;

import android.content.SharedPreferences;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;

public class ManualinputActivity extends AppCompatActivity implements
        View.OnClickListener{

    public static SharedPreferences preferences;
    public static LinearLayout layout;

    Spinner collegeSpinner;
    AutoCompleteTextView degreeEdit;

    private EditText itemET;
    private Button btn;
    private ListView itemsList;

    private ArrayList<String> takenCourses;
    private ArrayAdapter<String> itemAdapter;
    private ArrayAdapter<CharSequence> collegeAdapter;
    private ArrayAdapter<CharSequence> degreeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_input);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        layout = findViewById(R.id.manualInput_layout);

        //spinners
        collegeSpinner = findViewById(R.id.info_college_spinner);
        collegeAdapter = ArrayAdapter.createFromResource(this, R.array.All_ANU_colleges, R.layout.custom_spinner);
        collegeAdapter.setDropDownViewResource(R.layout.custom_spinner);
        collegeSpinner.setAdapter(collegeAdapter);

        degreeEdit = findViewById(R.id.info_degree_edit);
        degreeAdapter = ArrayAdapter.createFromResource(this, R.array.degrees, R.layout.custom_autocomplete);
        degreeEdit.setAdapter(degreeAdapter);


        //add a course
        itemET = findViewById(R.id.input_courses);
        btn = findViewById(R.id.addCourse_btn);

        takenCourses = CoursesFileHelper.readData(this, 0);
        itemsList = findViewById(R.id.added_courses);

        itemAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, takenCourses);

        //individual takenCourses
        itemsList.setAdapter(itemAdapter);
        itemsList.setLongClickable(true);
        setDeleteIndividual();
        setDeleteAll();

        //press btn to add course
        btn.setOnClickListener(this);

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
        }        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar_home, menu);
        return true;
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.addCourse_btn){
            String itemEntered = itemET.getText().toString();
            itemAdapter.add(itemEntered);
            itemET.setText("");

            //file_helper
            CoursesFileHelper.writeData(takenCourses, this, 0);
            Toast toast = Toast.makeText(getApplicationContext(), "Course Added", Toast.LENGTH_SHORT);
            View toastView = toast.getView();
            toastView.getBackground().setColorFilter(getResources().getColor(R.color.green), PorterDuff.Mode.SRC_IN);
            toast.show();
        }
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    public void setDeleteIndividual(){
        itemsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View v, final int position, long id) {
                String course = takenCourses.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(ManualinputActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Delete");
                builder.setMessage("Delete course "+course+" ?");

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        takenCourses.remove(position);
                        itemAdapter.notifyDataSetChanged();

                        //file_helper
                        CoursesFileHelper.writeData(takenCourses, getApplicationContext(), 0);
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

    public void setDeleteAll(){
    }

    public ArrayList<String> getTakenCourses() {
        return takenCourses;
    }
}
