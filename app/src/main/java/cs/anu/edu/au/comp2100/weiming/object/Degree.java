package cs.anu.edu.au.comp2100.weiming.object;

public class Degree {
  private String code;
  private String name;
  private String short_name;
  private int atar;
  private Career career;
  private int year;
  private int duration;

  public Degree(String code, String name, String short_name, int atar, Career career, int year, int duration) {
    this.code = code;
    this.name = name;
    this.short_name = short_name;
    this.atar = atar;
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

  public String getShort_name() {
    return short_name;
  }

  public void setShort_name(String short_name) {
    this.short_name = short_name;
  }

  public int getAtar() {
    return atar;
  }

  public void setAtar(int atar) {
    this.atar = atar;
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
}
