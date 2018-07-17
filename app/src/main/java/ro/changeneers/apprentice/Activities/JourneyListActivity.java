package ro.changeneers.apprentice.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ro.changeneers.apprentice.Adapters.JourneyRecyclerViewAdapter;
import ro.changeneers.apprentice.Models.JourneyItem;
import ro.changeneers.apprentice.NavDrawer;
import ro.changeneers.apprentice.R;

public class JourneyListActivity extends NavDrawer {

    private static final String TAG = "JourneyListActivity";

    List<JourneyItem> journeys;
    private RecyclerView.LayoutManager jLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_list);
        journeys = new ArrayList<>();
        journeys.add(new JourneyItem("Programare",R.drawable.adventure));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerViewJourneyList);
        recyclerView.setHasFixedSize(true);
        JourneyRecyclerViewAdapter myJourneyAdapter = new JourneyRecyclerViewAdapter(this,journeys);
        jLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myJourneyAdapter);


    }
}