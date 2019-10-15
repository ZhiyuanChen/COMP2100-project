package cs.anu.edu.au.comp2100.weiming.object;

public enum Career {
  Undergraduate("Undergraduate", 0),
  Postgraduate("Postgraduate", 1),
  Research("Research", 2),
  NonAward("Drop-in Session", 3);

  private String name;
  private int index;

  Career(String name, int index) {
    this.name = name;
    this.index = index;
  }

  public String getName(int index) {
    for (Career career : Career.values()) {
      if (career.getIndex() == index) {
        return career.name;
      }
    }
    return null;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }
}
