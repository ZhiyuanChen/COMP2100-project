package cs.anu.edu.au.comp2100.weiming.server.ProgramCourse;

import cs.anu.edu.au.comp2100.weiming.server.HttpClient;

public class ProgramCourseApi {
  public static ProgramCourseService getProgramCourseService() {
    return HttpClient.getProgramCourseClient().create(ProgramCourseService.class);
  }
}
