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

  public Course(String code, String name){
    this.code = code;
    this.name = name;
  }

  public Course(){}

  public String getCode(){
    return this.code;
  }

  public String getName() {
    return name;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public void setName(String name) {
    this.name = name;
  }
}
