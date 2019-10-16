package cs.anu.edu.au.comp2100.weiming.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

abstract class Subject {

  @SerializedName("Name")
  @Expose
  private String name;

  @SerializedName("Units")
  @Expose
  private Double unit;

  @SerializedName("SubPlanCode")
  @Expose
  private String code;

  @SerializedName("SubplanType")
  @Expose
  private String type;

  @SerializedName("Year")
  @Expose
  private Integer year;

  @SerializedName("Career")
  @Expose
  private String career;

  private String description;
  private Map<String, List> requirementMap;

  Subject(String code, String name, String career, double unit, int year) {
    this.code = code;
    this.name = name;
    this.career = career;
    this.unit = unit;
    this.year = year;
  }

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

  public Map<String, List> getRequirementMap() {
    return requirementMap;
  }

  public void setRequirementMap(Map<String, List> requirementMap) {
    this.requirementMap = requirementMap;
  }

  public void update(String description, Map<String, List> requirementMap) {
    setDescription(description);
    setRequirementMap(requirementMap);
  }
}
