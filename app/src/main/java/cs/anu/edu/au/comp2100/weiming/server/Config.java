package cs.anu.edu.au.comp2100.weiming.server;

import java.io.*;
import java.util.Properties;

public class Config {

  static Properties properties;
  static int cacheTime = 0;
  static String ProgramCourseURL = "https://programsandcourses.anu.edu.au";
  static String WattleURL = "https://wattlecourses.anu.edu.au";
  static String IsisURL = "https://isis.anu.edu.au";

  public Config() throws Exception {
    try {
      File file = new File("config.properties");
      Reader reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
      properties = new Properties();
      properties.load(reader);

      cacheTime = Integer.parseInt(properties.getProperty("CacheTime"));
      ProgramCourseURL = properties.getProperty("ProgramCourseURL");
      WattleURL = properties.getProperty("WattleURL");
      IsisURL = properties.getProperty("IsisURL");

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
