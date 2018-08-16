package ro.changeneers.apprentice.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ro.changeneers.apprentice.ApprenticeApplication;
import ro.changeneers.apprentice.R;
import ro.changeneers.apprentice.adapters.QuestListAdapter;
import ro.changeneers.apprentice.models.MQuest;
import ro.changeneers.apprentice.utils.SharedPrefManager;

import static ro.changeneers.apprentice.utils.Constants.DIFFICULTY_EXTRA;
import static ro.changeneers.apprentice.utils.Constants.ID_EXTRA;

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
        int difficulty = receivingIntent.getIntExtra(DIFFICULTY_EXTRA, 0);
        this.difficulty = difficulty;


        List<MQuest> quests = new ArrayList<>();
        DifficultyTitleTV = findViewById(R.id.TextViewQuestListTitle);

        switch (difficulty) {
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
        int stars = SharedPrefManager.getInstance().getStarsFromSharedPrefs();
        QuestListAdapter questListAdapter = new QuestListAdapter(quests, this, stars );
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        questListAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(questListAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }


    @Override
    public void onQuestClick(MQuest quest) {

        if(SharedPrefManager.getInstance().getStarsFromSharedPrefs()<quest.minimStarsToUnlock){

            Toast.makeText(QuestListActivity.this,"Aduna mai multe stelute!", Toast.LENGTH_SHORT).show();

        }else {


            Intent intent = new Intent(this, QuestDetailActivity.class);
            intent.putExtra(ID_EXTRA, quest.id);
            intent.putExtra(DIFFICULTY_EXTRA, difficulty);
            startActivity(intent);

        }
    }
}
