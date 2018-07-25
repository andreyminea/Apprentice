package ro.changeneers.apprentice.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by vlad__000 on 25-Jul-18.
 */
public class Utils {
    private Context mContext = null;

    public Utils(Context con) {
        mContext = con;
    }

    public boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager

                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }

}
