package ro.changeneers.apprentice.networking;

/**
 * Created by vlad__000 on 03-Aug-18.
 */

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

import ro.changeneers.apprentice.interfaces.CallbackDB;
import ro.changeneers.apprentice.models.Curs;
import ro.changeneers.apprentice.models.Quest;

public class DatabaseFunctions
{
    private DatabaseReference root;
    private DatabaseReference quests;
    private DatabaseReference users;
    private DatabaseReference chat;



    public DatabaseFunctions()
    {
        this.root = FirebaseDatabase.getInstance().getReference();
        this.quests = root.child("Quests");
        this.users = root.child("Users");
        this.chat = root.child("ChatROOMS");
    }

    private void getQuests(@NonNull DataSnapshot dataSnapshot, @NonNull CallbackDB callbackDB){
        Quest quest = null;
        String keyQuest;
        String keyCurs;
        Curs curs;
        ArrayList<Quest> listResult = new ArrayList<>();

        Iterator iteratorQuesturi = dataSnapshot.getChildren().iterator();

        while(iteratorQuesturi.hasNext())
        {
            ArrayList<Curs> listaCursuri = new ArrayList<>();
            keyQuest = ((DataSnapshot)iteratorQuesturi.next()).getKey();
            DataSnapshot dataQuest = dataSnapshot.child(keyQuest);
            quest = dataQuest.getValue(Quest.class);
            Iterator iteratorCursuri = dataQuest.child("Cursuri").getChildren().iterator();

            while (iteratorCursuri.hasNext())
            {
                keyCurs =((DataSnapshot)iteratorCursuri.next()).getKey();
                curs = dataQuest.child("Cursuri").child(keyCurs).getValue(Curs.class);
                listaCursuri.add(curs);
            }
            quest.setListCursuri(listaCursuri);

            listResult.add(quest);
        }

        callbackDB.onSuccess(listResult);

        Log.d("DatabaseFunctions", "onDataChange: "+dataSnapshot.toString());
    }

    public void getEasyQuests(@NonNull final CallbackDB callbackDB)
    {
        quests.child("Programare/Easy").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                getQuests(dataSnapshot, callbackDB);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callbackDB.onCancelled(databaseError);
            }
        });
    }

    public void getMediumQuests(@NonNull final CallbackDB callbackDB)
    {
        quests.child("Programare/Medium").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                getQuests(dataSnapshot, callbackDB);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callbackDB.onCancelled(databaseError);
            }
        });
    }

    public void getHardQuests(@NonNull final CallbackDB callbackDB)
    {
        quests.child("Programare/Hard").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                getQuests(dataSnapshot, callbackDB);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callbackDB.onCancelled(databaseError);
            }
        });
    }



}