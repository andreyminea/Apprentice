package ro.changeneers.apprentice.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_list);

        Intent receivingIntent = getIntent();
        int difficulty = receivingIntent.getIntExtra("DIFFICULTY",0);

        List<Quest> quests = new ArrayList<>();

        switch(difficulty){
            case EASY:
                quests.clear();
                quests.add(new Quest(0,"Getting Started","Nu stiu de ce e imporant.","Nimic"));
                quests.add(new Quest(1,"Getting Started","Nu stiu de ce e imporant.","Nimic"));
                quests.add(new Quest(2,"Getting Started","Nu stiu de ce e imporant.","Nimic"));
                quests.add(new Quest(3,"Getting Started","Nu stiu de ce e imporant.","Nimic"));
                quests.add(new Quest(4,"Getting Started","Nu stiu de ce e imporant.","Nimic"));
                break;
            case MEDIUM:
                quests.clear();
                quests.add(new Quest(0,"MEDIUM","Nu stiu de ce e imporant.","Nimic"));
                quests.add(new Quest(1,"MEDIUM","Nu stiu de ce e imporant.","Nimic"));
                quests.add(new Quest(2,"MEDIUM","Nu stiu de ce e imporant.","Nimic"));
                quests.add(new Quest(3,"MEDIUM","Nu stiu de ce e imporant.","Nimic"));
                quests.add(new Quest(4,"MEDIUM","Nu stiu de ce e imporant.","Nimic"));
                break;
            case HARD:
                quests.clear();
                quests.add(new Quest(0,"HARD","Nu stiu de ce e imporant.","Nimic"));
                quests.add(new Quest(1,"HARD","Nu stiu de ce e imporant.","Nimic"));
                quests.add(new Quest(2,"HARD","Nu stiu de ce e imporant.","Nimic"));
                quests.add(new Quest(3,"HARD","Nu stiu de ce e imporant.","Nimic"));
                quests.add(new Quest(4,"HARD","Nu stiu de ce e imporant.","Nimic"));

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
