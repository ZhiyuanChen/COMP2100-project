package cs.anu.edu.au.comp2100.weiming.object;

import com.alamkanak.weekview.WeekViewEvent;

import java.util.Calendar;

import cs.anu.edu.au.comp2100.weiming.MainActivity;

public class Schedule {
  private String name;
  private Calendar start_time;
  private Calendar end_time;
  private String location;


  //constructor with location
  public Schedule(
      Calendar start_time,
      Calendar end_time,
      String location) {
    this.start_time = start_time;
    this.end_time = end_time;
    this.location = location;
  }


  //constructor without location
  public Schedule(
          Calendar start_time,
          Calendar end_time) {
    this.start_time = start_time;
    this.end_time = end_time;
    this.location = null;
  }

  //constructor accepting duration interval with location
  public Schedule(
          Calendar start_time,
          int interval,
          String location) {
    this.start_time = start_time;
    this.end_time = Calendar.getInstance();
    this.end_time.setTimeInMillis(this.start_time.getTimeInMillis() + interval * 60000);
    this.location = location;
  }

  //constructor accepting duration interval without location
  public Schedule(
          Calendar start_time,
          int interval) {
    this.start_time = start_time;
    this.end_time = Calendar.getInstance();
    this.end_time.setTimeInMillis(this.start_time.getTimeInMillis() + interval * 60000);
    this.location = null;
  }

  public String getName() {
    return name;
  }

  public WeekViewEvent toEvent(){
    WeekViewEvent event = new WeekViewEvent();
    event.setName(this.getName());
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
