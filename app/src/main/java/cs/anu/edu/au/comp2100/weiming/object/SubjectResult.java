package cs.anu.edu.au.comp2100.weiming.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SubjectResult {

  @SerializedName("TotalCount")
  @Expose
  private Integer count;
  @SerializedName("Items")
  @Expose
  private List<Subject> subjectList = null;
  @SerializedName("Suggestion")
  @Expose
  private Object suggestion;

  public Integer getTotalCount() {
    return count;
  }

  public void setTotalCount(Integer count) {
    this.count = count;
  }

  public List<Subject> getSubjectList() {
    return subjectList;
  }

  public void setSubjectList(List<Subject> subjectList) {
    this.subjectList = subjectList;
  }

  public Object getSuggestion() {
    return suggestion;
  }

  public void setSuggestion(Object suggestion) {
    this.suggestion = suggestion;
  }
}
