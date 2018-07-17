package ro.changeneers.apprentice.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import ro.changeneers.apprentice.Adapters.RecyclerViewAdapter;
import ro.changeneers.apprentice.Models.DashboardItem;
import ro.changeneers.apprentice.NavDrawer;
import ro.changeneers.apprentice.R;

public class MainActivity extends NavDrawer {
    private static final String TAG = "MainActivity";

    List<DashboardItem> items;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        items.add(new DashboardItem("User Journeys"," Un User Journey este un mod organizat de a te dezvolta spre obiectivul tău, astfel încât la fiecare pas vei fi îndrumat spre activități relevante nivelului la care te afli.\n" +
                "Descoperă User Journey-urile disponibile",R.drawable.adventure));
        items.add(new DashboardItem("Grup de Chat"," Ai o întrebare sau vrei să oferi la rândul tău ajutor?\n" +
                "Te poți folosi de grupurile de chat pentru a comunica cu ceilalți membri.",R.drawable.chatting));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerViewDashboard);
        recyclerView.setHasFixedSize(true);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,items);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);


    }
}
