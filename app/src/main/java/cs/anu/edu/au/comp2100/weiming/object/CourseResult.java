package cs.anu.edu.au.comp2100.weiming.object;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CourseResult {

  @SerializedName("TotalCount")
  @Expose
  private Integer count;

  @SerializedName("Items")
  @Expose
  private List<Course> courseList = null;

  @SerializedName("Suggestion")
  @Expose
  private Object suggestion;

  public Integer getTotalCount() {
    return count;
  }

  public void setTotalCount(Integer count) {
    this.count = count;
  }

  public List<Course> getCourseList() {
    return courseList;
  }

  public void setCourseList(List<Course> courseList) {
    this.courseList = courseList;
  }

  public Object getSuggestion() {
    return suggestion;
  }

  public void setSuggestion(Object suggestion) {
    this.suggestion = suggestion;
  }
}
