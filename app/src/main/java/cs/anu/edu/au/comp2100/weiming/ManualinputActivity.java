package cs.anu.edu.au.comp2100.weiming;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;

public class ManualinputActivity extends AppCompatActivity implements
        View.OnClickListener,
        AdapterView.OnItemSelectedListener {

    Spinner collegeSpinner;
    Spinner degreeSpinner;

    private EditText itemET;
    private Button btn;
    private ListView itemsList;

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_input);

        //spinners
        collegeSpinner = findViewById(R.id.info_college_spinner);
        ArrayAdapter<CharSequence> colleges = ArrayAdapter.createFromResource(this, R.array.All_ANU_colleges, R.layout.custom_spinner);
        colleges.setDropDownViewResource(R.layout.custom_spinner);
        collegeSpinner.setAdapter(colleges);
        degreeSpinner = findViewById(R.id.info_degree_spinner);
        degreeSpinner.setOnItemSelectedListener(this);


        //add a course
        itemET = findViewById(R.id.input_courses);
        btn = findViewById(R.id.addCourse_btn);

        items = ListFileHelper.readData(this);
        itemsList = findViewById(R.id.added_courses);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

        //individual items
        itemsList.setAdapter(adapter);
        itemsList.setLongClickable(true);
        setDeleteIndividual();
        setDeleteAll();

        //press btn to add course
        btn.setOnClickListener(this);

        //Return button
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
            adapter.add(itemEntered);
            itemET.setText("");
            //file_helper
            ListFileHelper.writeData(items, this);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void setDeleteIndividual(){
        itemsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View v, final int position, long id) {
                String course = items.get(position);

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
                        items.remove(position);
                        adapter.notifyDataSetChanged();

                        //file_helper
                        ListFileHelper.writeData(items, getApplicationContext());
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
}
