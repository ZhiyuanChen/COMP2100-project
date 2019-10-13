package cs.anu.edu.au.comp2100.weiming;

import android.content.Context;
import com.alamkanak.weekview.WeekViewEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;


//Hint by github.com/BradTeachesCode/TodoList

public class EventsFileHelper {

    public static final String FILENAME = "iid.json.dat";

    public static ArrayList<String> eventsProperties(ArrayList<WeekViewEvent> events){
        ArrayList<String> properties = new ArrayList<>();
        for(WeekViewEvent event : events){
            String property = event.getName() + "," +
                    event.getLocation() + "," +
                    event.getColor() + "," +
                    event.getStartTime().get(Calendar.YEAR) + "," +
                    event.getStartTime().get(Calendar.MONTH) + "," +
                    event.getStartTime().get(Calendar.DATE) + "," +
                    event.getStartTime().get(Calendar.HOUR_OF_DAY) + "," +
                    event.getStartTime().get(Calendar.MINUTE) + "," +
                    event.getEndTime().get(Calendar.YEAR) + "," +
                    event.getEndTime().get(Calendar.MONTH) + "," +
                    event.getEndTime().get(Calendar.DATE) + "," +
                    event.getEndTime().get(Calendar.HOUR_OF_DAY) + "," +
                    event.getEndTime().get(Calendar.MINUTE);
            properties.add(property);
        }
        return properties;
    }


    public static void writeData(ArrayList<WeekViewEvent> events, Context context){
        try {
            ArrayList<String> eventsProperty = eventsProperties(events);
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(eventsProperty);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static ArrayList<WeekViewEvent> readData(Context context){
        ArrayList<WeekViewEvent> events = new ArrayList<>();
        ArrayList<String> eventsProperties = null;
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            eventsProperties = (ArrayList<String>) ois.readObject();
            for(String eventProperty : eventsProperties){
                WeekViewEvent event = new WeekViewEvent();
                String[] properties = eventProperty.split(",");
                event.setName(properties[0]);
                event.setLocation(properties[1]);
                event.setColor(Integer.parseInt(properties[2]));
                Calendar start = Calendar.getInstance();
                start.set(Integer.parseInt(properties[3]), Integer.parseInt(properties[4]),
                        Integer.parseInt(properties[5]), Integer.parseInt(properties[6]), Integer.parseInt(properties[7]));
                event.setStartTime(start);
                Calendar end = Calendar.getInstance();
                end.set(Integer.parseInt(properties[8]), Integer.parseInt(properties[9]),
                        Integer.parseInt(properties[10]), Integer.parseInt(properties[11]), Integer.parseInt(properties[12]));
                event.setEndTime(end);
                events.add(event);
            }
        } catch (FileNotFoundException e) {
            events = new ArrayList<>();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return events;

    }
}
