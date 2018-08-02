package ro.changeneers.apprentice.interfaces;

import android.support.annotation.NonNull;

import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

import ro.changeneers.apprentice.Quest;

public interface CallbackDB {

    void onSuccess(@NonNull ArrayList<Quest> quests);

    void onCancelled(@NonNull DatabaseError var1);
}
