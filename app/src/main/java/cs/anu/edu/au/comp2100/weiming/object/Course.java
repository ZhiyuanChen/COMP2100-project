package cs.anu.edu.au.comp2100.weiming.object;

import java.util.List;

public class Course {
  private String code;
  private String name;
  private Career career;
  private Session session;
  private int unit;
  private int year;
  private String description;
  private List<Course> prerequisiteList;

  public Course(String code, String name, Career career, Session session, int unit, int year) {
    this.code = code;
    this.name = name;
    this.career = career;
    this.session = session;
    this.unit = unit;
    this.year = year;
  }

  public Course(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public Course() {}

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Career getCareer() {
    return career;
  }

  public void setCareer(Career career) {
    this.career = career;
  }

  public Session getSession() {
    return session;
  }

  public void setSession(Session session) {
    this.session = session;
  }

  public int getUnit() {
    return unit;
  }

  public void setUnit(int unit) {
    this.unit = unit;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Course> getPrerequisiteList() {
    return prerequisiteList;
  }

  public void setPrerequisiteList(List<Course> prerequisiteList) {
    this.prerequisiteList = prerequisiteList;
  }

  public void update(String description, List<Course> prerequisiteList) {
    setDescription(description);
    setPrerequisiteList(prerequisiteList);
  }
}

enum Session {
  F("First Semester", 0),
  S("Second Semester", 1),
  A("Spring", 2),
  B("Summer", 3),
  C("Autumn", 4),
  D("Winter", 5);

  private String name;
  private int index;

  Session(String name, int index) {
    this.name = name;
    this.index = index;
  }

  public String getName(int index) {
    for (Session session : Session.values()) {
      if (session.getIndex() == index) {
        return session.name;
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
