package ro.changeneers.apprentice.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ro.changeneers.apprentice.models.Quest;

public class SharedPrefManager {

    private SharedPreferences sharedPreferences;
    private Context mContext;
    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "sesionPref";
    private SharedPreferences.Editor editor;

    private static final int EASY = 1;
    private static final int MEDIUM = 2;
    private static final int HARD = 3;

    public SharedPrefManager(Context context) {
        mContext = context;
        sharedPreferences = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void saveIsLoggedIn(Context context, boolean isLoggedIn) {
        mContext = context;
        sharedPreferences = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("IS_LOGGED_IN", isLoggedIn);
        editor.apply();
    }

    public boolean getISLogged_IN() {
        //mContext = context;
        sharedPreferences = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        return sharedPreferences.getBoolean("IS_LOGGED_IN", false);
    }

    public void saveToken(Context context, String toke) {
        mContext = context;
        sharedPreferences = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ID_TOKEN", toke);
        editor.apply();
    }

    public String getUserToken() {
        //mContext = context;
        sharedPreferences = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        return sharedPreferences.getString("ID_TOKEN", "");
    }

    public void saveEmail(Context context, String email) {
        mContext = context;
        sharedPreferences = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("EMAIL", email);
        editor.apply();
    }

    public String getUserEmail() {
        sharedPreferences = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        return sharedPreferences.getString("EMAIL", null);
    }


    public void saveName(Context context, String name) {
        mContext = context;
        sharedPreferences = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("NAME", name);
        editor.apply();
    }

    public String getName() {
        sharedPreferences = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        return sharedPreferences.getString("NAME", null);
    }

    public void savePhoto(Context context, String photo) {
        mContext = context;
        sharedPreferences = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("PHOTO", photo);
        editor.apply();
    }

    public String getPhoto() {
        sharedPreferences = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        return sharedPreferences.getString("PHOTO", null);
    }

    public void clear() {
        editor.clear();
        editor.apply();
    }

    public void saveQuestListInSharedPrefs(Context context, List<Quest> quests, int difficulty) {

        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        Gson gson = new Gson();

        switch (difficulty) {
            case EASY:
                String jsonEasy = gson.toJson(quests);
                editor.putString("QuestListEasy", jsonEasy);
                editor.commit();
                break;
            case MEDIUM:
                String jsonMedium = gson.toJson(quests);
                editor.putString("QuestListMedium", jsonMedium);
                editor.commit();
                break;
            case HARD:
                String jsonHard = gson.toJson(quests);
                editor.putString("QuestListHard", jsonHard);
                editor.commit();
                break;
        }
    }
        public List<Quest> loadQuestListFromSharedPrefs(Context context, int difficulty) {

            sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
            Gson gson = new Gson();
            Type type = new TypeToken<List<Quest>>(){}.getType();
            List<Quest> list = new ArrayList<>();
            switch (difficulty) {
                case EASY:
                    String jsonEasy = sharedPreferences.getString("QuestListEasy", "");

                    List<Quest> questsEasy = gson.fromJson(jsonEasy, type);
                    return questsEasy;
                case MEDIUM:
                    String jsonMedium = sharedPreferences.getString("QuestListMedium", "");
                    List<Quest> questsMedium = gson.fromJson(jsonMedium, type);
                    return questsMedium;
                case HARD:
                    String jsonHard = sharedPreferences.getString("QuestListHard", "");
                    List<Quest> questsHard = gson.fromJson(jsonHard, type);
                    return questsHard;

            }
            return list;
        }
}
