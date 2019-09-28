package cs.anu.edu.au.comp2100.weiming;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DefaultSettingFileHelper {

    public static final String FILENAME = "defaultSetting.dat";

    public static void writeData(ArrayList<String> settings, Context context){
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(settings);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<String> readData(Context context){
        ArrayList<String> settings = null;
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            settings = (ArrayList<String>) ois.readObject();
        } catch (FileNotFoundException e) {

            settings = new ArrayList<>();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return settings;
    }

    public static int getBackgroundColor(ArrayList<String> settings){
        return Integer.parseInt(settings.get(0));
    }

    public static boolean getShowLine(ArrayList<String> settings){
        return Boolean.parseBoolean(settings.get(1));
    }

    public static int getLineColor(ArrayList<String> settings) {
        return Integer.parseInt(settings.get(2));
    }

    public static boolean getAMPMMode(ArrayList<String> settings){
        return Boolean.parseBoolean(settings.get(3));
    }

    public static int getTimetableView(ArrayList<String> settings) {
        return Integer.parseInt(settings.get(4));
    }

    public static int getStartHour(ArrayList<String> settings) {
        return Integer.parseInt(settings.get(5));
    }

    public static int getStartOfTheWeek(ArrayList<String> settings) {
        return Integer.parseInt(settings.get(6));
    }

    public static int getDefaultDuration(ArrayList<String> settings) {
        return Integer.parseInt(settings.get(7));
    }


}
