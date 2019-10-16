package cs.anu.edu.au.comp2100.weiming.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProgramResult {

  @SerializedName("TotalCount")
  @Expose
  private Integer count;

  @SerializedName("Items")
  @Expose
  private List<Program> programList = null;

  @SerializedName("Suggestion")
  @Expose
  private Object suggestion;

  public Integer getTotalCount() {
    return count;
  }

  public void setTotalCount(Integer count) {
    this.count = count;
  }

  public List<Program> getProgramList() {
    return programList;
  }

  public void setProgramList(List<Program> programList) {
    this.programList = programList;
  }

  public Object getSuggestion() {
    return suggestion;
  }

  public void setSuggestion(Object suggestion) {
    this.suggestion = suggestion;
  }
}
