package cs.anu.edu.au.comp2100.weiming.server;

import cs.anu.edu.au.comp2100.weiming.object.Course;

import java.util.List;
import java.util.ArrayList;

import OkHttpClient;

public class ISISimpl {

    private final OkHttpClient client = new OkHttpClient();

    List<Course> getCourseList() {

        List<Course> course_list = new ArrayList<Course>();


        return course_list;
    }

}
