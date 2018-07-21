package ro.changeneers.apprentice.activities;
import android.os.Bundle;

import ro.changeneers.apprentice.R;

public class ChatActivity extends NavDrawer {

    private static final String TAG = "ChatActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }

    @Override
    protected int getNavigationItemID()
    {
        return R.id.nav_chat;
    }
}
