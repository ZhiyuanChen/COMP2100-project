package cs.anu.edu.au.comp2100.weiming.server;

import cs.anu.edu.au.comp2100.weiming.object.Course;

import java.util.ArrayList;
import java.util.List;

import cs.anu.edu.au.comp2100.weiming.object.Course;
import okhttp3.OkHttpClient;

public class ISISimpl {

    private final OkHttpClient client = new OkHttpClient();

    List<Course> getCourseList() {

        List<Course> course_list = new ArrayList<Course>();


        return course_list;
    }

}
