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

import ro.changeneers.apprentice.interfaces.CallbackDB;
import ro.changeneers.apprentice.models.Quest;
import ro.changeneers.apprentice.networking.DatabaseFunctions;

/**
 * Created by vlad__000 on 25-Jul-18.
 */
public class Utils {
    private Context mContext = null;

    public Utils(Context con) {
        mContext = con;
    }
    private ProgressDialog progressDialog;

    public boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager

                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }

    //returneaza lista de questuri easy din db
    //are nevoie progress barul de context
    public List<Quest> getListQuestEasyDataBase(final Context context) {
        DatabaseFunctions handler;
        Quest quest;
        final DatabaseReference ref;
        final List<Quest> questList = null;

        handler = new DatabaseFunctions();

        //start progress
        startProgress(context);
        handler.getEasyQuests(new CallbackDB() {

                                  @Override
                                  public void onSuccess(@NonNull ArrayList<Quest> quests) {
                                      dismissProgressDialog();
                                      for (Quest aux : quests) {
                                          Log.d("getListQuestDataBase", "onSuccess: " + aux.toString());
                                          try {
                                              questList.add(aux);
                                          }catch (NullPointerException e){
                                              Log.d("getListQuestDataBase", "err: " + e.getMessage());
                                          }
                                      }
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
        return questList;
    }

    public void startProgress(Context context){
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
        }
        progressDialog.setMessage("your message");
        progressDialog.show();
    }

    public void dismissProgressDialog(){
        if (progressDialog != null ) {
            progressDialog.dismiss();
        }
    }

}
