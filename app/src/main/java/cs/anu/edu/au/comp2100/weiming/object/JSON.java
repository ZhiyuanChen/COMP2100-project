package cs.anu.edu.au.comp2100.weiming.object;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

import cs.anu.edu.au.comp2100.weiming.object.College;
import cs.anu.edu.au.comp2100.weiming.object.Course;
import cs.anu.edu.au.comp2100.weiming.object.Schedule;


public class JSON {
    private static final String ID ="id";
    private static final String NID ="nid";
    private static final String IID ="iid";
    private static final String LID ="lid";
    private static final String START ="start";
    private static final String DUR ="dur";
    private static final String WEEKS ="weeks";
    private static final String DAY ="day";



    public List loadCourseName(String filePath) {
        File f = new File(filePath);
        List<Course> courses = new ArrayList<>();
        try {
            JSONArray array = (JSONArray) JSONValue.parse(new FileReader(f));
            for (int i = 0; i<array.size();i++) {
                JSONObject obj = (JSONObject) array.get(i);
                Course newCourse = new Course();
                String c = obj.toString();
                String[] courseString = c.split("_S2 ");
                newCourse.setCode(courseString[0]);
                newCourse.setName(courseString[1]);
                courses.add(newCourse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public List loadCourseTime(String filePath){
        File f = new File(filePath);
        List<Schedule> schedules = new ArrayList<>();
        try {
            JSONArray array = (JSONArray) JSONValue.parse(new FileReader(f));
            for (int i = 0; i<array.size();i++) {
                JSONObject obj = (JSONObject) array.get(i);
                //Schedule schedule = new Schedule();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return schedules;
    }





}
