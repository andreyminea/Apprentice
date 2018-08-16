package ro.changeneers.apprentice;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ro.changeneers.apprentice.interfaces.CallbackDB;
import ro.changeneers.apprentice.models.Curs;
import ro.changeneers.apprentice.models.MQuest;

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
        MQuest quest = null;
        String keyQuest;
        String keyCurs;
        ro.changeneers.apprentice.models.Curs curs;
        ArrayList<ro.changeneers.apprentice.models.Curs> listaCursuri = new ArrayList<>();
        ArrayList<MQuest> listResult = new ArrayList<>();

        Iterator iteratorQuesturi = dataSnapshot.getChildren().iterator();

        while(iteratorQuesturi.hasNext())
        {
            keyQuest = ((DataSnapshot)iteratorQuesturi.next()).getKey();
            DataSnapshot dataQuest = dataSnapshot.child(keyQuest);
            quest = dataQuest.getValue(MQuest.class);
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
