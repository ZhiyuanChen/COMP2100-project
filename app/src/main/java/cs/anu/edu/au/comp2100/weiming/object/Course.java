package cs.anu.edu.au.comp2100.weiming.object;

import java.util.List;

public class Course {
  private String code;
  private String name;
  private String description;
  private List<Course> prerequisite_list;

  public Course(String code, String name, String description, List<Course> prerequisite_list) {
    this.code = code;
    this.name = name;
    this.description = description;
    this.prerequisite_list = prerequisite_list;
  }

  public Course(String code, String name, String description) {
    this.code = code;
    this.name = name;
    this.description = description;
    this.prerequisite_list = null;
  }
}
