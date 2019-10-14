package cs.anu.edu.au.comp2100.weiming;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.preference.PreferenceManager;

import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;

public class ManageActivity extends AppCompatActivity {

    public static SharedPreferences preferences;
    public static LinearLayout layout;
    public ArrayList<String> selectedCourses;
    public ArrayList<WeekViewEvent> events;
    public ArrayList<String> eventsStr;
    public ListView courseManage;
    public ListView otherManage;
    public ArrayAdapter courseAdapter;
    public ArrayAdapter eventAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        layout = findViewById(R.id.manage_layout);
        courseManage = findViewById(R.id.courseManage);
        otherManage = findViewById(R.id.otherManage);

        //set current courses
        selectedCourses = CoursesFileHelper.readData(this, 1);
        courseAdapter = new ArrayAdapter<>(this, R.layout.custom_listview, selectedCourses);
        courseManage.setAdapter(courseAdapter);
        courseManage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        //events
        events = MainActivity.getEvents();
        eventsStr = new ArrayList<>();
        for(WeekViewEvent event : events){
            String eventStr = event.getName() +
                    " - Time: " + event.getStartTime().getTime().getDate() +
                    "/" + (event.getStartTime().getTime().getMonth() + 1) +
                    " Location: " + event.getLocation();
            eventsStr.add(eventStr);
        }
        eventAdapter = new ArrayAdapter<>(this, R.layout.custom_listview, eventsStr);
        otherManage.setAdapter(eventAdapter);
        otherManage.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                String eventStr = eventsStr.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(ManageActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Delete");
                builder.setMessage("Delete event " + eventStr + " ?");

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        eventsStr.remove(position);
                        events.remove(position);
                        eventAdapter.notifyDataSetChanged();
                        MainActivity.mWeekView.notifyDatasetChanged();

                        //file helper
                        EventsFileHelper.writeData(events, getApplicationContext());
                    }
                });
                builder.show();
                return true;
            }
        });

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
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
