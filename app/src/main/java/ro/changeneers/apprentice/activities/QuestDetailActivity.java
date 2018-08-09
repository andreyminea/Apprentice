package ro.changeneers.apprentice.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ro.changeneers.apprentice.ApprenticeApplication;
import ro.changeneers.apprentice.R;
import ro.changeneers.apprentice.models.Curs;
import ro.changeneers.apprentice.models.Quest;

public class QuestDetailActivity extends AppCompatActivity {

    private static final int EASY = 1;
    private static final int MEDIUM = 2;
    private static final int HARD = 3;

    ScrollView scrollView;

    TextView titleQuest;
    TextView impQuest;
    TextView ceInvQuest;

    TextView titleCurs1;
    TextView textDescriereCurs1;
    TextView textCostCurs1;
    TextView textDurataCurs1;
    Button buttonBeginCurs1;


    TextView titleCurs2;
    TextView textDescriereCurs2;
    TextView textCostCurs2;
    TextView textDurataCurs2;
    Button buttonBeginCurs2;

    TextView titleCurs3;
    TextView textDescriereCurs3;
    TextView textCostCurs3;
    TextView textDurataCurs3;
    Button buttonBeginCurs3;


    private Quest quest;
    private static final String TAG = "QuestDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_detail);

//        Curs curs  = new Curs("0","Getting started","https://www.udemy.com/java-tutorial/","se ocupa foarte bine de notiuni de la 0",
//                "dureaza 16 ore");
//            Intent intent = getIntent();
//            int incomingQuestId = intent.getExtras().getInt("ID");
//            List<Curs> cursuri = new ArrayList<>();
//            cursuri.add(curs);
//            cursuri.add(curs);
//            cursuri.add(curs);
//            Quest quest = new Quest("0", "Getting Started", "Este important pentru ca e nevoie sa iti pui bazele programrii intainte sa incepi ceva mai complicat",
//                    "Vei invata ce inseamna notiunile de baza ale programarii in general", 0, cursuri);

        Intent intent = getIntent();
        String incomingQuestId = intent.getExtras().getString("ID");
        Log.d(TAG, "onCreate: ID FROM INTENT IS "+ incomingQuestId);
        int difficulty = intent.getExtras().getInt("DIFFICULTY");

        List<Quest> localList = new ArrayList<>();

        switch (difficulty) {
            case EASY:
                localList = ApprenticeApplication.getQuestListEasyDB();
                break;
            case MEDIUM:
                localList = ApprenticeApplication.getQuestListMediumDB();
                break;
            case HARD:
                localList = ApprenticeApplication.getQuestListHardDB();
                break;
        }

        for (int i = 0; i < localList.size(); i++) {
            Log.d(TAG, "onCreate: local list size " +localList.size());
            if (localList.get(i).id.equals(incomingQuestId)) {
                quest = localList.get(i);
                Log.d(TAG, "onCreate: "+quest.toString());
            }

        }


        scrollView = findViewById(R.id.scrollviewid);


        titleQuest = findViewById(R.id.TextViewQuestDetailTitle);
        titleQuest.setText(quest.title);
        impQuest = findViewById(R.id.TextViewQuestDetailImportanta);
        impQuest.setText(quest.importanta);
        ceInvQuest = findViewById(R.id.TextViewQuestDetailCeInvat);
        ceInvQuest.setText(quest.ceInvat);

        titleCurs1 = findViewById(R.id.TextViewTitleCurs1);
        titleCurs1.setText(quest.getListCursuri().get(0).title);
        textDurataCurs1 = findViewById(R.id.TextViewDurataCurs1);
        textDurataCurs1.setText(quest.getListCursuri().get(0).durata);
        textCostCurs1 = findViewById(R.id.TextViewCostCurs1);
        textCostCurs1.setText(quest.getListCursuri().get(0).cost);

        titleCurs2 = findViewById(R.id.TextViewTitleCurs2);
        titleCurs2.setText(quest.getListCursuri().get(1).title);
        textDurataCurs2 = findViewById(R.id.TextViewDurataCurs2);
        textDurataCurs2.setText(quest.getListCursuri().get(1).durata);
        textCostCurs2 = findViewById(R.id.TextViewCostCurs2);
        textCostCurs2.setText(quest.getListCursuri().get(1).cost);

        titleCurs3 = findViewById(R.id.TextViewTitleCurs3);
        titleCurs3.setText(quest.getListCursuri().get(2).title);
        textDurataCurs3 = findViewById(R.id.TextViewDurataCurs3);
        textDurataCurs3.setText(quest.getListCursuri().get(2).durata);
        textCostCurs3 = findViewById(R.id.TextViewCostCurs3);
        textCostCurs3.setText(quest.getListCursuri().get(2).cost);

        final View curs1View = findViewById(R.id.childCurs1);
        textDescriereCurs1 = curs1View.findViewById(R.id.TextViewDescriereCurs);
        textDescriereCurs1.setText(quest.getListCursuri().get(0).descriere);
        buttonBeginCurs1 = curs1View.findViewById(R.id.ButtonBeginQuest);
        buttonBeginCurs1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(quest.getListCursuri().get(0).url));
                startActivity(intent);

            }
        });



        final View curs2View = findViewById(R.id.childCurs2);
        textDescriereCurs2 = curs2View.findViewById(R.id.TextViewDescriereCurs);
        textDescriereCurs2.setText(quest.getListCursuri().get(1).descriere);
        buttonBeginCurs2 = curs2View.findViewById(R.id.ButtonBeginQuest);
        buttonBeginCurs2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(quest.getListCursuri().get(1).url));
                startActivity(intent);

            }
        });


        final View curs3View = findViewById(R.id.childCurs3);
        textDescriereCurs3 = curs3View.findViewById(R.id.TextViewDescriereCurs);
        textDescriereCurs3.setText(quest.getListCursuri().get(2).descriere);
        buttonBeginCurs3 = curs3View.findViewById(R.id.ButtonBeginQuest);
        buttonBeginCurs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(quest.getListCursuri().get(2).url));
                startActivity(intent);

                }
        });

        final ViewGroup transitionCurs1 = (ViewGroup) findViewById(R.id.LinearLayoutParentCurs1);
        transitionCurs1.setOnClickListener(new View.OnClickListener() {
            boolean visible = false;

            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(transitionCurs1);
                visible = !visible;
                curs1View.setVisibility(visible ? View.VISIBLE : View.GONE);
                curs1View.findViewById(R.id.ButtonBeginQuest).requestFocus();

            }
        });

        final ViewGroup transitionCurs2 = (ViewGroup) findViewById(R.id.LinearLayoutParentCurs2);
        transitionCurs2.setOnClickListener(new View.OnClickListener() {
            boolean visible = false;

            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(transitionCurs2);
                visible = !visible;
                curs2View.setVisibility(visible ? View.VISIBLE : View.GONE);
                curs2View.findViewById(R.id.ButtonBeginQuest).requestFocus();

            }
        });

        final ViewGroup transitionCurs3 = (ViewGroup) findViewById(R.id.LinearLayoutParentCurs3);
        transitionCurs3.setOnClickListener(new View.OnClickListener() {
            boolean visible = false;

            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(transitionCurs3);
                visible = !visible;
                curs3View.setVisibility(visible ? View.VISIBLE : View.GONE);
                curs3View.findViewById(R.id.ButtonBeginQuest).requestFocus();
            }
        });
    }

}
