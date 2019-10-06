package cs.anu.edu.au.comp2100.weiming;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreferenceCompat;

import petrov.kristiyan.colorpicker.ColorPicker;

public class SettingsActivity extends AppCompatActivity{


    public static SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();

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


    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            //background color summary set
            Preference background = findPreference("background");
            int backgroundColor = preferences.getInt("background", 0);
            Spannable summary1 = new SpannableString("Current color  (" + backgroundColor +")");
            summary1.setSpan(0, 0, summary1.length(), 0);
            background.setSummary(summary1);

            //default event color summary set
            Preference nowLine = findPreference("eventColor");
            int lineColor = preferences.getInt("eventColor", getResources().getColor(R.color.colorAccent));
            Spannable summary2 = new SpannableString("Current color  (" + lineColor +")");
            summary2.setSpan(0, 0, summary2.length(), 0);
            nowLine.setSummary(summary2);


            ListPreference startHr = findPreference("startHr");
            startHr.setValue("midnight");
            ListPreference eventDuration = findPreference("eventDuration");
            eventDuration.setValue("60");
        }


        @Override
        public boolean onPreferenceTreeClick(final Preference preference) {
            String key = preference.getKey();

            if(key.equals("background")){
                ColorPicker colorPicker = new ColorPicker(getActivity());
                colorPicker
                        .setTitle("Choose Event Color")
                        .setColorButtonMargin(10,7,10,7)
                        .setColors(R.array.light_colors)
                        .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                            @Override
                            public void onChooseColor(int position, int color) {
                                if(color != 0){
                                    Spannable summary = new SpannableString("Current color (" + color +")");
                                    summary.setSpan(0, 0, summary.length(), 0);
                                    preference.setSummary(summary);
                                    preferences.edit().putInt("background", color).apply();
                                    MainActivity.mWeekView.setBackgroundColor(color);
                                }
                            }
                            @Override
                            public void onCancel(){
                            }
                        });
                colorPicker.show();
            }

            if (key.equals("nowLine")) {
                SwitchPreferenceCompat switchPreference = (SwitchPreferenceCompat) preference;
                MainActivity.mWeekView.setShowNowLine(switchPreference.isChecked());
                MainActivity.mWeekView.setShowDistinctPastFutureColor(switchPreference.isChecked());
            }

            if(key.equals("eventColor")){
                ColorPicker colorPicker = new ColorPicker(getActivity());
                colorPicker.setRoundColorButton(true)
                        .setTitle("Choose Default Event Color")
                        .disableDefaultButtons(false)
                        .setColorButtonMargin(10,7,10,7)
                        .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                            @Override
                            public void onChooseColor(int position, int color) {
                                if(color != 0){
                                    Spannable summary = new SpannableString("Current color (" + color + ")");
                                    summary.setSpan(0, 0, summary.length(), 0);
                                    preference.setSummary(summary);
                                    preferences.edit().putInt("eventColor", color).apply();
                                    MainActivity.defaultEventColor = color;
                                }
                            }
                            @Override
                            public void onCancel(){
                            }
                        });
                colorPicker.show();
            }

            if (key.equals("ampmMode")) {
                SwitchPreferenceCompat switchPreference = (SwitchPreferenceCompat) preference;
                MainActivity.setupDateTimeInterpreter(switchPreference.isChecked());
            }

            if(key.equals("dayViews")) {
                ListPreference dayViews = (ListPreference) preference;
                dayViews.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        int value = Integer.parseInt((String) newValue);
                        MainActivity.changeView(value);
                        return true;
                    }
                });
            }


            if (key.equals("startHr")) {
                final ListPreference startHr = (ListPreference) preference;
                startHr.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        MainActivity.goTo(newValue.toString());
                        return true;
                    }
                });
            }

            if (key.equals("weekStart")) {
                final ListPreference weekStart = (ListPreference) preference;
                weekStart.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        MainActivity.mWeekView.setFirstDayOfWeek(Integer.parseInt(weekStart.getValue()));
                        return true;
                    }
                });
            }

            if (key.equals("eventDuration")) {
                ListPreference eventDuration = (ListPreference) preference;
                eventDuration.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        return true;
                    }
                });
            }

            return super.onPreferenceTreeClick(preference);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}