package cs.anu.edu.au.comp2100.weiming.object;

import android.location.Location;

import java.util.Calendar;

public class Schedule {
  private Calendar start_time;
  private Calendar end_time;
  private Location location;
  private String description;

  public Schedule(
      Calendar start_time,
      Calendar end_time,
      Location location,
      String description) {
    this.start_time = start_time;
    this.end_time = end_time;
    this.location = location;
    this.description = description;
  }

  public Schedule(
          Calendar start_time,
          Calendar end_time,
          String description) {
    this.start_time = start_time;
    this.end_time = end_time;
    this.location = null;
    this.description = description;
  }

  public Schedule(
          Calendar start_time,
          int interval,
          Location location,
          String description) {
    this.start_time = start_time;
    this.end_time = Calendar.getInstance();
    this.end_time.setTimeInMillis(this.start_time.getTimeInMillis() + interval * 60000);
    this.location = location;
    this.description = description;
  }

  public Schedule(
          Calendar start_time,
          int interval,
          String description) {
    this.start_time = start_time;
    this.end_time = Calendar.getInstance();
    this.end_time.setTimeInMillis(this.start_time.getTimeInMillis() + interval * 60000);
    this.location = null;
    this.description = description;
  }
}
