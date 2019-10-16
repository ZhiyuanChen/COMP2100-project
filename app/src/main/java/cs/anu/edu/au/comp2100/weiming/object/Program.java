package cs.anu.edu.au.comp2100.weiming.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class Program {

  @SerializedName("AcademicPlanCode")
  @Expose
  private String code;

  @SerializedName("ProgramName")
  @Expose
  private String name;

  @SerializedName("ShortProgramName")
  @Expose
  private String shortName;

  @SerializedName("CareerText")
  @Expose
  private String careerText;

  @SerializedName("AcademicCareer")
  @Expose
  private String career;

  @SerializedName("ProgramAcademicYear")
  @Expose
  private String year;

  @SerializedName("DegreeIdentifiers")
  @Expose
  private List<String> degreeIdentifiers = null;

  @SerializedName("Duration")
  @Expose
  private Double duration;

  @SerializedName("Categories")
  @Expose
  private Object categories;

  @SerializedName("AnchorDegree")
  @Expose
  private Object anchorDegree;

  @SerializedName("ModeOfDelivery")
  @Expose

  private String modeOfDelivery;
  @SerializedName("CanCombine")
  @Expose
  private Boolean flexible;

  @SerializedName("CanCombineVertical")
  @Expose
  private Boolean vertical;

  private String description;
  private List<Specialisation> specializationList;
  private List<Minor> minorList;
  private List<Major> majorList;
  private Map<String, List> requirementMap;

  public Program(
      String code, String name, String shortName, String career, int year, double duration) {
    this.code = code;
    this.name = name;
    this.shortName = shortName;
    this.career = career;
    this.year = String.valueOf(year);
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
    return Integer.parseInt(year);
  }

  public void setYear(int year) {
    this.year = String.valueOf(year);
  }

  public double getDuration() {
    return duration;
  }

  public void setDuration(double duration) {
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
