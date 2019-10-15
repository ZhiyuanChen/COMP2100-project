package cs.anu.edu.au.comp2100.weiming;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class AppintroActivity extends AppIntro { //this class is about app instructions

  String[] Headings = {
    "Welcome to WeiMing Timetable",
    "Designed by ANU Students for ANU Students",
    "Course/Tutorial Enrolment",
    "Easy Management",
    "Quick Add",
    "Smart Recommendations",
    "Ready"
  };

    int[] Images = {
            R.drawable.ready,
            R.drawable.ready,
            R.drawable.ready,
            R.drawable.ready,
            R.drawable.ready,
            R.drawable.ready,
            R.drawable.ready
    };


    String[] Description = { //one line text for each page
            "Smart timetable assistant \n Help manage uni schedule at ease",
            "Updated with official ANU course information \n Support ANU account log in",
            "Enrol nid.json and tutorial from list",
            "Check overlapping tutorial times \n Manage your current nid.json",
            "Add your own iid.json",
            "Smart recommending nid.json based on what you have taken so far \n Tutorials recommendation to avoid time overlapping",
            "Start your journey!"
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int[] Colors = { //different color background for each page
                ContextCompat.getColor(getApplicationContext(), R.color.slide1),
                ContextCompat.getColor(getApplicationContext(), R.color.slide2),
                ContextCompat.getColor(getApplicationContext(), R.color.slide3),
                ContextCompat.getColor(getApplicationContext(), R.color.slide4),
                ContextCompat.getColor(getApplicationContext(), R.color.slide5),
                ContextCompat.getColor(getApplicationContext(), R.color.slide6),
                ContextCompat.getColor(getApplicationContext(), R.color.slide7),
        };

        for(int i = 0; i < Headings.length; i ++){ //build pages
            addSlide(AppIntroFragment.newInstance(Headings[i], Description[i], Images[i], Colors[i]));
        }
        setFadeAnimation();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent infoIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(infoIntent);
    }


    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent infoIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(infoIntent);
    }

    @Override
    public void finish() {
        super.finish();
    }
}