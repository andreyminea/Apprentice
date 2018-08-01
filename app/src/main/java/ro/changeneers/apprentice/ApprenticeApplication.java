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

    }


}
