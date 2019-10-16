package cs.anu.edu.au.comp2100.weiming.object;

import com.alamkanak.weekview.WeekViewEvent;

import java.util.Calendar;

public class CourseSchedule extends Schedule {

  private Course course;
  private String category;
  private Calendar startTime;
  private Calendar endTime;
  private String location;

  // constructor with all
  public CourseSchedule(
      Course course, String category, Calendar startTime, Calendar endTime, String location) {
    super(startTime, endTime);
    this.course = course;
    this.category = category;
    this.location = location;
  }

  // constructor without location
  public CourseSchedule(Course course, String category, Calendar startTime, Calendar endTime) {
    super(startTime, endTime);
    this.course = course;
    this.category = category;
  }

  // constructor with interval with location
  public CourseSchedule(
      Course course, String category, Calendar startTime, int interval, String location) {
    super(startTime, interval, location);
    this.course = course;
    this.category = category;
  }

  // constructor with interval without location
  public CourseSchedule(Course course, String category, Calendar startTime, int interval) {
    super(startTime, interval);
    this.course = course;
    this.category = category;
  }

  @Override
  public String getName() {
    String courseCode = this.course.getCode();
    String category = this.category;
    return courseCode + " " + category;
  }

  @Override
  public WeekViewEvent toEvent() {
    return super.toEvent();
  }

  @Override
  public void addToEventsList() {
    super.addToEventsList();
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

  public String getName(int index) {
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
