package ro.changeneers.apprentice.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ro.changeneers.apprentice.R;

public class ChatActivity extends AppCompatActivity {

    private static final String TAG = "ChatActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }
}
