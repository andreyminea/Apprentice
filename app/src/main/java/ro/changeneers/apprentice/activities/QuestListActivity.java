package ro.changeneers.apprentice.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ro.changeneers.apprentice.R;
import ro.changeneers.apprentice.adapters.QuestListAdapter;
import ro.changeneers.apprentice.models.Quest;

public class QuestListActivity extends AppCompatActivity  {

    private static final String TAG = "QuestListActivity";

    private static final int EASY = 1;
    private static final int MEDIUM = 2;
    private static final int HARD = 3;


    private RecyclerView.LayoutManager layoutManager;
    private TextView DifficultyTitleTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_list);

        Intent receivingIntent = getIntent();
        int difficulty = receivingIntent.getIntExtra("DIFFICULTY",0);


        List<Quest> quests = new ArrayList<>();
        DifficultyTitleTV = findViewById(R.id.TextViewQuestListTitle);

        switch(difficulty){
            case EASY:
                DifficultyTitleTV.setText("Easy");
                quests.clear();
                quests.add(new Quest(0,"Getting Started", 0));
                quests.add(new Quest(1,"Java Basics",1));
                quests.add(new Quest(2,"Java Basics",2));
                quests.add(new Quest(3,"Java Basics",4));
                quests.add(new Quest(4,"Java Basics",5));
                break;
            case MEDIUM:
                DifficultyTitleTV.setText("Medium");
                quests.clear();
                quests.add(new Quest(0,"Java Basics",7));
                quests.add(new Quest(1,"Java Basics",8));
                quests.add(new Quest(2,"Java Basics",9));
                quests.add(new Quest(3,"Java Basics",11));
                quests.add(new Quest(4,"Java Basics",13));
                break;
            case HARD:
                DifficultyTitleTV.setText("Hard");
                quests.clear();
                quests.add(new Quest(0,"Java Basics",14));
                quests.add(new Quest(1,"Java Basics",16));
                quests.add(new Quest(2,"Java Basics",17));
                quests.add(new Quest(3,"Java Basics",18));
                quests.add(new Quest(4,"Java Basics",20));

        }


        RecyclerView recyclerView = findViewById(R.id.RecyclerViewQuestList);
        recyclerView.setHasFixedSize(true);

        QuestListAdapter questListAdapter = new QuestListAdapter(quests, this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(questListAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }
}
