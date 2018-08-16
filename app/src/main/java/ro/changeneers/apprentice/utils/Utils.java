package ro.changeneers.apprentice.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import ro.changeneers.apprentice.ApprenticeApplication;
import ro.changeneers.apprentice.interfaces.CallbackDB;
import ro.changeneers.apprentice.models.MQuest;
import ro.changeneers.apprentice.networking.DatabaseFunctions;

import static ro.changeneers.apprentice.utils.Constants.EASY;
import static ro.changeneers.apprentice.utils.Constants.FINISHED;
import static ro.changeneers.apprentice.utils.Constants.HARD;
import static ro.changeneers.apprentice.utils.Constants.IN_PROGRESS;
import static ro.changeneers.apprentice.utils.Constants.MEDIUM;

/**
 * Created by vlad__000 on 25-Jul-18.
 */
public class Utils {

    private static final String TAG = "Utils";

    private Context mContext = null;

    public Utils(Context con) {
        mContext = con;
    }

    private ProgressDialog progressDialog;

    public static Utils utilsInstance;

    public static Utils getInstance(Context context) {
        if (utilsInstance == null) {
            utilsInstance = new Utils(context);
        }
        return utilsInstance;
    }

    public boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager

                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }

    //returneaza lista de questuri easy din db
    //are nevoie progress barul de context
    public void getListQuestFromDataBase(final Context context, final int difficulty, final CallbackDB callback) {
        //ca sa nu facem 3 metode diferite, 1 pentru easy, 2 pt medium, 3 pt hard

        DatabaseFunctions handler;
        MQuest quest;
        final DatabaseReference ref;

        handler = new DatabaseFunctions();

        //start progress
        startProgress(context);
        switch (difficulty) {
            case EASY:
                handler.getEasyQuests(
                        new CallbackDB() {

                            @Override
                            public void onSuccess(@NonNull ArrayList<MQuest> quests) {
                                dismissProgressDialog();
                                List<MQuest> questList = new ArrayList<>();
                                for (MQuest aux : quests) {
                                    Log.d("getListQuestDataBase", "onSuccess: " + aux.toString());
                                    try {
                                        aux.setDifficulty(1);
                                        questList.add(aux);
                                    } catch (NullPointerException e) {
                                        Log.d("getListQuestDataBase", "err: " + e.getMessage());
                                    }
                                }
                                ApprenticeApplication.getInstance();
                                ApprenticeApplication.setQuestListDB(questList, 1, context);
                                callback.onSuccess(quests);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError var1) {
                                //stop progress
                                dismissProgressDialog();
                                //show toast var1.getError / var1.getMessage
                                Toast.makeText(context, var1.getMessage(), Toast.LENGTH_LONG).show();
                                callback.onCancelled(var1);
                            }
                        }
                );
                break;
            case MEDIUM:
                handler.getMediumQuests(
                        new CallbackDB() {

                            @Override
                            public void onSuccess(@NonNull ArrayList<MQuest> quests) {
                                dismissProgressDialog();
                                List<MQuest> questList = new ArrayList<>();
                                for (MQuest aux : quests) {
                                    Log.d("getListQuestDataBase", "onSuccess: " + aux.toString());
                                    try {
                                        aux.setDifficulty(2);
                                        questList.add(aux);
                                    } catch (NullPointerException e) {
                                        Log.d("getListQuestDataBase", "err: " + e.getMessage());
                                    }
                                }
                                ApprenticeApplication.getInstance();
                                ApprenticeApplication.setQuestListDB(questList, 2, context);
                                callback.onSuccess(quests);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError var1) {
                                //stop progress
                                dismissProgressDialog();
                                //show toast var1.getError / var1.getMessage
                                Toast.makeText(context, var1.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                );
                break;
            case HARD:
                handler.getHardQuests(
                        new CallbackDB() {

                            @Override
                            public void onSuccess(@NonNull ArrayList<MQuest> quests) {
                                dismissProgressDialog();
                                List<MQuest> questList = new ArrayList<>();
                                for (MQuest aux : quests) {
                                    Log.d("getListQuestDataBase", "onSuccess: " + aux.toString());
                                    try {
                                        aux.setDifficulty(3);
                                        questList.add(aux);
                                    } catch (NullPointerException e) {
                                        Log.d("getListQuestDataBase", "err: " + e.getMessage());
                                    }
                                }
                                ApprenticeApplication.getInstance();
                                ApprenticeApplication.setQuestListDB(questList, 3, context);
                                callback.onSuccess(quests);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError var1) {
                                //stop progress
                                dismissProgressDialog();
                                //show toast var1.getError / var1.getMessage
                                Toast.makeText(context, var1.getMessage(), Toast.LENGTH_LONG).show();
                                callback.onCancelled(var1);
                            }
                        }
                );
                break;
        }

    }

    public void startProgress(Context context) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
        }
        progressDialog.setMessage("Se descarca lista...");
        progressDialog.show();
    }

    public void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    public static void updateQuestStatus(String id, int difficulty, int newStatus) {

        List<MQuest> list;
        MQuest quest;

        switch (difficulty) {
            case EASY:
                list = ApprenticeApplication.getQuestListEasyDB();
                break;
            case MEDIUM:
                list = ApprenticeApplication.getQuestListMediumDB();
                break;
            case HARD:
                list = ApprenticeApplication.getQuestListHardDB();
                break;
            default:
                list = null;
                break;
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).id.equals(id)) {
                quest = list.get(i);
                quest.setStatus(newStatus);
                break;
            }
        }

        SharedPrefManager.getInstance().saveQuestListInSharedPrefs(list, difficulty);

    }

    public static List<MQuest> getQuestsInProgress() {

        List<MQuest> questsInProgress = new ArrayList<>();

        List<MQuest> sharedPrefsQuests;

        sharedPrefsQuests = SharedPrefManager.getInstance().loadQuestListFromSharedPrefs(EASY);

            for (MQuest aux : sharedPrefsQuests) {
                if (aux.getStatus() == IN_PROGRESS) {
                    questsInProgress.add(aux);
                }
            }



            sharedPrefsQuests.clear();

        sharedPrefsQuests = SharedPrefManager.getInstance().loadQuestListFromSharedPrefs(MEDIUM);


            for (MQuest aux : sharedPrefsQuests) {
                if (aux.getStatus() == IN_PROGRESS) {
                    questsInProgress.add(aux);
                }
            }

        sharedPrefsQuests.clear();
        sharedPrefsQuests = SharedPrefManager.getInstance().loadQuestListFromSharedPrefs(HARD);



            for (MQuest aux : sharedPrefsQuests) {
                if (aux.getStatus() == IN_PROGRESS) {
                    questsInProgress.add(aux);
                }
            }


        return questsInProgress;

    }

    public static List<MQuest> getQuestsDone() {

        List<MQuest> questsFinished = new ArrayList<>();
        List<MQuest> sharedPrefsQuests;

        sharedPrefsQuests = SharedPrefManager.getInstance().loadQuestListFromSharedPrefs(EASY);



            for (MQuest aux : sharedPrefsQuests) {
                if (aux.getStatus() == FINISHED) {
                    questsFinished.add(aux);
                }
            }


        sharedPrefsQuests.clear();
        sharedPrefsQuests = SharedPrefManager.getInstance().loadQuestListFromSharedPrefs(MEDIUM);



            for (MQuest aux : sharedPrefsQuests) {
                if (aux.getStatus() == FINISHED) {
                    questsFinished.add(aux);
                }
            }


        sharedPrefsQuests.clear();
        sharedPrefsQuests = SharedPrefManager.getInstance().loadQuestListFromSharedPrefs(HARD);



            for (MQuest aux : sharedPrefsQuests) {
                if (aux.getStatus() == FINISHED) {
                    questsFinished.add(aux);
                }
            }


        return questsFinished;

    }

}
