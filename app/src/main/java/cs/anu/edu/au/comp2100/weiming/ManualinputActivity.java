package cs.anu.edu.au.comp2100.weiming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.NumberPicker;
import android.widget.Toast;

public class ManualinputActivity extends AppCompatActivity {

    private NumberPicker collegePicker;
    private NumberPicker degreePicker;
    private NumberPicker majorPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_input);

        collegePicker = findViewById(R.id.college_picker);
        collegePicker.setOnValueChangedListener(onValueChangeListener);
        collegePicker.setMinValue(0);
        collegePicker.setMaxValue(20);

        degreePicker = findViewById(R.id.degree_picker);
        degreePicker.setOnValueChangedListener(onValueChangeListener);
        degreePicker.setMinValue(0);
        degreePicker.setMaxValue(20);

        majorPicker = findViewById(R.id.major_picker);
        majorPicker.setOnValueChangedListener(onValueChangeListener);
        majorPicker.setMinValue(0);
        majorPicker.setMaxValue(20);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the home; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent infoIntent = new Intent(this, MainActivity.class);
        startActivity(infoIntent);
        return super.onOptionsItemSelected(item);
    }

    NumberPicker.OnValueChangeListener onValueChangeListener = new NumberPicker.OnValueChangeListener(){
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        }
    };
}
