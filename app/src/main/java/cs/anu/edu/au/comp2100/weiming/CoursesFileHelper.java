package cs.anu.edu.au.comp2100.weiming;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


//Hint by github.com/BradTeachesCode/TodoList
public class CoursesFileHelper {

    public static final String COURSE_TAKEN = "courseTakenInfo.dat";
    public static final String COURSE_SELECTED = "courseSelected.dat";

    public static void writeData(ArrayList<String> items, Context context, int mode){
        String fileName = null;
        if(mode == 0){
            fileName = COURSE_TAKEN;
        }
        if(mode == 1){
            fileName = COURSE_SELECTED;
        }
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(items);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static ArrayList<String> readData(Context context, int mode){
        String fileName = null;
        if(mode == 0){
            fileName = COURSE_TAKEN;
        }
        if(mode == 1){
            fileName = COURSE_SELECTED;
        }
        ArrayList<String> itemsList = null;
        try {
            FileInputStream fis = context.openFileInput(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            itemsList = (ArrayList<String>) ois.readObject();
        } catch (FileNotFoundException e) {
            itemsList = new ArrayList<>();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return itemsList;

    }
}