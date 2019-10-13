package cs.anu.edu.au.comp2100.weiming.object;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class JSON {
    private static final String ID ="id";
    private static final String NID ="nid.json";
    private static final String IID ="iid.json";
    private static final String LID ="lid.json";
    private static final String START ="start";
    private static final String DUR ="dur";
    private static final String WEEKS ="weeks";
    private static final String DAY ="day";

    String nidpath = "/Users/sallyzhao/ANU/COMP2100/gp19s2/app/src/main/java/cs/anu/edu/au/comp2100/weiming/object/nid.json";
    String iidpath = "/Users/sallyzhao/ANU/COMP2100/gp19s2/app/src/main/java/cs/anu/edu/au/comp2100/weiming/object/iid.json";
    String lidpath = "/Users/sallyzhao/ANU/COMP2100/gp19s2/app/src/main/java/cs/anu/edu/au/comp2100/weiming/object/lid.json";
    String evnpath = "/Users/sallyzhao/ANU/COMP2100/gp19s2/app/src/main/java/cs/anu/edu/au/comp2100/weiming/object/events.json";


    //nid (course)
    public List loadCourseName(String filePath) {
        File f = new File(filePath);
        List<Course> courses = new ArrayList<>();
        try {
            JSONArray array = (JSONArray) JSONValue.parse(new FileReader(f));
            for (int i = 0; i <array.size();i++) {
                String str = (String) array.get(i);
                Course newCourse = new Course();
                String[] courseString = str.split("_S2 ");
                newCourse.setCode(courseString[0]);
                newCourse.setName(courseString[1]);
                courses.add(newCourse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courses;
    }

    //iid.json (type) and lid.json (location)
    public List loadInfo(String filepath){
        File f = new File(filepath);
        List<String> info = new ArrayList<>();
        try {
            JSONArray array = (JSONArray) JSONValue.parse(new FileReader(f));
            for (int i = 0; i < array.size();i++) {
                String str = (String) array.get(i);
                info.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return info;
    }

    //event to courseSchedule
    public List loadEvent(String nidpath, String iidpath, String lidpath, String eventpath) {
        File f = new File(eventpath);
        try {
            List<Course> courses = loadCourseName(nidpath);
            List <String> types = loadInfo(iidpath);
            List <String> locations = loadInfo(lidpath);
            JSONArray array = (JSONArray) JSONValue.parse(new FileReader(f));
            for (int i = 0; i < array.size(); i++) {
                JSONObject obj = (JSONObject) array.get(i);

                int courseId = Integer.parseInt(obj.get("nid").toString());
                Course course = courses.get(courseId);
                String courseName = course.getCode() + " " + course.getName();

                int typeId = Integer.parseInt(obj.get("iid").toString());
                String type = types.get(typeId);

                int locationId = Integer.parseInt(obj.get("lid").toString());
                String location = locations.get(locationId);

                double durationD = Double.parseDouble(obj.get("dur").toString());
                int duration = (int) durationD*60;

                int day = Integer.parseInt(obj.get("day").toString());

                double start = Double.parseDouble(obj.get("start").toString());

                String weeksStr = (String) obj.get("weeks");
                List<Integer> weeks = parseWeeks(weeksStr);
                for(int week : weeks){
                    Calendar startTime = weekToDate(week, day);
                }

                System.out.println(courseName + " " + type + " " + location + " " + duration + " " + noToDay(day) + " " + start);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList();
    }

    public List loadCourseTime(String filePath){
        File f = new File(filePath);
        List<Schedule> schedules = new ArrayList<>();
        try {
            JSONArray array = (JSONArray) JSONValue.parse(new FileReader(f));
            for (int i = 0; i < array.size();i++) {
                JSONObject obj = (JSONObject) array.get(i);
                //Schedule schedule = new Schedule();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return schedules;
    }

    public String noToDay(int day){
        if(day == 0) return "Monday";
        if(day == 1) return "Tuesday";
        if(day == 2) return "Wednesday";
        if(day == 3) return "Thursday";
        if(day == 4) return "Friday";
        return null;
    }

    public List<Integer> parseWeeks(String weeksStr){
        List<Integer> weeks = new ArrayList<>();
        String[] split = weeksStr.split(",");
        for(String str : split){
            if(!str.contains("‑")){
                int week = Integer.parseInt(str);
                weeks.add(week);
            }
            else{
                String[] period = str.split("‑");
                int start = Integer.parseInt(period[0]);
                int end = Integer.parseInt(period[1]);
                for(int i = start; i <= end; i ++){
                    weeks.add(i);
                }
            }
        }
        return weeks;
    }

    public Calendar weekToDate(int week, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        calendar.set(Calendar.DAY_OF_WEEK, day+2);
        return calendar;
    }

    public static void main(String[] args) {
        JSON json = new JSON();
        List list = json.loadEvent(json.nidpath, json.iidpath, json.lidpath, json.evnpath);
    }

}
