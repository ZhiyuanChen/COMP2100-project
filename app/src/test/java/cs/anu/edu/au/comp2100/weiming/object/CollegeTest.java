package cs.anu.edu.au.comp2100.weiming.object;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CollegeTest {
  @Test
  public void CollegeTest() {
    College cecs = new College("CECS", "College of Engineering and Computer Science");
    assertEquals(cecs.getCode(), "CECS");
    assertEquals(cecs.getName(), "College of Engineering and Computer Science");
  }
}

