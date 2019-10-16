package cs.anu.edu.au.comp2100.weiming.object;

import com.alamkanak.weekview.WeekViewEvent;

import java.util.Calendar;

import cs.anu.edu.au.comp2100.weiming.MainActivity;

public class Schedule {
  private String name;
  private Calendar startTime;
  private Calendar endTime;
  private String location;


  //constructor with location
  public Schedule(
      Calendar startTime,
      Calendar endTime,
      String location) {
    this.startTime = startTime;
    this.endTime = endTime;
    this.location = location;
  }


  //constructor without location
  public Schedule(
          Calendar startTime,
          Calendar endTime) {
    this.startTime = startTime;
    this.endTime = endTime;
    this.location = null;
  }

  //constructor accepting duration interval with location
  public Schedule(
          Calendar startTime,
          int interval,
          String location) {
    this.startTime = startTime;
    this.endTime = Calendar.getInstance();
    this.endTime.setTimeInMillis(this.startTime.getTimeInMillis() + interval * 60000);
    this.location = location;
  }

  //constructor accepting duration interval without location
  public Schedule(
          Calendar startTime,
          int interval) {
    this.startTime = startTime;
    this.endTime = Calendar.getInstance();
    this.endTime.setTimeInMillis(this.startTime.getTimeInMillis() + interval * 60000);
    this.location = null;
  }

  public String getName() {
    return name;
  }

  public WeekViewEvent toEvent(){
    WeekViewEvent event = new WeekViewEvent();
    event.setName(this.getName());
    event.setLocation(this.location.toString());
    event.setStartTime(this.startTime);
    event.setEndTime(this.endTime);
    return event;
  }

  public void addToEventsList(){
    WeekViewEvent event = this.toEvent();
    MainActivity.addEvent(event);
  }
}
