package cs.anu.edu.au.comp2100.weiming.object;
import com.alamkanak.weekview.WeekViewEvent;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class JSONTest {

    @Test
    public void loadFile() throws Exception {

        List<List<WeekViewEvent>> test1 = JSON.loadEvents("COMP2100");
        List<String> name = new ArrayList<>();
        List<String> location= new ArrayList<>();
        List<String> time = new ArrayList<>();
        for(List<WeekViewEvent> list : test1){
            for(WeekViewEvent weekViewEvent : list){
                String newName = weekViewEvent.getName();
                String newLocation = weekViewEvent.getLocation();
                String newTime = "" +weekViewEvent.getStartTime();
                if(!name.contains(newName))
                    name.add(newName);
                if(!location.contains(newLocation))
                    location.add(newLocation);
                if(!time.contains(newTime))
                    time.add(newTime);
            }
        }
        String nameTest = name.get(0);
        assertEquals("COMP2100 Lec_01", nameTest);
    }

}
