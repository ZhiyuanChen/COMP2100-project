package cs.anu.edu.au.comp2100.weiming.object;

import java.util.List;
import java.util.Map;

public class Program {
  private String code;
  private String name;
  private String shortName;
  private String career;
  private int year;
  private int duration;
  private String description;
  private List<Specialisation> specializationList;
  private List<Minor> minorList;
  private List<Major> majorList;
  private Map<String, List> requirementMap;

  public Program(
      String code, String name, String shortName, String career, int year, int duration) {
    this.code = code;
    this.name = name;
    this.shortName = shortName;
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
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public String getCareer() {
    return career;
  }

  public void setCareer(String career) {
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
    return specializationList;
  }

  public void setSpecializationList(List<Specialisation> specializationList) {
    this.specializationList = specializationList;
  }

  public List<Minor> getMinorList() {
    return minorList;
  }

  public void setMinorList(List<Minor> minorList) {
    this.minorList = minorList;
  }

  public List<Major> getMajorList() {
    return majorList;
  }

  public void setMajorList(List<Major> majorList) {
    this.majorList = majorList;
  }

  public Map<String, List> getRequirementMap() {
    return requirementMap;
  }

  public void setRequirementMap(Map<String, List> requirementMap) {
    this.requirementMap = requirementMap;
  }

  public void update(
      String description,
      List<Specialisation> specializationList,
      List<Minor> minorList,
      List<Major> majorList,
      Map<String, List> requirementMap) {
    setDescription(description);
    setSpecializationList(specializationList);
    setMinorList(minorList);
    setMajorList(majorList);
    setRequirementMap(requirementMap);
  }
}
