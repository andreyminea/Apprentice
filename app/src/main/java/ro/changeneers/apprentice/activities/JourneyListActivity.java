package ro.changeneers.apprentice.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ro.changeneers.apprentice.adapters.JourneyRecyclerViewAdapter;
import ro.changeneers.apprentice.models.JourneyItem;
import ro.changeneers.apprentice.R;

import static ro.changeneers.apprentice.utils.Constants.POSITION_EXTRA;

public class JourneyListActivity extends NavDrawer implements JourneyRecyclerViewAdapter.OnJourneyItemClickListener {

    private static final String TAG = "JourneyListActivity";

    List<JourneyItem> journeys;
    private RecyclerView.LayoutManager jLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_list);
        journeys = new ArrayList<>();
        journeys.add(new JourneyItem("Programare","Afla mai multe detalii despre java si ce presupune acest Journey."));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerViewJourneyList);
        recyclerView.setHasFixedSize(true);
        JourneyRecyclerViewAdapter myJourneyAdapter = new JourneyRecyclerViewAdapter(journeys,this);
        jLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myJourneyAdapter);
    }

    @Override
    protected int getNavigationItemID() {
        return R.id.nav_jlist ;
    }


    @Override
    public void onJourneyCardClick() {

        JourneyListActivity.this.startActivity(getIntent(0));
    }

    @Override
    public void onOverviewIconClick() {

        JourneyListActivity.this.startActivity(getIntent(0));

    }

    @Override
    public void onBaniIconClick() {

        JourneyListActivity.this.startActivity(getIntent(1));

    }

    @Override
    public void onStresIconClick() {

        JourneyListActivity.this.startActivity(getIntent(2));

    }

    @Override
    public void onJobsIconClick() {

        JourneyListActivity.this.startActivity(getIntent(3));

    }

    private Intent getIntent(int position) {
        Intent intent = new Intent(this,BottomNavigationActivity.class) ;
        intent.putExtra(POSITION_EXTRA,position);
        return intent;
    }
}
