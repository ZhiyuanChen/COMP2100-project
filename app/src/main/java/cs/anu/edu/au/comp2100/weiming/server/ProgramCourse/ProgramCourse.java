package cs.anu.edu.au.comp2100.weiming.server.ProgramCourse;

import cs.anu.edu.au.comp2100.weiming.object.*;

import java.util.List;
import java.util.Map;

public interface ProgramCourse {

  List<Program> getDegreeList(String query);

  List<Program> getDegreeList(String query, String career);

  List<Program> getDegreeList(String query, int year);

  List<Program> getDegreeList(String query, String career, int year);

  List<Specialisation> getSpecificationList(String query);

  List<Specialisation> getSpecificationList(String query, String career);

  List<Specialisation> getSpecificationList(String query, int year);

  List<Specialisation> getSpecificationList(String query, String career, int year);

  List<Minor> getMinorList(String query);

  List<Minor> getMinorList(String query, String career);

  List<Minor> getMinorList(String query, int year);

  List<Minor> getMinorList(String query, String career, int year);

  List<Major> getMajorList(String query);

  List<Major> getMajorList(String query, String career);

  List<Major> getMajorList(String query, int year);

  List<Major> getMajorList(String query, String career, int year);

  Map<String, List> getRequirementMap(Program program);

  Map<String, List> getRequirementMap(Specialisation specialisation);

  Map<String, List> getRequirementMap(Minor minor);

  Map<String, List> getRequirementMap(Major major);
}
