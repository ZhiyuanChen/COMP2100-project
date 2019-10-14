package cs.anu.edu.au.comp2100.weiming.object;


import android.widget.ListView;

import com.alamkanak.weekview.WeekViewEvent;

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
    //private static final String ID ="id";
    private static final String NID ="nid";
    private static final String IID ="iid";
    private static final String LID ="lid";
    private static final String START ="start";
    private static final String DUR ="dur";
    private static final String WEEKS ="weeks";
    private static final String DAY ="day";

    private static String absolutePath = new File("").getAbsolutePath();
    private static String nidpath = absolutePath + "/app/src/main/java/cs/anu/edu/au/comp2100/weiming/object/nid.json";
    private static String iidpath = absolutePath + "/app/src/main/java/cs/anu/edu/au/comp2100/weiming/object/iid.json";
    private static String lidpath = absolutePath + "/app/src/main/java/cs/anu/edu/au/comp2100/weiming/object/lid.json";
    private static String evnpath = absolutePath + "/app/src/main/java/cs/anu/edu/au/comp2100/weiming/object/events.json";


    //nid (course)
    public static List<String> loadCourse() {
        File f = new File(nidpath);
        List<String> courses = new ArrayList<>();
        try {
            JSONArray array = (JSONArray) JSONValue.parse(new FileReader(f));
            for (int i = 0; i <array.size();i++) {
                String str = (String) array.get(i);
//                Course newCourse = new Course();
//                String[] courseString = str.split("_S2 ");
//                newCourse.setCode(courseString[0]);
//                newCourse.setName(courseString[1]);
                courses.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courses;
    }

    //iid.json (type) and lid.json (location)
    public static List<String> loadInfo(String filepath){
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
    public static List<List<WeekViewEvent>> loadEvents(String course) {
        File f = new File(evnpath);
        List<WeekViewEvent> lectures = new ArrayList<>();
        List<WeekViewEvent> tutorials = new ArrayList<>();
        List<WeekViewEvent> dropin = new ArrayList<>();
        try {
            List<String> courses = loadInfo(nidpath);
            List <String> types = loadInfo(iidpath);
            List <String> locations = loadInfo(lidpath);
            JSONArray array = (JSONArray) JSONValue.parse(new FileReader(f));
            for (int i = 0; i < array.size(); i++) {
                JSONObject obj = (JSONObject) array.get(i);

                int courseId = Integer.parseInt(obj.get(NID).toString());
                String[] courseInfo = courses.get(courseId).split("_S2 ");
                String courseCode = courseInfo[0];

                if(courseCode.equals(course)){
                    int typeId = Integer.parseInt(obj.get(IID).toString());
                    String type = types.get(typeId);
                    String subType = type.substring(0, 3);

                    int locationId = Integer.parseInt(obj.get(LID).toString());
                    String location = locations.get(locationId);

                    double durationD = Double.parseDouble(obj.get(DUR).toString());
                    int duration = (int) durationD*60;

                    int day = Integer.parseInt(obj.get(DAY).toString());

                    double start = Double.parseDouble(obj.get(START).toString());

                    String weeksStr = (String) obj.get(WEEKS);
                    List<Integer> weeks = parseWeeks(weeksStr);
                    for(int week : weeks){
                        Calendar startTime = weekToDate(week, day);
                        Calendar endTime = weekToDate(week, day);

                        int[] time = parseStartTime(start);
                        startTime.set(Calendar.HOUR_OF_DAY, time[0]);
                        startTime.set(Calendar.MINUTE, time[1]);
                        endTime.set(Calendar.HOUR_OF_DAY, time[0]);
                        endTime.set(Calendar.MINUTE, time[1]);
                        endTime.add(Calendar.MINUTE, duration);

                        WeekViewEvent schedule = new WeekViewEvent();
                        schedule.setName(courseCode + " " + type);
                        schedule.setLocation(location);
                        schedule.setStartTime(startTime);
                        schedule.setEndTime(endTime);

                        if(subType.equals("Lec")){
                            lectures.add(schedule);
                        }
                        else if(subType.equals("Drp")){
                            dropin.add(schedule);
                        }
                        else{
                            tutorials.add(schedule);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<List<WeekViewEvent>> result = new ArrayList<>();
        result.add(lectures);
        result.add(tutorials);
        result.add(dropin);
        return result;
    }



    public static List<Integer> parseWeeks(String weeksStr){
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

    public static Calendar weekToDate(int week, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        calendar.set(Calendar.DAY_OF_WEEK, day+2);
        return calendar;
    }


    public static int[] parseStartTime(Double time){
        int hour = (int) Math.floor(time);
        int miniute = (int) ((time - hour)*60);
        return new int[]{hour, miniute};
    }



    public static void main(String[] args) {
        List<List<WeekViewEvent>> test = JSON.loadEvents("COMP2100");
        for(List<WeekViewEvent> list : test){
            for(WeekViewEvent weekViewEvent : list){
                System.out.println(weekViewEvent.getName() +" "+ weekViewEvent.getLocation() +" "+ weekViewEvent.getStartTime().getTime().getDay());
            }
        }
    }
}
