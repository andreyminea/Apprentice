package ro.changeneers.apprentice.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ro.changeneers.apprentice.models.Quest;

import static ro.changeneers.apprentice.utils.Constants.EASY;
import static ro.changeneers.apprentice.utils.Constants.HARD;
import static ro.changeneers.apprentice.utils.Constants.MEDIUM;

public class SharedPrefManager {

    private SharedPreferences sharedPreferences;
    private Context mContext;
    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "sesionPref";


    public static SharedPrefManager sharedPrefManager;

    public static SharedPrefManager getInstance() {

        return sharedPrefManager;
    }

    public static void initialize(Context context) {

        sharedPrefManager = new SharedPrefManager(context);
    }


    public SharedPrefManager(Context context) {
        mContext = context;
        sharedPreferences = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
    }

    public void saveIsLoggedIn(boolean isLoggedIn) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("IS_LOGGED_IN", isLoggedIn);
        editor.apply();
    }

    public boolean getISLogged_IN() {
        return sharedPreferences.getBoolean("IS_LOGGED_IN", false);
    }

    public void saveToken(String toke) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ID_TOKEN", toke);
        editor.apply();
    }

    public String getUserToken() {
        return sharedPreferences.getString("ID_TOKEN", "");
    }

    public void saveEmail(String email) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("EMAIL", email);
        editor.apply();
    }

    public String getUserEmail() {
        return sharedPreferences.getString("EMAIL", null);
    }


    public void saveName(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("NAME", name);
        editor.apply();
    }

    public String getName() {
        return sharedPreferences.getString("NAME", null);
    }

    public void savePhoto(String photo) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("PHOTO", photo);
        editor.apply();
    }

    public String getPhoto() {
        return sharedPreferences.getString("PHOTO", null);
    }

    public void clear() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void saveQuestListInSharedPrefs(List<Quest> quests, int difficulty) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
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

    public List<Quest> loadQuestListFromSharedPrefs(int difficulty) {

        Gson gson = new Gson();
        Type type = new TypeToken<List<Quest>>() {
        }.getType();
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

    public void updateStarsInSharedPrefs(int starsObtained){

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("STARS",getStarsFromSharedPrefs() + starsObtained);
        editor.apply();

    }

    public int getStarsFromSharedPrefs(){

        return sharedPreferences.getInt("STARS",0);
    }
}
