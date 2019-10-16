package cs.anu.edu.au.comp2100.weiming.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Course {

  @SerializedName("CourseCode")
  @Expose
  private String code;

  @SerializedName("Name")
  @Expose
  private String name;

  @SerializedName("Session")
  @Expose
  private String session;

  @SerializedName("Career")
  @Expose
  private String career;

  @SerializedName("Units")
  @Expose
  private Double unit;

  @SerializedName("ModeOfDelivery")
  @Expose
  private String modeOfDelivery;

  @SerializedName("Year")
  @Expose
  private Integer year;

  private String description;
  private List<Course> prerequisiteList;

  public Course(String code, String name, String career, String session, double unit, int year) {
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

  public String getCareer() {
    return career;
  }

  public void setCareer(String career) {
    this.career = career;
  }

  public String getSession() {
    return session;
  }

  public void setSession(String session) {
    this.session = session;
  }

  public double getUnit() {
    return unit;
  }

  public void setUnit(double unit) {
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
