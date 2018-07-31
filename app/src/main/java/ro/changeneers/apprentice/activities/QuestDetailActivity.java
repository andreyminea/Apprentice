package ro.changeneers.apprentice.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ro.changeneers.apprentice.R;
import ro.changeneers.apprentice.models.Curs;
import ro.changeneers.apprentice.models.Quest;

public class QuestDetailActivity extends AppCompatActivity {

    TextView titleQuest;
    TextView impQuest;
    TextView ceInvQuest;

    TextView titleCurs1;
    TextView textCurs1Pro;
    TextView textCurs1Contra;

    TextView titleCurs2;
    TextView textCurs2Pro;
    TextView textCurs2Contra;

    TextView titleCurs3;
    TextView textCurs3Pro;
    TextView textCurs3Contra;


    Curs curs  = new Curs(0,"Getting started","https://www.udemy.com/java-tutorial/","se ocupa foarte bine de notiuni de la 0",
            "dureaza 16 ore");

    Quest quest = new Quest(0,"Getting Started","Este important pentru ca e nevoie sa iti pui bazele programrii intainte sa incepi ceva mai complicat",
                                          "Vei invata ce inseamna notiunile de baza ale programarii in general",0,1,curs,curs,curs);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_detail);

        titleQuest = findViewById(R.id.TextViewQuestDetailTitle);
        titleQuest.setText(quest.getTitle());
        impQuest = findViewById(R.id.TextViewQuestDetailImportanta);
        impQuest.setText(quest.getImportanta());
        ceInvQuest = findViewById(R.id.TextViewQuestDetailCeInvat);
        ceInvQuest.setText(quest.getCeInvat());

        titleCurs1 = findViewById(R.id.TextViewTitleCurs1);
        titleCurs1.setText(quest.getCurs1().getTitle());
        titleCurs2 = findViewById(R.id.TextViewTitleCurs2);
        titleCurs2.setText(quest.getCurs2().getTitle());
        titleCurs3 = findViewById(R.id.TextViewTitleCurs3);
        titleCurs3.setText(quest.getCurs3().getTitle());

        final View curs1View = findViewById(R.id.childCurs1);
        textCurs1Pro = curs1View.findViewById(R.id.TextViewPro);
        textCurs1Pro.setText(quest.getCurs1().getPro());
        textCurs1Contra = curs1View.findViewById(R.id.TextViewContra);
        textCurs1Contra.setText(quest.getCurs1().getContra());


        final View curs2View = findViewById(R.id.childCurs2);
        textCurs2Pro = curs2View.findViewById(R.id.TextViewPro);
        textCurs2Pro.setText(quest.getCurs2().getPro());
        textCurs2Contra = curs2View.findViewById(R.id.TextViewContra);
        textCurs2Contra.setText(quest.getCurs2().getContra());


        final View curs3View = findViewById(R.id.childCurs3);
        textCurs3Pro = curs3View.findViewById(R.id.TextViewPro);
        textCurs3Pro.setText(quest.getCurs3().getPro());
        textCurs3Contra = curs3View.findViewById(R.id.TextViewContra);
        textCurs3Contra.setText(quest.getCurs3().getContra());

        final ViewGroup transitionCurs1 = (ViewGroup) findViewById(R.id.LinearLayoutParentCurs1);
        transitionCurs1.setOnClickListener(new View.OnClickListener() {
            boolean visible=false;
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(transitionCurs1);
                visible = !visible;
                curs1View.setVisibility(visible?View.VISIBLE:View.GONE);
            }
        });

        final ViewGroup transitionCurs2 = (ViewGroup) findViewById(R.id.LinearLayoutParentCurs2);
        transitionCurs2.setOnClickListener(new View.OnClickListener() {
            boolean visible=false;
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(transitionCurs2);
                visible = !visible;
                curs2View.setVisibility(visible?View.VISIBLE:View.GONE);
            }
        });

        final ViewGroup transitionCurs3 = (ViewGroup) findViewById(R.id.LinearLayoutParentCurs3);
        transitionCurs3.setOnClickListener(new View.OnClickListener() {
            boolean visible=false;
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(transitionCurs3);
                visible = !visible;
                curs3View.setVisibility(visible?View.VISIBLE:View.GONE);
            }
        });





    }
}
