package cs.anu.edu.au.comp2100.weiming;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.RectF;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        MonthLoader.MonthChangeListener,
        WeekView.EventClickListener,
        WeekView.EventLongPressListener,
        WeekView.EmptyViewLongPressListener
{

    private WeekView mWeekView;
    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private int mWeekViewType = TYPE_THREE_DAY_VIEW;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private ArrayList<WeekViewEvent> events = new ArrayList<>();

    LayoutInflater inflater;

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


        //navigator setup
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        //timetable
        events = EventsFileHelper.readData(this);
        mWeekView = findViewById(R.id.weekView);
        // Set an action when any event is clicked.
        mWeekView.setOnEventClickListener(this);
        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(this);
        // Set empty long press listener
        mWeekView.setEmptyViewLongPressListener(this);
        // Set long press listener for events.
        mWeekView.setEventLongPressListener(this);

        inflater = getLayoutInflater();
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


    public String getEventTitle(Calendar time) {
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH)+1, time.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        // Populate the week view with some events
        if (newMonth == Calendar.getInstance().get(Calendar.MONTH))
            return events;
        else {
            return new ArrayList<>();
        }
    }


    // Add event
    public void addEvent(final Calendar startTime){
        Toast.makeText(this, "Add Clicked: Add event?", Toast.LENGTH_SHORT).show();

        // initialize
        View dialog = inflater.inflate(R.layout.dialog_add_event, null);
        final EditText enterStart = dialog.findViewById(R.id.pick_start_txt);
        System.out.println(startTime.toString());
        System.out.println(enterStart.getHint());
        enterStart.setHint("WHY?");
        System.out.println(enterStart.getHint());
        enterStart.invalidate();

        // Build a alert dialog

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Add an event");
        builder.setView(inflater.inflate(R.layout.dialog_add_event, null));

        //messages
        int endYear, endMonth, endDate, endHour, endMin;

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.out.println(events.size());

                Calendar endTime = Calendar.getInstance();
                endTime.set(2019, 8, 22, 20, 00);
                WeekViewEvent event2 = new WeekViewEvent(0,"try",startTime, endTime);
                event2.setColor(getResources().getColor(R.color.yellow));
                events.add(event2);

                onMonthChange(2019, 9);
                mWeekView.notifyDatasetChanged();

                //file_helper
                EventsFileHelper.writeData(events, getApplicationContext());
//                Toast toast = Toast.makeText(getApplicationContext(), "Course Deleted", Toast.LENGTH_SHORT);
//                View toastView = toast.getView();
//                toastView.getBackground().setColorFilter(getResources().getColor(R.color.pink), PorterDuff.Mode.SRC_IN);
//                toast.show();
            }
        });

        builder.show();

    }


    // Click event to show detail
    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(this, "Event Clicked: Detail?" + event.getName(), Toast.LENGTH_SHORT).show();
    }


    // Long press event to delete
    @Override
    public void onEventLongPress(final WeekViewEvent event, RectF eventRect) {
        Toast.makeText(this, "Event Long pressed: Edit? " + event.getName(), Toast.LENGTH_SHORT).show();

        String eventStr = event.getName();
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Delete");
        builder.setMessage("Delete event "+ eventStr +" ?");

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                events.remove(event);
                System.out.println(events.size());
                mWeekView.notifyDatasetChanged();

                //file_helper
                EventsFileHelper.writeData(events, getApplicationContext());
//                Toast toast = Toast.makeText(getApplicationContext(), "Course Deleted", Toast.LENGTH_SHORT);
//                View toastView = toast.getView();
//                toastView.getBackground().setColorFilter(getResources().getColor(R.color.pink), PorterDuff.Mode.SRC_IN);
//                toast.show();
            }
        });

        builder.show();
    }


    // Long press empty view to add an event
    // Set start time as where it is pressed
    @Override
    public void onEmptyViewLongPress(Calendar time) {
        Toast.makeText(this, "Empty place Long pressed: Add?" + getEventTitle(time), Toast.LENGTH_SHORT).show();
        addEvent(time);
    }


    //Different View-options
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_now:
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                mWeekView.goToHour(hour);
                mWeekView.goToToday();
                System.out.println(hour);
                return true;

            case R.id.action_day_view:
                if (mWeekViewType != TYPE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(1);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;

            case R.id.action_three_day_view:
                if (mWeekViewType != TYPE_THREE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_THREE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(3);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;

            case R.id.action_week_view:
                if (mWeekViewType != TYPE_WEEK_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_WEEK_VIEW;
                    mWeekView.setNumberOfVisibleDays(7);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                }
                return true;

            case R.id.go_to:
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);


                //pop up a calendar picker
                final DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, mDateSetListener,
                        year, month, day);

                //set a listener
                mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    }
                };

                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialog.setButton(DialogInterface.BUTTON_POSITIVE, "GO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        DatePicker datePicker = dialog.getDatePicker();
                        mDateSetListener.onDateSet(datePicker, datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                        Calendar calNew = Calendar.getInstance();
                        calNew.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                        mWeekView.goToDate(calNew);
                    }
                });

                dialog.show();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar_weekview, menu);
        return true;
    }

}
