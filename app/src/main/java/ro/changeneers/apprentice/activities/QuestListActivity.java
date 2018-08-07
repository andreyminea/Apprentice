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

import ro.changeneers.apprentice.ApprenticeApplication;
import ro.changeneers.apprentice.R;
import ro.changeneers.apprentice.adapters.QuestListAdapter;
import ro.changeneers.apprentice.models.Quest;

public class QuestListActivity extends AppCompatActivity implements QuestListAdapter.OnQuestClickListener {

    private static final String TAG = "QuestListActivity";

    private static final int EASY = 1;
    private static final int MEDIUM = 2;
    private static final int HARD = 3;
    private int difficulty;


    private RecyclerView.LayoutManager layoutManager;
    private TextView DifficultyTitleTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_list);

        Intent receivingIntent = getIntent();
        int difficulty = receivingIntent.getIntExtra("DIFFICULTY",0);
        this.difficulty = difficulty;


        List<Quest> quests = new ArrayList<>();
        DifficultyTitleTV = findViewById(R.id.TextViewQuestListTitle);

        switch(difficulty){
            case EASY:
                DifficultyTitleTV.setText("Easy");
                quests = ApprenticeApplication.getQuestListEasyDB();
                break;
            case MEDIUM:
                DifficultyTitleTV.setText("Medium");
                quests = ApprenticeApplication.getQuestListMediumDB();
                break;
            case HARD:
                DifficultyTitleTV.setText("Hard");
                quests = ApprenticeApplication.getQuestListHardDB();
        }


        RecyclerView recyclerView = findViewById(R.id.RecyclerViewQuestList);
        recyclerView.setHasFixedSize(true);

        QuestListAdapter questListAdapter = new QuestListAdapter(quests, this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(questListAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }


    @Override
    public void onQuestClick(Quest quest) {
        Intent intent = new Intent(this, QuestDetailActivity.class);
        intent.putExtra("ID",quest.id);
        intent.putExtra("DIFFICULTY",difficulty);
        startActivity(intent);
    }
}
