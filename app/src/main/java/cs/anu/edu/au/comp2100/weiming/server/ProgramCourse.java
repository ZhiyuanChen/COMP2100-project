package cs.anu.edu.au.comp2100.weiming.server;

import cs.anu.edu.au.comp2100.weiming.object.*;

import java.util.List;
import java.util.Map;

public interface ProgramCourse {

  List<Program> getDegreeList(String query);

  List<Program> getDegreeList(String query, Career career);

  List<Program> getDegreeList(String query, int year);

  List<Program> getDegreeList(String query, Career career, int year);

  List<Specialisation> getSpecificationList(String query);

  List<Specialisation> getSpecificationList(String query, Career career);

  List<Specialisation> getSpecificationList(String query, int year);

  List<Specialisation> getSpecificationList(String query, Career career, int year);

  List<Minor> getMinorList(String query);

  List<Minor> getMinorList(String query, Career career);

  List<Minor> getMinorList(String query, int year);

  List<Minor> getMinorList(String query, Career career, int year);

  List<Major> getMajorList(String query);

  List<Major> getMajorList(String query, Career career);

  List<Major> getMajorList(String query, int year);

  List<Major> getMajorList(String query, Career career, int year);

  Map<String, List> getRequirementMap(Program program);

  Map<String, List> getRequirementMap(Specialisation specialisation);

  Map<String, List> getRequirementMap(Minor minor);

  Map<String, List> getRequirementMap(Major major);
}
