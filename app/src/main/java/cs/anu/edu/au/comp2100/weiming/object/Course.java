package cs.anu.edu.au.comp2100.weiming.object;

import java.util.List;

public class Course {
  private String code;
  private String name;
  private List<Course> prerequisite_list;

  public Course(String code, String name, List<Course> prerequisite_list) {
    this.code = code;
    this.name = name;
    this.prerequisite_list = prerequisite_list;
  }
}
