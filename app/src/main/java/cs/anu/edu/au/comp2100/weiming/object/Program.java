package cs.anu.edu.au.comp2100.weiming.object;

import java.util.List;
import java.util.Map;

public class Program {
  private String code;
  private String name;
  private String short_name;
  private Career career;
  private int year;
  private int duration;
  private String description;
  private List<Specialisation> specialization_list;
  private List<Minor> minor_list;
  private List<Major> major_list;
  private Map<String, List> requirement_map;

  public Program(
      String code, String name, String short_name, Career career, int year, int duration) {
    this.code = code;
    this.name = name;
    this.short_name = short_name;
    this.career = career;
    this.year = year;
    this.duration = duration;
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

  public String getShortName() {
    return short_name;
  }

  public void setShortName(String short_name) {
    this.short_name = short_name;
  }

  public Career getCareer() {
    return career;
  }

  public void setCareer(Career career) {
    this.career = career;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Specialisation> getSpecializationList() {
    return specialization_list;
  }

  public void setSpecializationList(List<Specialisation> specialization_list) {
    this.specialization_list = specialization_list;
  }

  public List<Minor> getMinorList() {
    return minor_list;
  }

  public void setMinorList(List<Minor> minor_list) {
    this.minor_list = minor_list;
  }

  public List<Major> getMajorList() {
    return major_list;
  }

  public void setMajorList(List<Major> major_list) {
    this.major_list = major_list;
  }

  public Map<String, List> getRequirementMap() {
    return requirement_map;
  }

  public void setRequirementMap(Map<String, List> requirement_map) {
    this.requirement_map = requirement_map;
  }

  public void update(
      String description,
      List<Specialisation> specialization_list,
      List<Minor> minor_list,
      List<Major> major_list,
      Map<String, List> requirement_map) {
    setDescription(description);
    setSpecializationList(specialization_list);
    setMinorList(minor_list);
    setMajorList(major_list);
    setRequirementMap(requirement_map);
  }
}
