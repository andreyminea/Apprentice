package ro.changeneers.apprentice;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import ro.changeneers.apprentice.models.Curs;
import ro.changeneers.apprentice.models.Quest;

public class ApprenticeApplication extends Application
{

    public static ApprenticeApplication applicationInstance;

    public static ApprenticeApplication getInstance(){
        if (applicationInstance == null){

            applicationInstance = new ApprenticeApplication();
        }
        return applicationInstance;
    }

    private static List<Quest> questListEasyDB = new ArrayList<>();
    private static List<Quest> questListMediumDB = new ArrayList<>();
    private static List<Quest> questListHardDB = new ArrayList<>();

    private static final int EASY = 1;
    private static final int MEDIUM = 2;
    private static final int HARD = 3;

    @Override
    public void onCreate()
    {
        super.onCreate();
        if(!FirebaseApp.getApps(this).isEmpty())
        {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        }

    }


    public static void setQuestListDB(List<Quest> qList, int difficulty ){
        switch (difficulty){
            case EASY:
                questListEasyDB.clear();
                questListEasyDB.addAll(qList);
                break;
            case MEDIUM:
                questListMediumDB.clear();
                questListMediumDB.addAll(qList);
                break;
            case HARD:
                questListHardDB.clear();
                questListHardDB.addAll(qList);
                break;
        }
    }

    public static List<Quest> getQuestListEasyDB() {
        return questListEasyDB;
    }

    public static List<Quest> getQuestListMediumDB() {
        return questListMediumDB;
    }

    public static List<Quest> getQuestListHardDB() {
        return questListHardDB;
    }
}
