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

    public List<Quest> dbQuestList = new ArrayList<>();
    @Override
    public void onCreate()
    {
        super.onCreate();
        if(!FirebaseApp.getApps(this).isEmpty())
        {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        }

        Curs curs  = new Curs(0,"Getting started","https://www.udemy.com/java-tutorial/","se ocupa foarte bine de notiuni de la 0",
                "dureaza 16 ore");

        dbQuestList.add(new Quest(0,"Getting Started","Este important pentru ca e nevoie sa iti pui bazele programrii intainte sa incepi ceva mai complicat",
                "Vei invata ce inseamna notiunile de baza ale programarii in general",0,1,curs,curs,curs));

    }


}
