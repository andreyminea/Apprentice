package ro.changeneers.apprentice.interfaces;

import android.support.annotation.NonNull;
import com.google.firebase.database.DatabaseError;
import java.util.ArrayList;

import ro.changeneers.apprentice.models.MQuest;

/**
 * asta este interfata de callback pentru a obtine toata lista de quest-uri (easy/medium/hard)
 */
public interface CallbackDB {

    void onSuccess(@NonNull ArrayList<MQuest> quests);

    void onCancelled(@NonNull DatabaseError var1);

}