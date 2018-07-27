package ro.changeneers.apprentice;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import ro.changeneers.apprentice.models.Quest;

public class ApprenticeApplication extends Application
{

    public List<Quest> dbQuestList = new ArrayList<>();
    @Override
    public void onCreate()
    {
        super.onCreate();
        if(!FirebaseApp.getApps(this).isEmpty())
        {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        }

        dbQuestList.add(new Quest(0,"Getting Started","Este important pentru ca e nevoie sa iti pui bazele programrii intainte sa incepi ceva mai complicat",
                "Vei invata ce inseamna notiunile de baza ale programarii in general",0,1, "Curs 1","https://www.udemy.com/java-tutorial/","se ocupa foarte bine de notiuni de la 0",
                "dureaza 16 ore","Curs 2", "https://www.udemy.com/java-tutorial/","se ocupa foarte bine de notiuni de la 0",
                "dureaza 16 ore","Curs 3","https://www.udemy.com/java-tutorial/","se ocupa foarte bine de notiuni de la 0",
                "dureaza 16 ore"));
        dbQuestList.add(new Quest(1,"Getting Started","Este important pentru ca e nevoie sa iti pui bazele programrii intainte sa incepi ceva mai complicat",
                "Vei invata ce inseamna notiunile de baza ale programarii in general",1,1, "Curs 1","https://www.udemy.com/java-tutorial/","se ocupa foarte bine de notiuni de la 0",
                "dureaza 16 ore","Curs 2", "https://www.udemy.com/java-tutorial/","se ocupa foarte bine de notiuni de la 0",
                "dureaza 16 ore","Curs 3","https://www.udemy.com/java-tutorial/","se ocupa foarte bine de notiuni de la 0",
                "dureaza 16 ore"));
        dbQuestList.add(new Quest(2,"Getting Started","Este important pentru ca e nevoie sa iti pui bazele programrii intainte sa incepi ceva mai complicat",
                "Vei invata ce inseamna notiunile de baza ale programarii in general",2,1, "Curs 1","https://www.udemy.com/java-tutorial/","se ocupa foarte bine de notiuni de la 0",
                "dureaza 16 ore","Curs 2", "https://www.udemy.com/java-tutorial/","se ocupa foarte bine de notiuni de la 0",
                "dureaza 16 ore","Curs 3","https://www.udemy.com/java-tutorial/","se ocupa foarte bine de notiuni de la 0",
                "dureaza 16 ore"));
        dbQuestList.add(new Quest(3,"Getting Started","Este important pentru ca e nevoie sa iti pui bazele programrii intainte sa incepi ceva mai complicat",
                "Vei invata ce inseamna notiunile de baza ale programarii in general",4,1, "Curs 1","https://www.udemy.com/java-tutorial/","se ocupa foarte bine de notiuni de la 0",
                "dureaza 16 ore","Curs 2", "https://www.udemy.com/java-tutorial/","se ocupa foarte bine de notiuni de la 0",
                "dureaza 16 ore","Curs 3","https://www.udemy.com/java-tutorial/","se ocupa foarte bine de notiuni de la 0",
                "dureaza 16 ore"));
        dbQuestList.add(new Quest(4,"Getting Started","Este important pentru ca e nevoie sa iti pui bazele programrii intainte sa incepi ceva mai complicat",
                "Vei invata ce inseamna notiunile de baza ale programarii in general",5,1, "Curs 1","https://www.udemy.com/java-tutorial/","se ocupa foarte bine de notiuni de la 0",
                "dureaza 16 ore","Curs 2", "https://www.udemy.com/java-tutorial/","se ocupa foarte bine de notiuni de la 0",
                "dureaza 16 ore","Curs 3","https://www.udemy.com/java-tutorial/","se ocupa foarte bine de notiuni de la 0",
                "dureaza 16 ore"));
    }


}
