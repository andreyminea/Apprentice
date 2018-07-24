package ro.changeneers.apprentice.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ro.changeneers.apprentice.Adapters.RecyclerViewAdapter;
import ro.changeneers.apprentice.Models.DashboardItem;
import ro.changeneers.apprentice.R;

public class MainActivity extends NavDrawer implements RecyclerViewAdapter.OnItemClickListener {

    private static final String TAG = "MainActivity";

    private RecyclerView.LayoutManager layoutManager;
    private int backpress = 0;

    //ids for list items
    public static final int USER_JOURNEY_ID = 23;
    public static final int CHAT_ID = 24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<DashboardItem> items = new ArrayList<>();
        items.add(new DashboardItem(USER_JOURNEY_ID, "User Journeys", " Un User Journey este un mod organizat de a te dezvolta spre obiectivul tău, astfel încât la fiecare pas vei fi îndrumat spre activități relevante nivelului la care te afli.\n" +
                "Descoperă User Journey-urile disponibile", R.drawable.adventure));
        items.add(new DashboardItem(CHAT_ID, "Grup de Chat", " Ai o întrebare sau vrei să oferi la rândul tău ajutor?\n" +
                "Te poți folosi de grupurile de chat pentru a comunica cu ceilalți membri.", R.drawable.chatting));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewDashboard);
        recyclerView.setHasFixedSize(true);

        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(items, this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(myAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void onBackPressed() {
        backpress = (backpress + 1);
        Toast.makeText(getApplicationContext(), " Press Back again to Exit ", Toast.LENGTH_SHORT).show();

        if (backpress > 1) {
            super.onBackPressed();
        }
    }


    @Override
    protected int getNavigationItemID() {
        return R.id.nav_home;
    }

    @Override
    public void onItemClick(DashboardItem item) {
        if (item.getId() == USER_JOURNEY_ID) {
            Intent intent = new Intent(this, JourneyListActivity.class);
            startActivity(intent);
        } else if (item.getId() == CHAT_ID) {
            Intent intent = new Intent(this, ChatActivity.class);
            startActivity(intent);
        }
    }
}
