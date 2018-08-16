package ro.changeneers.apprentice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;

import ro.changeneers.apprentice.R;
import ro.changeneers.apprentice.interfaces.CallbackDB;
import ro.changeneers.apprentice.models.MQuest;
import ro.changeneers.apprentice.utils.Utils;

import static ro.changeneers.apprentice.utils.Constants.DIFFICULTY_EXTRA;

public class JourneyActivity extends NavDrawer {

    private static final String TAG = "JourneyActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey);

        LinearLayout easyLayout = findViewById(R.id.LinearLayoutEasyQuests);
        CardView easyCard = findViewById(R.id.CardEasy);
        CardView mediumCard = findViewById(R.id.CardMedium);
        CardView hardCard = findViewById(R.id.CardHard);




        easyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Utils.getInstance(JourneyActivity.this).getListQuestFromDataBase(JourneyActivity.this, 1, new CallbackDB(){

                   @Override
                   public void onSuccess(@NonNull ArrayList<MQuest> quests) {
                       Intent intent = new Intent(JourneyActivity.this, QuestListActivity.class);
                       intent.putExtra(DIFFICULTY_EXTRA,1);
                       startActivity(intent);
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError var1) {

                   }
               });
            }
        });

        mediumCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils.getInstance(JourneyActivity.this).getListQuestFromDataBase(JourneyActivity.this, 2, new CallbackDB() {
                    @Override
                    public void onSuccess(@NonNull ArrayList<MQuest> quests) {
                        Intent intent = new Intent(JourneyActivity.this,QuestListActivity.class);
                        intent.putExtra(DIFFICULTY_EXTRA,2);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError var1) {

                    }
                });
            }
        });

        hardCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils.getInstance(JourneyActivity.this).getListQuestFromDataBase(JourneyActivity.this, 3, new CallbackDB() {
                    @Override
                    public void onSuccess(@NonNull ArrayList<MQuest> quests) {
                        Intent intent = new Intent(JourneyActivity.this,QuestListActivity.class);
                        intent.putExtra(DIFFICULTY_EXTRA,3);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError var1) {

                    }
                });
            }
        });

    }
    @Override
    protected int getNavigationItemID()
    {
        return 0;
    }
}
