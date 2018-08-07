package ro.changeneers.apprentice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import ro.changeneers.apprentice.ApprenticeApplication;
import ro.changeneers.apprentice.R;
import ro.changeneers.apprentice.models.Quest;
import ro.changeneers.apprentice.utils.Utils;

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

               Utils.getInstance(JourneyActivity.this).getListQuestFromDataBase(JourneyActivity.this, 1);

                Intent intent = new Intent(JourneyActivity.this,QuestListActivity.class);
                intent.putExtra("DIFFICULTY",1);
                startActivity(intent);
            }
        });

        mediumCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils.getInstance(JourneyActivity.this).getListQuestFromDataBase(JourneyActivity.this, 2);


                Intent intent = new Intent(JourneyActivity.this,QuestListActivity.class);
                intent.putExtra("DIFFICULTY",2);
                startActivity(intent);
            }
        });

        hardCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Utils.getInstance(JourneyActivity.this).getListQuestFromDataBase(JourneyActivity.this, 3);

                Intent intent = new Intent(JourneyActivity.this,QuestListActivity.class);
                intent.putExtra("DIFFICULTY",3);
                startActivity(intent);
            }
        });

    }

    @Override
    protected int getNavigationItemID()
    {
        return 0;
    }
}
