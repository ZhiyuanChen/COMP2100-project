package cs.anu.edu.au.comp2100.weiming;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import java.util.ArrayList;

import petrov.kristiyan.colorpicker.ColorPicker;

public class SettingsActivity extends AppCompatActivity{

    public static ArrayList<String> settings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<String> temp = DefaultSettingFileHelper.readData(this);
        if(temp.size() == 8){
            settings = temp;
        } else{
            settings.add("" +getResources().getColor(android.R.color.background_light));
            settings.add("true");
            settings.add("" + getResources().getColor(R.color.colorAccent));
            settings.add("true");
            settings.add("3");
            settings.add("1");
            settings.add("0");
            settings.add("60");
        }
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
        return true;
    }


    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }

        @Override
        public boolean onPreferenceTreeClick(final androidx.preference.Preference preference) {
            String key = preference.getKey();

            if(key.equals("background")){
                ColorPicker colorPicker = new ColorPicker(getActivity());
                colorPicker.setRoundColorButton(true)
                        .setTitle("Choose Event Color")
                        .setColorButtonMargin(10,7,10,7)
                        .setColors(R.array.light_colors)
                        .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                            @Override
                            public void onChooseColor(int position, int color) {
                                if(color != 0){
                                    Spannable summary = new SpannableString("Current color " + position);
                                    summary.setSpan(new ForegroundColorSpan(color), 0, summary.length(), 0);
                                    preference.setSummary(summary);
                                    MainActivity.mWeekView.setBackgroundColor(color);
                                }
                            }
                            @Override
                            public void onCancel(){
                            }
                        });
                colorPicker.show();
            }

            if (key.equals("now_line")) {
                SwitchPreferenceCompat switchPreference = (SwitchPreferenceCompat) preference;
                boolean on = switchPreference.isChecked();
                if (on) {
                    settings.set(1, "true");
                } else {
                    settings.set(1, "false");
                }
                DefaultSettingFileHelper.writeData(settings, getContext());
            }

            if(key.equals("line_color")){
                ColorPicker colorPicker = new ColorPicker(getActivity());
                colorPicker.setRoundColorButton(true)
                        .setTitle("Choose Event Color")
                        .disableDefaultButtons(false)
                        .setColorButtonMargin(10,7,10,7)
                        .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                            @Override
                            public void onChooseColor(int position, int color) {
                                if(color != 0){
                                    Spannable summary = new SpannableString("Current color " + position);
                                    summary.setSpan(new ForegroundColorSpan(color), 0, summary.length(), 0);
                                    preference.setSummary(summary);
                                    MainActivity.mWeekView.setNowLineColor(color);
                                }
                            }
                            @Override
                            public void onCancel(){
                            }
                        });
                colorPicker.show();
            }

            if (key.equals("AMPM_Mode")) {
                SwitchPreferenceCompat switchPreference = (SwitchPreferenceCompat) preference;
                MainActivity.mWeekView.setShowNowLine(switchPreference.isChecked());
            }

            if (key.equals("dayviews")) {
                ListPreference dayviewsPre = (ListPreference) preference;
                dayviewsPre.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        String value = newValue.toString();
                        return true;
                    }
                });
            }

            if (key.equals("starthr")) {
                ListPreference dayviewsPre = (ListPreference) preference;
                dayviewsPre.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        String value = newValue.toString();
                        settings.set(5, value);
                        DefaultSettingFileHelper.writeData(settings, getContext());
                        return true;
                    }
                });
            }

            if (key.equals("startWeek")) {
                ListPreference dayviewsPre = (ListPreference) preference;
                dayviewsPre.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        String value = newValue.toString();
                        settings.set(6, value);
                        DefaultSettingFileHelper.writeData(settings, getContext());
                        return true;
                    }
                });
            }

            if (key.equals("default_event_duration")) {
                ListPreference dayviewsPre = (ListPreference) preference;
                dayviewsPre.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        String value = newValue.toString();
                        settings.set(7, value);
                        DefaultSettingFileHelper.writeData(settings, getContext());
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