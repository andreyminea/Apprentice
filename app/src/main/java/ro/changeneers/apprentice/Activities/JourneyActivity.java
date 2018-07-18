package ro.changeneers.apprentice.Activities;

import android.os.Bundle;

import ro.changeneers.apprentice.R;

public class JourneyActivity extends NavDrawer {

    private static final String TAG = "JourneyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey);
    }
}
