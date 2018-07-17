package ro.changeneers.apprentice.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

<<<<<<< HEAD:app/src/main/java/ro/changeneers/apprentice/ChatActivity.java
public class ChatActivity extends NavDrawer {
=======
import ro.changeneers.apprentice.R;

public class ChatActivity extends AppCompatActivity {
>>>>>>> origin/bottomnavigation:app/src/main/java/ro/changeneers/apprentice/Activities/ChatActivity.java

    private static final String TAG = "ChatActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }
}
