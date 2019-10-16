package cs.anu.edu.au.comp2100.weiming.server.ProgramCourse;

import cs.anu.edu.au.comp2100.weiming.object.CourseResult;
import cs.anu.edu.au.comp2100.weiming.object.SubjectResult;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProgramCourseService {

  @GET("/data/SpecialisationSearch/GetSpecialisations?ShowAll=true")
  Call<List<CourseResult>> getCourseList();

  @GET("/data/SpecialisationSearch/GetSpecialisations?ShowAll=true")
  Call<List<CourseResult>> getCourseList(@Query("SearchText") String text);

  @GET("/data/SpecialisationSearch/GetSpecialisations?ShowAll=true")
  Call<List<CourseResult>> getCourseListByTextYear(
      @Query("SearchText") String text, @Query("SelectedYear") String year);

  @GET("/data/SpecialisationSearch/GetSpecialisations?ShowAll=true")
  Call<List<CourseResult>> getCourseListByTextCareer(
      @Query("SearchText") String text,
      @Query("Careers%5B0%5D") String career);

  @GET("/data/SpecialisationSearch/GetSpecialisations?ShowAll=true")
  Call<List<CourseResult>> getCourseListByTextCollege(
      @Query("SearchText") String text,
      @Query("CollegeName") String collegeCode);

  @GET("/data/SpecialisationSearch/GetSpecialisations?ShowAll=true")
  Call<List<CourseResult>> getCourseListByTextYearCareer(
      @Query("SearchText") String text,
      @Query("SelectedYear") String year,
      @Query("Careers%5B0%5D") String career);

  @GET("/data/SpecialisationSearch/GetSpecialisations?ShowAll=true")
  Call<List<CourseResult>> getCourseListByTextYearCollege(
      @Query("SearchText") String text,
      @Query("SelectedYear") String year,
      @Query("CollegeName") String collegeCode);

  @GET("/data/SpecialisationSearch/GetSpecialisations?ShowAll=true")
  Call<List<CourseResult>> getCourseListByTextYearCareerCollege(
      @Query("SearchText") String text,
      @Query("SelectedYear") String year,
      @Query("Careers%5B0%5D") String careers,
      @Query("CollegeName") String collegeCode);

  @GET("/data/SpecialisationSearch/GetSpecialisations?ShowAll=true")
  Call<List<CourseResult>> getCourseListByTextYearCareerCollegeMOD(
      @Query("SearchText") String text,
      @Query("SelectedYear") String year,
      @Query("Careers%5B0%5D") String careers,
      @Query("CollegeName") String collegeCode,
      @Query("ModeOfDelivery") String modeOfDelivery);

  @GET("/data/SpecialisationSearch/GetSpecialisations?ShowAll=true")
  Call<List<SubjectResult>> getSubjectList();

  @GET("/data/SpecialisationSearch/GetSpecialisations?ShowAll=true")
  Call<List<SubjectResult>> getSubjectList(@Query("SearchText") String text);

  @GET("/data/SpecialisationSearch/GetSpecialisations?ShowAll=true")
  Call<List<SubjectResult>> getSubjectListByTextYear(
      @Query("SearchText") String text, @Query("SelectedYear") String year);

  @GET("/data/SpecialisationSearch/GetSpecialisations?ShowAll=true")
  Call<List<SubjectResult>> getSubjectListByTextCareer(
      @Query("SearchText") String text,
      @Query("Careers%5B0%5D") String career);

  @GET("/data/SpecialisationSearch/GetSpecialisations?ShowAll=true")
  Call<List<SubjectResult>> getSubjectListByTextCollege(
      @Query("SearchText") String text,
      @Query("CollegeName") String collegeCode);

  @GET("/data/SpecialisationSearch/GetSpecialisations?ShowAll=true")
  Call<List<SubjectResult>> getSubjectListByTextYearCareer(
      @Query("SearchText") String text,
      @Query("SelectedYear") String year,
      @Query("Careers%5B0%5D") String career);

  @GET("/data/SpecialisationSearch/GetSpecialisations?ShowAll=true")
  Call<List<SubjectResult>> getSubjectListByTextYearCollege(
      @Query("SearchText") String text,
      @Query("SelectedYear") String year,
      @Query("CollegeName") String collegeCode);

  @GET("/data/SpecialisationSearch/GetSpecialisations?ShowAll=true")
  Call<List<SubjectResult>> getSubjectListByTextYearCareerCollege(
      @Query("SearchText") String text,
      @Query("SelectedYear") String year,
      @Query("Careers%5B0%5D") String careers,
      @Query("CollegeName") String collegeCode);

  @GET("/data/SpecialisationSearch/GetSpecialisations?ShowAll=true")
  Call<List<SubjectResult>> getSubjectListByTextYearCareerCollegeMOD(
      @Query("SearchText") String text,
      @Query("SelectedYear") String year,
      @Query("Careers%5B0%5D") String careers,
      @Query("CollegeName") String collegeCode,
      @Query("ModeOfDelivery") String modeOfDelivery);
}
