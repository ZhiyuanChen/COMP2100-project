package cs.anu.edu.au.comp2100.weiming.server;

import cs.anu.edu.au.comp2100.weiming.object.Course;
import cs.anu.edu.au.comp2100.weiming.object.Schedule;

import java.util.List;

public interface TimeTable {

    List<Schedule> getScheduleList();
    List<Schedule> getScheduleList(Course course);
    List<Schedule> getScheduleListByCode(String code);

}
