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
import ro.changeneers.apprentice.models.Quest;
import ro.changeneers.apprentice.networking.DatabaseFunctions;

/**
 * Created by vlad__000 on 25-Jul-18.
 */
public class Utils {
    private Context mContext = null;

    private static final int EASY = 1;
    private static final int MEDIUM = 2;
    private static final int HARD = 3;

    public Utils(Context con) {
        mContext = con;
    }
    private ProgressDialog progressDialog;

    public static Utils utilsInstance;

    public static Utils getInstance(Context context){
        if(utilsInstance == null){
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
    public void getListQuestFromDataBase(final Context context, final int difficulty) {
        //ca sa nu facem 3 metode diferite, 1 pentru easy, 2 pt medium, 3 pt hard

        DatabaseFunctions handler;
        Quest quest;
        final DatabaseReference ref;

        handler = new DatabaseFunctions();

        //start progress
        startProgress(context);
        switch (difficulty){
            case EASY:
                handler.getEasyQuests(new CallbackDB() {

                                          @Override
                                          public void onSuccess(@NonNull ArrayList<Quest> quests) {
                                              dismissProgressDialog();
                                              List<Quest> questList = new ArrayList<>();
                                              for (Quest aux : quests) {
                                                  Log.d("getListQuestDataBase", "onSuccess: " + aux.toString());
                                                  try {
                                                      questList.add(aux);
                                                  }catch (NullPointerException e){
                                                      Log.d("getListQuestDataBase", "err: " + e.getMessage());
                                                  }
                                              }
                                              ApprenticeApplication.getInstance();
                                              ApprenticeApplication.setQuestListDB(questList,1);

                                          }

                                          @Override
                                          public void onCancelled(@NonNull DatabaseError var1) {
                                              //stop progress
                                              dismissProgressDialog();
                                              //show toast var1.getError / var1.getMessage
                                              Toast.makeText(context, var1.getMessage(),Toast.LENGTH_LONG).show();
                                          }
                                      }
                );
                break;
            case MEDIUM:
                handler.getMediumQuests(new CallbackDB() {

                                          @Override
                                          public void onSuccess(@NonNull ArrayList<Quest> quests) {
                                              dismissProgressDialog();
                                              List<Quest> questList = new ArrayList<>();
                                              for (Quest aux : quests) {
                                                  Log.d("getListQuestDataBase", "onSuccess: " + aux.toString());
                                                  try {
                                                      questList.add(aux);
                                                  }catch (NullPointerException e){
                                                      Log.d("getListQuestDataBase", "err: " + e.getMessage());
                                                  }
                                              }
                                              ApprenticeApplication.getInstance();
                                              ApprenticeApplication.setQuestListDB(questList,2);

                                          }

                                          @Override
                                          public void onCancelled(@NonNull DatabaseError var1) {
                                              //stop progress
                                              dismissProgressDialog();
                                              //show toast var1.getError / var1.getMessage
                                              Toast.makeText(context, var1.getMessage(),Toast.LENGTH_LONG).show();
                                          }
                                      }
                );
                break;
            case HARD:
                handler.getHardQuests(new CallbackDB() {

                                          @Override
                                          public void onSuccess(@NonNull ArrayList<Quest> quests) {
                                              dismissProgressDialog();
                                              List<Quest> questList = new ArrayList<>();
                                              for (Quest aux : quests) {
                                                  Log.d("getListQuestDataBase", "onSuccess: " + aux.toString());
                                                  try {
                                                      questList.add(aux);
                                                  }catch (NullPointerException e){
                                                      Log.d("getListQuestDataBase", "err: " + e.getMessage());
                                                  }
                                              }
                                              ApprenticeApplication.getInstance();
                                              ApprenticeApplication.setQuestListDB(questList,3);

                                          }

                                          @Override
                                          public void onCancelled(@NonNull DatabaseError var1) {
                                              //stop progress
                                              dismissProgressDialog();
                                              //show toast var1.getError / var1.getMessage
                                              Toast.makeText(context, var1.getMessage(),Toast.LENGTH_LONG).show();
                                          }
                                      }
                );
                break;
        }

    }

    public void startProgress(Context context){
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
        }
        progressDialog.setMessage("Se descarca lista...");
        progressDialog.show();
    }

    public void dismissProgressDialog(){
        if (progressDialog != null ) {
            progressDialog.dismiss();
        }
    }

}
