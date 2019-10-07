package cs.anu.edu.au.comp2100.weiming.object;

public class College {
  private String code;
  private String name;

  public College(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public String getCode() {
    return this.code;
  }

  public String getName() {
    return this.name;
  }
}
