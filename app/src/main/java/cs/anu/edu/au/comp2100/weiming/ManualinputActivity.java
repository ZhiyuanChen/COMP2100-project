package cs.anu.edu.au.comp2100.weiming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.NumberPicker;

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

        //Return button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    NumberPicker.OnValueChangeListener onValueChangeListener = new NumberPicker.OnValueChangeListener(){
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        }
    };

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
