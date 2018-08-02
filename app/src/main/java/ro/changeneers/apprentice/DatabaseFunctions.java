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

    public void getEasyQuests(@NonNull final CallbackDB callbackDB)
    {
        quests.child("Programare/Easy").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                Quest quest= null;
                String sKey;
                ArrayList<Quest> listResult = new ArrayList<>();

                Iterator i = dataSnapshot.getChildren().iterator();

                while(i.hasNext())
                {
                    sKey = ((DataSnapshot)i.next()).getKey();
                    quest = dataSnapshot.child(sKey).getValue(Quest.class);
                    listResult.add(quest);
                }

                callbackDB.onSuccess(listResult);

               Log.d("DatabaseFunctions", "onDataChange: "+dataSnapshot.toString());

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
                Quest quest= null;
                String sKey;
                ArrayList<Quest> listResult = new ArrayList<>();

                Iterator i = dataSnapshot.getChildren().iterator();

                while(i.hasNext())
                {
                    sKey = ((DataSnapshot)i.next()).getKey();
                    quest = dataSnapshot.child(sKey).getValue(Quest.class);
                    listResult.add(quest);
                }

                callbackDB.onSuccess(listResult);

                Log.d("DatabaseFunctions", "onDataChange: "+dataSnapshot.toString());

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
                Quest quest= null;
                String sKey;
                ArrayList<Quest> listResult = new ArrayList<>();

                Iterator i = dataSnapshot.getChildren().iterator();

                while(i.hasNext())
                {
                    sKey = ((DataSnapshot)i.next()).getKey();
                    quest = dataSnapshot.child(sKey).getValue(Quest.class);
                    listResult.add(quest);
                }

                callbackDB.onSuccess(listResult);

                Log.d("DatabaseFunctions", "onDataChange: "+dataSnapshot.toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                callbackDB.onCancelled(databaseError);
            }
        });
    }



}
