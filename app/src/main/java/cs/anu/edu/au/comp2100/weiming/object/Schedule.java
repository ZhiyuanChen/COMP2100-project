package cs.anu.edu.au.comp2100.weiming.object;

import android.location.Location;

import androidx.annotation.NonNull;

import com.alamkanak.weekview.WeekViewEvent;

import java.util.Calendar;

import cs.anu.edu.au.comp2100.weiming.MainActivity;

public class Schedule {
  private Course course;
  private Category category;
  private Calendar start_time;
  private Calendar end_time;
  private Location location;
  private String description;

  public Schedule(
      Course course,
      Category category,
      Calendar start_time,
      Calendar end_time,
      Location location,
      String description) {
    this.course = course;
    this.category = category;
    this.start_time = start_time;
    this.end_time = end_time;
    this.location = location;
    this.description = description;
  }

  public Schedule(
          Course course,
          Category category,
          Calendar start_time,
          Calendar end_time,
          String description) {
    this.course = course;
    this.category = category;
    this.start_time = start_time;
    this.end_time = end_time;
    this.location = null;
    this.description = description;
  }

  public Schedule(
          Course course,
          Category category,
          Calendar start_time,
          int interval,
          Location location,
          String description) {
    this.course = course;
    this.category = category;
    this.start_time = start_time;
    this.end_time = Calendar.getInstance();
    this.end_time.setTimeInMillis(this.start_time.getTimeInMillis() + interval * 60000);
    this.location = location;
    this.description = description;
  }

  public Schedule(
          Course course,
          Category category,
          Calendar start_time,
          int interval,
          String description) {
    this.course = course;
    this.category = category;
    this.start_time = start_time;
    this.end_time = Calendar.getInstance();
    this.end_time.setTimeInMillis(this.start_time.getTimeInMillis() + interval * 60000);
    this.location = null;
    this.description = description;
  }

  @NonNull
  @Override
  public String toString() {
    String courseCode = this.course.getCode();
    String category = this.category.getName();
    return courseCode + " " + category;
  }

  public WeekViewEvent toEvent(){
    WeekViewEvent event = new WeekViewEvent();
    event.setName(this.toString());
    event.setLocation(this.location.toString());
    event.setStartTime(this.start_time);
    event.setEndTime(this.end_time);
    return event;
  }

  public void addToEventsList(){
    WeekViewEvent event = this.toEvent();
    MainActivity.addEvent(event);
  }
}

enum Category {
  LEC("Lecture", 0),
  TUT("Tutorial", 1),
  LAB("Laboratorial", 2),
  EXAM("Examination", 3),
  OHUR("Office Hours", 4),
  DPIN("Drop-in Session", 5);


  private String name;
  private int index;

  Category(String name, int index) {
    this.name = name;
    this.index = index;
  }

  public static String getName(int index) {
    for (Category category : Category.values()) {
      if (category.getIndex() == index) {
        return category.name;
      }
    }
    return null;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }
}
